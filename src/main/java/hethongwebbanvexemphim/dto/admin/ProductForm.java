package hethongwebbanvexemphim.dto.admin;

import hethongwebbanvexemphim.entity.enums.ProductStatus;
import hethongwebbanvexemphim.entity.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductForm {
    private Integer productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private ProductType productType;
    private ProductStatus status;
}
