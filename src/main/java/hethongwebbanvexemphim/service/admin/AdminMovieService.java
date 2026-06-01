package hethongwebbanvexemphim.service.admin;

import hethongwebbanvexemphim.dto.admin.MovieForm;
import hethongwebbanvexemphim.entity.Genre;
import hethongwebbanvexemphim.entity.Movie;
import hethongwebbanvexemphim.entity.MovieGenre;
import hethongwebbanvexemphim.repository.GenreRepository;
import hethongwebbanvexemphim.repository.MovieGenreRepository;
import hethongwebbanvexemphim.repository.MovieRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminMovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieGenreRepository movieGenreRepository;

    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    @Transactional(readOnly = true)
    public MovieForm getForm(Integer movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phim"));
        MovieForm form = new MovieForm();
        form.setMovieId(movie.getMovieId());
        form.setTitle(movie.getTitle());
        form.setDescription(movie.getDescription());
        form.setDuration(movie.getDuration());
        form.setReleaseDate(movie.getReleaseDate());
        form.setPosterUrl(movie.getPosterUrl());
        form.setTrailerUrl(movie.getTrailerUrl());
        form.setStar(movie.getStar());
        form.setStatus(movie.getStatus());
        form.setRating(movie.getRating());
        form.setGenreIds(movieGenreRepository.findByMovieId(movieId).stream()
                .map(MovieGenre::getGenreId)
                .toList());
        return form;
    }

    @Transactional
    public Movie save(MovieForm form) {
        validate(form);
        Movie movie;
        if (form.getMovieId() == null) {
            movie = new Movie();
        } else {
            movie = movieRepository.findById(form.getMovieId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phim"));
        }
        applyForm(movie, form);
        movie = movieRepository.save(movie);
        syncGenres(movie.getMovieId(), form.getGenreIds());
        return movie;
    }

    @Transactional
    public void delete(Integer movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new IllegalArgumentException("Không tìm thấy phim");
        }
        movieGenreRepository.deleteByMovieId(movieId);
        movieRepository.deleteById(movieId);
    }

    private static void validate(MovieForm form) {
        if (form.getTitle() == null || form.getTitle().isBlank()) {
            throw new IllegalArgumentException("Tên phim không được để trống");
        }
        if (form.getDuration() == null || form.getDuration() <= 0) {
            throw new IllegalArgumentException("Thời lượng phim phải lớn hơn 0");
        }
    }

    private static void applyForm(Movie movie, MovieForm form) {
        movie.setTitle(form.getTitle().trim());
        movie.setDescription(form.getDescription());
        movie.setDuration(form.getDuration());
        movie.setReleaseDate(form.getReleaseDate());
        movie.setPosterUrl(trimToNull(form.getPosterUrl()));
        movie.setTrailerUrl(trimToNull(form.getTrailerUrl()));
        movie.setStar(form.getStar());
        movie.setStatus(form.getStatus() != null ? form.getStatus() : movie.getStatus());
        movie.setRating(form.getRating());
    }

    private void syncGenres(Integer movieId, List<Integer> genreIds) {
        movieGenreRepository.deleteByMovieId(movieId);
        if (genreIds == null || genreIds.isEmpty()) {
            return;
        }
        for (Integer genreId : genreIds) {
            if (genreId == null) {
                continue;
            }
            movieGenreRepository.save(MovieGenre.builder()
                    .movieId(movieId)
                    .genreId(genreId)
                    .build());
        }
    }

    private static String trimToNull(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }
}
