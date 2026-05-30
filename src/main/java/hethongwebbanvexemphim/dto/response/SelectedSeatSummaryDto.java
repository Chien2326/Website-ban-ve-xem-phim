package hethongwebbanvexemphim.dto.response;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SelectedSeatSummaryDto {

    private final Integer showSeatId;
    private final String seatLabel;
    private final BigDecimal price;
}
