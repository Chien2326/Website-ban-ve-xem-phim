package hethongwebbanvexemphim.dto.admin;

import hethongwebbanvexemphim.entity.enums.ProductStatus;
import hethongwebbanvexemphim.entity.enums.ProductType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductForm {
    private Integer productId;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 100, message = "Tên sản phẩm không được quá 100 ký tự")
    @Pattern(regexp = "^[\\p{L}0-9 ,.'()\\-]+$", message = "Tên sản phẩm chỉ được chứa chữ, số và các ký tự cơ bản")
    private String productName;

    private String description;

    @NotNull(message = "Giá bán không được để trống")
    @DecimalMin(value = "0.01", message = "Giá bán phải lớn hơn 0")
    private BigDecimal price;

    private String imageUrl;

    @NotNull(message = "Vui lòng chọn loại sản phẩm")
    private ProductType productType;

    private ProductStatus status;
}
