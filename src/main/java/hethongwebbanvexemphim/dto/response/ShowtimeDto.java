package hethongwebbanvexemphim.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShowtimeDto {

    private final Integer showtimeId;
    private final Integer movieId;
    private final String movieTitle;
    private final Integer cinemaId;
    private final Integer roomId;
    private final String roomName;
    private final String cinemaName;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String formatType;
    private final BigDecimal priceBase;
}
