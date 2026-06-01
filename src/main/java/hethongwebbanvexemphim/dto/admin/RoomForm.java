package hethongwebbanvexemphim.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomForm {
    private Integer roomId;
    private Integer cinemaId;
    private String name;
    private Integer totalSeats;
}
