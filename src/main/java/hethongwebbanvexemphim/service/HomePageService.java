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
        return DtoMapper.toShowtimes(getUpcomingShowtimes(regionId));
    }

    private List<Showtime> getUpcomingShowtimes(Integer regionId) {
        return showtimeRepository.findUpcoming(
                LocalDateTime.now(),
                regionId,
                PageRequest.of(0, UPCOMING_SHOWTIME_LIMIT));
    }
}
