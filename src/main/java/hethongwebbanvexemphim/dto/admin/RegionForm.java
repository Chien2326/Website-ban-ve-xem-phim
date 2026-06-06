package hethongwebbanvexemphim.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionForm {
    private Integer regionId;

    @NotBlank(message = "Tên khu vực không được để trống")
    @Size(max = 100, message = "Tên khu vực không được quá 100 ký tự")
    @Pattern(regexp = "^[\\p{L}0-9 .]+$", message = "Tên khu vực chỉ được chứa chữ, số, dấu chấm và khoảng trắng")
    private String regionName;
}
