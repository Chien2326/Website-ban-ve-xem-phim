package hethongwebbanvexemphim.dto.admin;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowtimeForm {

    private Integer showtimeId;
    private Integer movieId;
    private Integer roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String formatType = "2D Phụ Đề";
    private BigDecimal priceBase;
}
