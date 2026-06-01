package hethongwebbanvexemphim.service.admin;

import hethongwebbanvexemphim.dto.admin.ShowtimeForm;
import hethongwebbanvexemphim.entity.Movie;
import hethongwebbanvexemphim.entity.Room;
import hethongwebbanvexemphim.entity.Seat;
import hethongwebbanvexemphim.entity.ShowSeat;
import hethongwebbanvexemphim.entity.Showtime;
import hethongwebbanvexemphim.entity.enums.SeatType;
import hethongwebbanvexemphim.entity.enums.ShowSeatStatus;
import hethongwebbanvexemphim.repository.MovieRepository;
import hethongwebbanvexemphim.repository.RoomRepository;
import hethongwebbanvexemphim.repository.SeatRepository;
import hethongwebbanvexemphim.repository.ShowSeatRepository;
import hethongwebbanvexemphim.repository.ShowtimeRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final SeatRepository seatRepository;
    private final ShowSeatRepository showSeatRepository;

    @Transactional(readOnly = true)
    public List<Showtime> findAll() {
        return showtimeRepository.findAllForAdmin();
    }

    @Transactional(readOnly = true)
    public List<Movie> findMovies() {
        return movieRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Room> findRooms() {
        return roomRepository.findAllWithCinema();
    }

    @Transactional(readOnly = true)
    public ShowtimeForm getForm(Integer showtimeId) {
        Showtime showtime = showtimeRepository.findDetailById(showtimeId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy suất chiếu"));
        ShowtimeForm form = new ShowtimeForm();
        form.setShowtimeId(showtime.getShowtimeId());
        form.setMovieId(showtime.getMovie().getMovieId());
        form.setRoomId(showtime.getRoom().getRoomId());
        form.setStartTime(showtime.getStartTime());
        form.setEndTime(showtime.getEndTime());
        form.setFormatType(showtime.getFormatType());
        form.setPriceBase(showtime.getPriceBase());
        return form;
    }

    @Transactional
    public Showtime save(ShowtimeForm form) {
        validate(form);
        Movie movie = movieRepository.findById(form.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phim"));
        Room room = roomRepository.findById(form.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phòng chiếu"));

        LocalDateTime endTime = form.getEndTime();
        if (endTime == null) {
            endTime = form.getStartTime().plusMinutes(movie.getDuration());
        }
        if (!endTime.isAfter(form.getStartTime())) {
            throw new IllegalArgumentException("Giờ kết thúc phải sau giờ bắt đầu");
        }

        Showtime showtime;
        boolean isNew = form.getShowtimeId() == null;
        if (isNew) {
            showtime = new Showtime();
            showtime.setMovie(movie);
            showtime.setRoom(room);
        } else {
            showtime = showtimeRepository.findDetailById(form.getShowtimeId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy suất chiếu"));
            if (!showtime.getRoom().getRoomId().equals(room.getRoomId())) {
                ensureRoomChangeAllowed(showtime.getShowtimeId());
                showSeatRepository.deleteAll(showSeatRepository.findByShowtimeShowtimeId(showtime.getShowtimeId()));
                showtime.setRoom(room);
            }
            showtime.setMovie(movie);
        }

        showtime.setStartTime(form.getStartTime());
        showtime.setEndTime(endTime);
        showtime.setFormatType(form.getFormatType().trim());
        showtime.setPriceBase(form.getPriceBase());
        showtime = showtimeRepository.save(showtime);

        if (isNew) {
            generateShowSeats(showtime, room, form.getPriceBase());
        } else {
            refreshAvailableSeatPrices(showtime.getShowtimeId(), form.getPriceBase());
        }
        return showtime;
    }

    @Transactional
    public void delete(Integer showtimeId) {
        if (showSeatRepository.existsTicketForShowtime(showtimeId)) {
            throw new IllegalArgumentException("Không thể xóa suất đã có vé bán");
        }
        showSeatRepository.deleteAll(showSeatRepository.findByShowtimeShowtimeId(showtimeId));
        showtimeRepository.deleteById(showtimeId);
    }

    private void ensureRoomChangeAllowed(Integer showtimeId) {
        if (showSeatRepository.existsByShowtimeShowtimeIdAndStatusIn(
                showtimeId, List.of(ShowSeatStatus.Held, ShowSeatStatus.Booked))) {
            throw new IllegalArgumentException("Không thể đổi phòng khi đã có ghế giữ/đã bán");
        }
    }

    private void generateShowSeats(Showtime showtime, Room room, BigDecimal priceBase) {
        List<Seat> seats = seatRepository.findByRoomRoomId(room.getRoomId());
        if (seats.isEmpty()) {
            throw new IllegalArgumentException("Phòng chưa có sơ đồ ghế");
        }
        for (Seat seat : seats) {
            showSeatRepository.save(ShowSeat.builder()
                    .showtime(showtime)
                    .seat(seat)
                    .status(ShowSeatStatus.Available)
                    .price(calculateSeatPrice(priceBase, seat.getSeatType()))
                    .build());
        }
    }

    private void refreshAvailableSeatPrices(Integer showtimeId, BigDecimal priceBase) {
        List<ShowSeat> showSeats = showSeatRepository.findByShowtimeWithSeats(showtimeId);
        for (ShowSeat showSeat : showSeats) {
            if (showSeat.getStatus() == ShowSeatStatus.Available) {
                showSeat.setPrice(calculateSeatPrice(priceBase, showSeat.getSeat().getSeatType()));
            }
        }
    }

    static BigDecimal calculateSeatPrice(BigDecimal priceBase, SeatType seatType) {
        if (seatType == SeatType.VIP) {
            return priceBase.multiply(new BigDecimal("1.25")).setScale(0, RoundingMode.HALF_UP);
        }
        if (seatType == SeatType.Sweetbox) {
            return priceBase.multiply(new BigDecimal("1.5")).setScale(0, RoundingMode.HALF_UP);
        }
        return priceBase;
    }

    private static void validate(ShowtimeForm form) {
        if (form.getMovieId() == null) {
            throw new IllegalArgumentException("Chọn phim");
        }
        if (form.getRoomId() == null) {
            throw new IllegalArgumentException("Chọn phòng chiếu");
        }
        if (form.getStartTime() == null) {
            throw new IllegalArgumentException("Chọn giờ bắt đầu");
        }
        if (form.getPriceBase() == null || form.getPriceBase().signum() < 0) {
            throw new IllegalArgumentException("Giá vé gốc không hợp lệ");
        }
        if (form.getFormatType() == null || form.getFormatType().isBlank()) {
            throw new IllegalArgumentException("Nhập định dạng suất chiếu");
        }
    }
}
