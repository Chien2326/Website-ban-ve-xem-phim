package hethongwebbanvexemphim.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CinemaScheduleDto {

    private final String cinemaName;
    private final List<ShowtimeFormatGroupDto> formatGroups;
}
