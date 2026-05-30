package hethongwebbanvexemphim.dto.response;

import java.time.Instant;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileDto {

    private final Integer userId;
    private final String fullName;
    private final String email;
    private final String phone;
    private final String genderLabel;
    private final LocalDate birthday;
    private final String roleName;
    private final Instant createdAt;
}
