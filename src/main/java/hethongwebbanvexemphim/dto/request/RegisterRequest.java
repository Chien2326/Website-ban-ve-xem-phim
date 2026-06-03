package hethongwebbanvexemphim.dto.request;

import hethongwebbanvexemphim.entity.enums.Gender;
import hethongwebbanvexemphim.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Họ và tên không được để trống")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;

    @NotNull(message = "Giới tính không được để trống")
    private Gender gender;

    private LocalDate birthday;

    @NotBlank(message = "Mật khẩu không được để trống")
    @ValidPassword
    private String password;

    private String confirmPassword;
}
