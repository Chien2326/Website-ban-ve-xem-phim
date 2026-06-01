package hethongwebbanvexemphim.dto.admin;

import hethongwebbanvexemphim.entity.enums.SeatType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatForm {
    private Integer seatId;
    private Integer roomId;
    private String rowChar;
    private Integer seatNumber;
    private SeatType seatType;
}
