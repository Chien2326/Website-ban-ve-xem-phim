package hethongwebbanvexemphim.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleForm {
    private Integer roleId;

    @NotBlank(message = "Tên vai trò không được để trống")
    @Size(max = 50, message = "Tên vai trò không được quá 50 ký tự")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Tên vai trò chỉ được chứa chữ cái và khoảng trắng")
    private String roleName;

    private String description;
}
