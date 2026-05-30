package hethongwebbanvexemphim.dto.response;

import hethongwebbanvexemphim.entity.enums.PaymentMethod;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckoutSummaryDto {

    private final Integer showtimeId;
    private final Integer movieId;
    private final String movieTitle;
    private final String posterUrl;
    private final String rating;
    private final String ratingBadgeClass;
    private final String formatType;
    private final String cinemaName;
    private final String roomName;
    private final LocalDateTime startTime;
    private final List<SelectedSeatSummaryDto> selectedSeats;
    private final List<SelectedProductSummaryDto> selectedProducts;
    private final BigDecimal seatTotal;
    private final BigDecimal productTotal;
    private final BigDecimal grandTotal;
    private final String seatLabels;
    private final List<PaymentMethod> paymentMethods;
}
