package hethongwebbanvexemphim.dto.response;

import hethongwebbanvexemphim.entity.enums.ProductType;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDto {

    private final Integer productId;
    private final String productName;
    private final String description;
    private final BigDecimal price;
    private final String imageUrl;
    private final ProductType productType;
    private final String typeLabel;
}
