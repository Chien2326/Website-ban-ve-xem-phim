package hethongwebbanvexemphim.service;

import hethongwebbanvexemphim.dto.mapper.DtoMapper;
import hethongwebbanvexemphim.dto.response.CinemaScheduleDto;
import hethongwebbanvexemphim.dto.response.ShowtimeDto;
import hethongwebbanvexemphim.dto.response.ShowtimeFormatGroupDto;
import hethongwebbanvexemphim.entity.Showtime;
import hethongwebbanvexemphim.repository.ShowtimeRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieDetailService {

    private static final int SCHEDULE_DATE_LIMIT = 14;

    private final ShowtimeRepository showtimeRepository;

    @Transactional(readOnly = true)
    public List<LocalDate> getScheduleDates(Integer movieId, Integer regionId) {
        return getUpcomingShowtimes(movieId, regionId).stream()
                .map(showtime -> showtime.getStartTime().toLocalDate())
                .distinct()
                .sorted()
                .limit(SCHEDULE_DATE_LIMIT)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CinemaScheduleDto> getSchedule(
            Integer movieId,
            Integer regionId,
            Integer cinemaId,
            LocalDate date) {
        LocalDate selectedDate = date != null ? date : LocalDate.now();
        LocalDateTime dayStart = selectedDate.atStartOfDay();
        LocalDateTime dayEnd = selectedDate.plusDays(1).atStartOfDay();

        List<ShowtimeDto> showtimes = DtoMapper.toShowtimes(getUpcomingShowtimes(movieId, regionId)).stream()
                .filter(st -> !st.getStartTime().isBefore(dayStart) && st.getStartTime().isBefore(dayEnd))
                .filter(st -> cinemaId == null || matchesCinema(st, cinemaId))
                .toList();

        return groupByCinemaAndFormat(showtimes);
    }

    private List<Showtime> getUpcomingShowtimes(Integer movieId, Integer regionId) {
        return showtimeRepository.findUpcomingByMovie(movieId, LocalDateTime.now(), regionId);
    }

    private static boolean matchesCinema(ShowtimeDto showtime, Integer cinemaId) {
        return cinemaId == null || cinemaId.equals(showtime.getCinemaId());
    }

    private static List<CinemaScheduleDto> groupByCinemaAndFormat(List<ShowtimeDto> showtimes) {
        Map<String, Map<String, List<ShowtimeDto>>> grouped = showtimes.stream()
                .collect(Collectors.groupingBy(
                        ShowtimeDto::getCinemaName,
                        LinkedHashMap::new,
                        Collectors.groupingBy(
                                st -> st.getFormatType() != null ? st.getFormatType() : "2D Phụ Đề",
                                LinkedHashMap::new,
                                Collectors.toList())));

        List<CinemaScheduleDto> result = new ArrayList<>();
        grouped.forEach((cinemaName, formatMap) -> {
            List<ShowtimeFormatGroupDto> formatGroups = formatMap.entrySet().stream()
                    .map(entry -> ShowtimeFormatGroupDto.builder()
                            .formatType(entry.getKey())
                            .slots(entry.getValue())
                            .build())
                    .toList();
            result.add(CinemaScheduleDto.builder()
                    .cinemaName(cinemaName)
                    .formatGroups(formatGroups)
                    .build());
        });
        return result;
    }
}
