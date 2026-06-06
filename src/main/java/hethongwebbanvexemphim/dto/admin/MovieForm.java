package hethongwebbanvexemphim.dto.admin;

import hethongwebbanvexemphim.entity.enums.AgeRating;
import hethongwebbanvexemphim.entity.enums.MovieStatus;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Tên phim không được để trống")
    @Size(max = 150, message = "Tên phim không được quá 150 ký tự")
    @Pattern(regexp = "^[\\p{L}0-9 ,.'()\\-!?:]+$", message = "Tên phim chỉ được chứa chữ, số và ký tự cơ bản")
    private String title;

    private String description;

    @NotNull(message = "Thời lượng phim không được để trống")
    @Min(value = 1, message = "Thời lượng phim phải lớn hơn 0")
    private Integer duration;

    private LocalDate releaseDate;
    private String posterUrl;
    private String trailerUrl;

    @DecimalMin(value = "0.0", inclusive = true, message = "Điểm đánh giá phải lớn hơn hoặc bằng 0")
    @DecimalMax(value = "10.0", inclusive = true, message = "Điểm đánh giá phải nhỏ hơn hoặc bằng 10")
    private BigDecimal star;

    private MovieStatus status = MovieStatus.COMING_SOON;
    private AgeRating rating;
    private List<Integer> genreIds = new ArrayList<>();
}
