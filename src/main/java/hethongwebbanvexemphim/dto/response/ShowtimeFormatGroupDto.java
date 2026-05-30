package hethongwebbanvexemphim.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShowtimeFormatGroupDto {

    private final String formatType;
    private final List<ShowtimeDto> slots;
}
