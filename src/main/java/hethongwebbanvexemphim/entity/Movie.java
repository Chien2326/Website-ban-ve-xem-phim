package hethongwebbanvexemphim.entity;

import hethongwebbanvexemphim.entity.enums.AgeRating;
import hethongwebbanvexemphim.entity.enums.MovieStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer movieId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer duration;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "poster_url", length = 500)
    private String posterUrl;

    @Column(name = "trailer_url", length = 500)
    private String trailerUrl;

    @Column(precision = 4, scale = 2)
    private BigDecimal star;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('COMING_SOON', 'NOW_SHOWING', 'ENDED')")
    @Builder.Default
    private MovieStatus status = MovieStatus.COMING_SOON;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('P', 'K', 'T13', 'T16', 'T18')")
    private AgeRating rating;

    @OneToMany(mappedBy = "movie")
    @Builder.Default
    private List<MovieGenre> movieGenres = new ArrayList<>();
}
