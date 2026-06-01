package hethongwebbanvexemphim.dto.admin;

import hethongwebbanvexemphim.entity.enums.AgeRating;
import hethongwebbanvexemphim.entity.enums.MovieStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieForm {

    private Integer movieId;
    private String title;
    private String description;
    private Integer duration;
    private LocalDate releaseDate;
    private String posterUrl;
    private String trailerUrl;
    private BigDecimal star;
    private MovieStatus status = MovieStatus.COMING_SOON;
    private AgeRating rating;
    private List<Integer> genreIds = new ArrayList<>();
}
