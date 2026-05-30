package hethongwebbanvexemphim.dto.response;

import hethongwebbanvexemphim.entity.enums.SeatType;
import hethongwebbanvexemphim.entity.enums.ShowSeatStatus;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SeatDto {

    private final Integer showSeatId;
    private final Integer seatId;
    private final String rowChar;
    private final Integer seatNumber;
    private final SeatType seatType;
    private final ShowSeatStatus status;
    private final BigDecimal price;
    private final boolean unavailable;
    private final String cssClass;
}
