package hethongwebbanvexemphim.dto.admin;

import hethongwebbanvexemphim.entity.enums.ShowSeatStatus;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminSeatCellDto {

    private final Integer showSeatId;
    private final String seatLabel;
    private final ShowSeatStatus status;
    private final BigDecimal price;
    private final String cssClass;
    private final String statusLabel;
}
