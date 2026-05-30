package hethongwebbanvexemphim.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovieDetailDto {

    private final Integer movieId;
    private final String title;
    private final String description;
    private final Integer durationMinutes;
    private final LocalDate releaseDate;
    private final String posterUrl;
    private final String trailerUrl;
    private final BigDecimal star;
    private final String statusLabel;
    private final String rating;
    private final String ratingBadgeClass;
    private final List<String> genres;
}
