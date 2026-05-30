package hethongwebbanvexemphim.dto.response;

import hethongwebbanvexemphim.entity.enums.BookingStatus;
import hethongwebbanvexemphim.entity.enums.PaymentMethod;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookingSummaryDto {

    private final Integer bookingId;
    private final String movieTitle;
    private final String cinemaName;
    private final LocalDateTime showtimeStart;
    private final BigDecimal totalAmount;
    private final LocalDateTime bookingTime;
    private final PaymentMethod paymentMethod;
    private final BookingStatus status;
    private final int ticketCount;
}
