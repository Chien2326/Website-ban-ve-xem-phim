package hethongwebbanvexemphim.dto.admin;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SeatMonitorPageDto {

    private final Integer showtimeId;
    private final String movieTitle;
    private final String cinemaName;
    private final String roomName;
    private final LocalDateTime startTime;
    private final String formatType;
    private final long availableCount;
    private final long heldCount;
    private final long bookedCount;
    private final List<AdminSeatRowDto> seatRows;
}
