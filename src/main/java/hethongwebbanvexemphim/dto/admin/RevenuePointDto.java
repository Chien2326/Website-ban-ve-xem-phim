package hethongwebbanvexemphim.dto.admin;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RevenuePointDto {

    private final String label;
    private final BigDecimal amount;
}
