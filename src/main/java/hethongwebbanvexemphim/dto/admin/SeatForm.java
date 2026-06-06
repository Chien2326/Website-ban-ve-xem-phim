package hethongwebbanvexemphim.dto.admin;

import hethongwebbanvexemphim.entity.enums.SeatType;
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
public class SeatForm {
    private Integer seatId;

    @NotNull(message = "Vui lòng chọn phòng")
    private Integer roomId;

    @NotBlank(message = "Hàng không được để trống")
    @Size(min = 1, max = 1, message = "Hàng phải là một ký tự")
    @Pattern(regexp = "^[A-Za-z]$", message = "Hàng phải là một chữ cái")
    private String rowChar;

    @NotNull(message = "Số ghế không được để trống")
    @Min(value = 1, message = "Số ghế phải lớn hơn 0")
    private Integer seatNumber;

    @NotNull(message = "Vui lòng chọn loại ghế")
    private SeatType seatType;
}
