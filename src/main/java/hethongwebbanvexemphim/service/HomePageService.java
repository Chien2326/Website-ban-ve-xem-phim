package hethongwebbanvexemphim.service;

import hethongwebbanvexemphim.dto.mapper.DtoMapper;
import hethongwebbanvexemphim.dto.response.CinemaDto;
import hethongwebbanvexemphim.dto.response.ShowtimeDto;
import hethongwebbanvexemphim.entity.Showtime;
import hethongwebbanvexemphim.repository.CinemaRepository;
import hethongwebbanvexemphim.repository.ShowtimeRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HomePageService {

    private static final int UPCOMING_SHOWTIME_LIMIT = 40;

    private final CinemaRepository cinemaRepository;
    private final ShowtimeRepository showtimeRepository;

    @Transactional(readOnly = true)
    public List<CinemaDto> getBookingCinemas(Integer regionId) {
        if (regionId == null) {
            return DtoMapper.toCinemas(cinemaRepository.findAllByOrderByNameAsc());
        }
        return DtoMapper.toCinemas(cinemaRepository.findByRegionRegionIdOrderByNameAsc(regionId));
    }

    @Transactional(readOnly = true)
    public List<LocalDate> getBookingDates(Integer regionId) {
        return getUpcomingShowtimes(regionId).stream()
                .map(showtime -> showtime.getStartTime().toLocalDate())
                .distinct()
                .sorted()
                .limit(14)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ShowtimeDto> getBookingShowtimes(Integer regionId) {
        var showtimes = getUpcomingShowtimes(regionId);
        System.out.println("=== DEBUG HomePageService ===");
        System.out.println("Found showtimes: " + showtimes.size());
        showtimes.forEach(s -> System.out.println(" - " + s.getShowtimeId() + " : " + s.getStartTime()));
        return DtoMapper.toShowtimes(showtimes);
    }

    private List<Showtime> getUpcomingShowtimes(Integer regionId) {
        System.out.println("=== getUpcomingShowtimes called with regionId: " + regionId + " and now: " + LocalDateTime.now());
        // Dùng findAllForAdmin() để đảm bảo nạp đầy đủ các quan hệ (movie, room, cinema)
        var allShowtimes = showtimeRepository.findAllForAdmin();
        System.out.println("findAllForAdmin returned: " + allShowtimes.size() + " showtimes");
        
        // Lọc các suất chiếu có startTime >= now
        var now = LocalDateTime.now();
        var filtered = allShowtimes.stream()
                .filter(st -> !st.getStartTime().isBefore(now))
                .limit(UPCOMING_SHOWTIME_LIMIT)
                .toList();
        
        System.out.println("After filtering by time: " + filtered.size() + " showtimes");
        filtered.forEach(st -> System.out.println(" - Showtime " + st.getShowtimeId() + ": " + st.getStartTime() + ", Movie: " + (st.getMovie() != null ? st.getMovie().getTitle() : "NULL")));
        
        return filtered;
    }
}
