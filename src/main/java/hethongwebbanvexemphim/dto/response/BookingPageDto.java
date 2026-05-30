package hethongwebbanvexemphim.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookingPageDto {

    private final Integer showtimeId;
    private final Integer movieId;
    private final String movieTitle;
    private final String posterUrl;
    private final String rating;
    private final String ratingBadgeClass;
    private final String formatType;
    private final String cinemaName;
    private final String roomName;
    private final LocalDateTime startTime;
    private final List<ShowtimeDto> alternateShowtimes;
    private final List<SeatRowDto> seatRows;
}
