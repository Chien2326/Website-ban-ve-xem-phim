package hethongwebbanvexemphim.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CinemaForm {
    private Integer cinemaId;

    @NotNull(message = "Vui lòng chọn khu vực")
    private Integer regionId;

    @NotBlank(message = "Tên rạp không được để trống")
    @Size(max = 100, message = "Tên rạp không được quá 100 ký tự")
    @Pattern(regexp = "^[\\p{L}0-9 .,]+$", message = "Tên rạp chỉ được chứa chữ, số, dấu chấm, dấu phẩy và khoảng trắng")
    private String name;

    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(max = 255, message = "Địa chỉ không được quá 255 ký tự")
    private String address;
}
