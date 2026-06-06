package hethongwebbanvexemphim.dto.admin;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomForm {
    private Integer roomId;

    @NotNull(message = "Vui lòng chọn cụm rạp")
    private Integer cinemaId;

    @NotBlank(message = "Tên phòng không được để trống")
    @Size(max = 50, message = "Tên phòng không được quá 50 ký tự")
    @Pattern(regexp = "^[\\p{L}0-9 ]+$", message = "Tên phòng chỉ được chứa chữ, số và khoảng trắng")
    private String name;

    @NotNull(message = "Tổng số ghế không được để trống")
    @Min(value = 1, message = "Tổng số ghế phải lớn hơn 0")
    @Max(value = 1000, message = "Tổng số ghế không được lớn hơn 1000")
    private Integer totalSeats;
}
