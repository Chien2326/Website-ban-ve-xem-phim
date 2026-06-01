package hethongwebbanvexemphim.repository;

import hethongwebbanvexemphim.entity.MovieGenre;
import hethongwebbanvexemphim.entity.MovieGenreId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, MovieGenreId> {

    List<MovieGenre> findByMovieId(Integer movieId);

    void deleteByMovieId(Integer movieId);

    @Query("""
            SELECT g.genreName FROM MovieGenre mg
            JOIN mg.genre g
            WHERE mg.movieId = :movieId
            ORDER BY g.genreName
            """)
    List<String> findGenreNamesByMovieId(@Param("movieId") Integer movieId);
}
