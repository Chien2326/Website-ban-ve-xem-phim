package hethongwebbanvexemphim.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovieSummaryDto {

    private final Integer movieId;
    private final String title;
    private final String posterUrl;
    private final String rating;
    private final String ratingBadgeClass;
    private final BigDecimal star;
    private final String statusLabel;
    private final Integer durationMinutes;
    private final LocalDate releaseDate;
    private final String detailUrl;
    private final List<String> genres;
}
