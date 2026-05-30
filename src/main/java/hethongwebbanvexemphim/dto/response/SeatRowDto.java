package hethongwebbanvexemphim.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SeatRowDto {

    private final String rowChar;
    private final List<SeatDto> seats;
}
