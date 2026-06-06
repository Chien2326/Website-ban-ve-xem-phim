package hethongwebbanvexemphim.dto.admin;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowtimeForm {

    private Integer showtimeId;

    @NotNull(message = "Chọn phim")
    private Integer movieId;

    @NotNull(message = "Chọn phòng chiếu")
    private Integer roomId;

    @NotNull(message = "Chọn giờ bắt đầu")
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @NotBlank(message = "Nhập định dạng suất chiếu")
    @Size(max = 50, message = "Định dạng suất chiếu không được quá 50 ký tự")
    private String formatType = "2D Phụ Đề";

    @NotNull(message = "Giá vé gốc không được để trống")
    @DecimalMin(value = "0.01", message = "Giá vé gốc phải lớn hơn 0")
    private BigDecimal priceBase;
}
