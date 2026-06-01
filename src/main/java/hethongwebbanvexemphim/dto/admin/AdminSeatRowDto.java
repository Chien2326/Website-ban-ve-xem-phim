package hethongwebbanvexemphim.dto.admin;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminSeatRowDto {

    private final String rowChar;
    private final List<AdminSeatCellDto> seats;
}
