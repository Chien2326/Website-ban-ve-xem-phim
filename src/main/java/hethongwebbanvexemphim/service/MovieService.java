package hethongwebbanvexemphim.service;

import hethongwebbanvexemphim.dto.mapper.DtoMapper;
import hethongwebbanvexemphim.dto.response.MovieDetailDto;
import hethongwebbanvexemphim.dto.response.MovieSummaryDto;
import hethongwebbanvexemphim.entity.Movie;
import hethongwebbanvexemphim.entity.enums.MovieStatus;
import hethongwebbanvexemphim.repository.MovieGenreRepository;
import hethongwebbanvexemphim.repository.MovieRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieService {

    public static final int HOME_MOVIE_LIMIT = 8;
    private static final String DEFAULT_POSTER =
            "https://images.unsplash.com/photo-1536440136628-849c177e76a1?q=80&w=400";

    private final MovieRepository movieRepository;
    private final MovieGenreRepository movieGenreRepository;

    @Transactional(readOnly = true)
    public List<MovieSummaryDto> getHomeMovies(String tab, Integer regionId) {
        List<Movie> movies = loadMovies(tab, regionId).stream().limit(HOME_MOVIE_LIMIT).toList();
        return DtoMapper.toMovieSummaries(movies, movieGenreRepository::findGenreNamesByMovieId);
    }

    @Transactional(readOnly = true)
    public List<MovieSummaryDto> getMoviesList(String tab, Integer regionId) {
        return DtoMapper.toMovieSummaries(loadMovies(tab, regionId), movieGenreRepository::findGenreNamesByMovieId);
    }

    @Transactional(readOnly = true)
    public List<MovieSummaryDto> getMoviesForBooking() {
        List<Movie> movies = movieRepository.findByStatusInOrderByTitleAsc(
                List.of(MovieStatus.NOW_SHOWING, MovieStatus.COMING_SOON));
        return DtoMapper.toMovieSummaries(movies, movieGenreRepository::findGenreNamesByMovieId);
    }

    @Transactional(readOnly = true)
    public List<MovieSummaryDto> getRelatedMovies(Integer movieId) {
        return getHomeMovies("dang-chieu", null).stream()
                .filter(movie -> !movie.getMovieId().equals(movieId))
                .limit(6)
                .toList();
    }

    @Transactional(readOnly = true)
    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phim"));
    }

    @Transactional(readOnly = true)
    public MovieDetailDto getMovieDetail(Integer id) {
        Movie movie = findById(id);
        return DtoMapper.toDetail(movie, movieGenreRepository.findGenreNamesByMovieId(id));
    }

    public String getMoreMoviesUrl(String tab) {
        return switch (tab) {
            case "sap-chieu" -> "/phim-sap-chieu";
            case "imax" -> "/phim-dang-chieu";
            default -> "/phim-dang-chieu";
        };
    }

    public static String defaultPoster() {
        return DEFAULT_POSTER;
    }

    private List<Movie> loadMovies(String tab, Integer regionId) {
        if ("imax".equals(tab)) {
            List<Movie> imaxMovies = movieRepository.findImaxMovies(regionId);
            if (!imaxMovies.isEmpty()) {
                return imaxMovies;
            }
            return movieRepository.findByStatusOrderByReleaseDateDesc(MovieStatus.NOW_SHOWING);
        }

        MovieStatus status = "sap-chieu".equals(tab) ? MovieStatus.COMING_SOON : MovieStatus.NOW_SHOWING;
        List<Movie> movies = movieRepository.findByStatusOrderByReleaseDateDesc(status);
        if (regionId == null) {
            return movies;
        }
        List<Movie> byRegion = movieRepository.findByStatusAndRegion(status, regionId);
        return byRegion.isEmpty() ? movies : byRegion;
    }
}
