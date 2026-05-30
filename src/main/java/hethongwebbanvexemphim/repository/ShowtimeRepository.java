package hethongwebbanvexemphim.repository;

import hethongwebbanvexemphim.entity.Showtime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {

    List<Showtime> findByMovieMovieId(Integer movieId);

    @Query("""
            SELECT st FROM Showtime st
            JOIN FETCH st.movie
            JOIN FETCH st.room r
            JOIN FETCH r.cinema
            WHERE st.showtimeId = :id
            """)
    Optional<Showtime> findDetailById(@Param("id") Integer id);

    @Query("""
            SELECT st FROM Showtime st
            JOIN FETCH st.room r
            WHERE st.movie.movieId = :movieId
            AND r.cinema.cinemaId = :cinemaId
            AND st.startTime >= :from
            ORDER BY st.startTime ASC
            """)
    List<Showtime> findUpcomingByMovieAndCinema(
            @Param("movieId") Integer movieId,
            @Param("cinemaId") Integer cinemaId,
            @Param("from") LocalDateTime from);

    @Query("""
            SELECT st FROM Showtime st
            JOIN FETCH st.movie
            JOIN FETCH st.room r
            JOIN FETCH r.cinema c
            WHERE st.movie.movieId = :movieId
            AND st.startTime >= :from
            AND (:regionId IS NULL OR c.region.regionId = :regionId)
            ORDER BY c.name ASC, st.formatType ASC, st.startTime ASC
            """)
    List<Showtime> findUpcomingByMovie(
            @Param("movieId") Integer movieId,
            @Param("from") LocalDateTime from,
            @Param("regionId") Integer regionId);

    List<Showtime> findByStartTimeBetween(LocalDateTime from, LocalDateTime to);

    @Query("""
            SELECT st FROM Showtime st
            JOIN FETCH st.movie
            JOIN FETCH st.room r
            JOIN FETCH r.cinema c
            WHERE st.startTime >= :from
            AND (:regionId IS NULL OR c.region.regionId = :regionId)
            ORDER BY st.startTime ASC
            """)
    List<Showtime> findUpcoming(
            @Param("from") LocalDateTime from,
            @Param("regionId") Integer regionId,
            Pageable pageable);

    @Query("""
            SELECT st FROM Showtime st
            JOIN FETCH st.movie
            JOIN FETCH st.room r
            JOIN FETCH r.cinema c
            ORDER BY st.startTime DESC
            """)
    List<Showtime> findAllForAdmin();
}
