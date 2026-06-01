package hethongwebbanvexemphim.service.admin;

import hethongwebbanvexemphim.dto.admin.AdminSeatCellDto;
import hethongwebbanvexemphim.dto.admin.AdminSeatRowDto;
import hethongwebbanvexemphim.dto.admin.SeatMonitorPageDto;
import hethongwebbanvexemphim.entity.ShowSeat;
import hethongwebbanvexemphim.entity.Showtime;
import hethongwebbanvexemphim.entity.enums.SeatType;
import hethongwebbanvexemphim.entity.enums.ShowSeatStatus;
import hethongwebbanvexemphim.repository.ShowSeatRepository;
import hethongwebbanvexemphim.repository.ShowtimeRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminSeatMonitorService {

    private final ShowtimeRepository showtimeRepository;
    private final ShowSeatRepository showSeatRepository;

    @Transactional(readOnly = true)
    public SeatMonitorPageDto getMonitorPage(Integer showtimeId) {
        Showtime showtime = showtimeRepository.findDetailById(showtimeId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy suất chiếu"));

        List<ShowSeat> showSeats = showSeatRepository.findByShowtimeWithSeats(showtimeId);
        List<AdminSeatRowDto> rows = groupSeats(showSeats);

        return SeatMonitorPageDto.builder()
                .showtimeId(showtimeId)
                .movieTitle(showtime.getMovie().getTitle())
                .cinemaName(showtime.getRoom().getCinema().getName())
                .roomName(showtime.getRoom().getName())
                .startTime(showtime.getStartTime())
                .formatType(showtime.getFormatType())
                .availableCount(showSeatRepository.countByShowtimeShowtimeIdAndStatus(
                        showtimeId, ShowSeatStatus.Available))
                .heldCount(showSeatRepository.countByShowtimeShowtimeIdAndStatus(
                        showtimeId, ShowSeatStatus.Held))
                .bookedCount(showSeatRepository.countByShowtimeShowtimeIdAndStatus(
                        showtimeId, ShowSeatStatus.Booked))
                .seatRows(rows)
                .build();
    }

    @Transactional
    public void updateSeatStatus(Integer showtimeId, Integer showSeatId, ShowSeatStatus status) {
        ShowSeat showSeat = showSeatRepository.findById(showSeatId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy ghế"));
        if (!showSeat.getShowtime().getShowtimeId().equals(showtimeId)) {
            throw new IllegalArgumentException("Ghế không thuộc suất chiếu này");
        }
        showSeat.setStatus(status);
    }

    private static List<AdminSeatRowDto> groupSeats(List<ShowSeat> showSeats) {
        Map<String, List<AdminSeatCellDto>> grouped = new LinkedHashMap<>();
        showSeats.stream()
                .sorted(Comparator
                        .comparing((ShowSeat ss) -> ss.getSeat().getRowChar())
                        .thenComparing(ss -> ss.getSeat().getSeatNumber()))
                .forEach(showSeat -> {
                    String row = showSeat.getSeat().getRowChar();
                    grouped.computeIfAbsent(row, key -> new ArrayList<>())
                            .add(toCell(showSeat));
                });

        return grouped.entrySet().stream()
                .map(entry -> AdminSeatRowDto.builder()
                        .rowChar(entry.getKey())
                        .seats(entry.getValue())
                        .build())
                .toList();
    }

    private static AdminSeatCellDto toCell(ShowSeat showSeat) {
        ShowSeatStatus status = showSeat.getStatus();
        return AdminSeatCellDto.builder()
                .showSeatId(showSeat.getShowSeatId())
                .seatLabel(showSeat.getSeat().getRowChar() + showSeat.getSeat().getSeatNumber())
                .status(status)
                .price(showSeat.getPrice())
                .statusLabel(statusLabel(status))
                .cssClass(adminSeatCssClass(status, showSeat.getSeat().getSeatType()))
                .build();
    }

    private static String statusLabel(ShowSeatStatus status) {
        return switch (status) {
            case Available -> "Trống";
            case Held -> "Đang giữ";
            case Booked -> "Đã bán";
        };
    }

    static String adminSeatCssClass(ShowSeatStatus status, SeatType seatType) {
        return switch (status) {
            case Booked -> "admin-seat-booked";
            case Held -> "admin-seat-held";
            case Available -> seatType == SeatType.VIP ? "admin-seat-vip" : "admin-seat-available";
        };
    }
}
