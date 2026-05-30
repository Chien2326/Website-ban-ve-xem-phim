package hethongwebbanvexemphim.dto.request;

import hethongwebbanvexemphim.entity.enums.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingCreateRequest {

    @NotNull(message = "Suất chiếu không hợp lệ")
    private Integer showtimeId;

    @NotEmpty(message = "Chọn ít nhất một ghế")
    private List<Integer> showSeatIds;

    @NotNull(message = "Chọn phương thức thanh toán")
    private PaymentMethod paymentMethod;
}
