package hethongwebbanvexemphim.dto.admin;

import hethongwebbanvexemphim.entity.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserForm {
    private Integer userId;
    private Integer roleId;
    private String fullName;
    private String email;
    private String phone;
    private Gender gender;
    private LocalDate birthday;
    private String password;
}
