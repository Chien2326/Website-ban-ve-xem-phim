package hethongwebbanvexemphim.dto.admin;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TopProductDto {

    private final Integer productId;
    private final String productName;
    private final long quantitySold;
    private final BigDecimal revenue;
}
