package hethongwebbanvexemphim.repository;

import hethongwebbanvexemphim.entity.Movie;
import hethongwebbanvexemphim.entity.enums.MovieStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByStatus(MovieStatus status);

    List<Movie> findByStatusOrderByReleaseDateDesc(MovieStatus status);

    List<Movie> findByStatusInOrderByTitleAsc(List<MovieStatus> statuses);

    @Query("""
            SELECT DISTINCT m FROM Movie m
            JOIN Showtime st ON st.movie = m
            JOIN st.room r
            JOIN r.cinema c
            WHERE m.status = :status
            AND (:regionId IS NULL OR c.region.regionId = :regionId)
            ORDER BY m.releaseDate DESC
            """)
    List<Movie> findByStatusAndRegion(
            @Param("status") MovieStatus status,
            @Param("regionId") Integer regionId);

    @Query("""
            SELECT DISTINCT m FROM Movie m
            JOIN Showtime st ON st.movie = m
            JOIN st.room r
            JOIN r.cinema c
            WHERE LOWER(st.formatType) LIKE '%imax%'
            AND (:regionId IS NULL OR c.region.regionId = :regionId)
            ORDER BY m.releaseDate DESC
            """)
    List<Movie> findImaxMovies(@Param("regionId") Integer regionId);
}
