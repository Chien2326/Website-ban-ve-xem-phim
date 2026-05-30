package hethongwebbanvexemphim.config;

import hethongwebbanvexemphim.entity.Cinema;
import hethongwebbanvexemphim.entity.Genre;
import hethongwebbanvexemphim.entity.Movie;
import hethongwebbanvexemphim.entity.MovieGenre;
import hethongwebbanvexemphim.entity.Region;
import hethongwebbanvexemphim.entity.Room;
import hethongwebbanvexemphim.entity.Seat;
import hethongwebbanvexemphim.entity.ShowSeat;
import hethongwebbanvexemphim.entity.Showtime;
import hethongwebbanvexemphim.entity.enums.AgeRating;
import hethongwebbanvexemphim.entity.enums.MovieStatus;
import hethongwebbanvexemphim.entity.enums.ShowSeatStatus;
import hethongwebbanvexemphim.repository.CinemaRepository;
import hethongwebbanvexemphim.repository.GenreRepository;
import hethongwebbanvexemphim.repository.MovieGenreRepository;
import hethongwebbanvexemphim.repository.MovieRepository;
import hethongwebbanvexemphim.repository.RegionRepository;
import hethongwebbanvexemphim.repository.RoomRepository;
import hethongwebbanvexemphim.repository.SeatRepository;
import hethongwebbanvexemphim.repository.ShowSeatRepository;
import hethongwebbanvexemphim.repository.ShowtimeRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieDataInitializer implements ApplicationRunner {

    private final MovieRepository movieRepository;
    private final RegionRepository regionRepository;
    private final CinemaRepository cinemaRepository;
    private final RoomRepository roomRepository;
    private final SeatRepository seatRepository;
    private final ShowtimeRepository showtimeRepository;
    private final ShowSeatRepository showSeatRepository;
    private final GenreRepository genreRepository;
    private final MovieGenreRepository movieGenreRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (movieRepository.count() > 0) {
            return;
        }

        Genre action = genreRepository.save(Genre.builder().genreName("Hành động").build());
        Genre horror = genreRepository.save(Genre.builder().genreName("Kinh dị").build());
        Genre scifi = genreRepository.save(Genre.builder().genreName("Khoa học viễn tưởng").build());
        Genre family = genreRepository.save(Genre.builder().genreName("Gia đình").build());

        Region hanoi = regionRepository.save(Region.builder().regionName("Hà Nội").build());
        Region hcm = regionRepository.save(Region.builder().regionName("TP. Hồ Chí Minh").build());

        Cinema galaxyDu = cinemaRepository.save(Cinema.builder()
                .region(hanoi)
                .name("Galaxy Nguyễn Du")
                .address("116 Nguyễn Du, Hai Bà Trưng, Hà Nội")
                .build());
        Cinema galaxyTb = cinemaRepository.save(Cinema.builder()
                .region(hcm)
                .name("Galaxy Tân Bình")
                .address("246 Minh Phụng, Tân Bình, TP.HCM")
                .build());

        Room room1 = roomRepository.save(Room.builder().cinema(galaxyDu).name("Phòng 01").totalSeats(8).build());
        Room roomImax = roomRepository.save(Room.builder().cinema(galaxyTb).name("IMAX 01").totalSeats(8).build());

        List<Seat> seatsRoom1 = createSeats(room1, 4);
        List<Seat> seatsImax = createSeats(roomImax, 4);

        Movie m1 = saveMovie("Làng Trùng Tang", MovieStatus.NOW_SHOWING, 107, AgeRating.T18,
                new BigDecimal("8.20"), "https://images.unsplash.com/photo-1534447677768-be436bb09401?q=80&w=400",
                LocalDate.of(2026, 5, 22), List.of(horror));
        Movie m2 = saveMovie("Bài Trùng Phá Án", MovieStatus.NOW_SHOWING, 128, AgeRating.T16,
                new BigDecimal("8.60"), "https://images.unsplash.com/photo-1485846234645-a62644f84728?q=80&w=400",
                LocalDate.of(2026, 4, 10), List.of(action));
        Movie m3 = saveMovie("Star Wars: Mandalorian Và Grogu", MovieStatus.NOW_SHOWING, 115, AgeRating.T13,
                new BigDecimal("8.30"), "https://images.unsplash.com/photo-1559893088-c0787ebfc084?q=80&w=400",
                LocalDate.of(2026, 3, 1), List.of(scifi, action));
        Movie m4 = saveMovie("Làng Quỷ Quái", MovieStatus.COMING_SOON, 98, AgeRating.T16,
                new BigDecimal("7.50"), "https://images.unsplash.com/photo-1509248961158-e54f6934749c?q=80&w=400",
                LocalDate.of(2026, 6, 15), List.of(horror));
        Movie m5 = saveMovie("Doraemon: Nobita Và Lâu Đài Dưới Đáy Biển", MovieStatus.COMING_SOON, 110, AgeRating.P,
                new BigDecimal("8.00"), "https://images.unsplash.com/photo-1608889174639-414d9fde9bfd?q=80&w=400",
                LocalDate.of(2026, 7, 1), List.of(family));

        Showtime st1 = saveShowtime(m1, room1, "2D Phụ Đề", LocalDateTime.of(2026, 5, 29, 19, 45));
        Showtime st2 = saveShowtime(m2, room1, "2D Phụ Đề", LocalDateTime.of(2026, 5, 29, 21, 0));
        Showtime st3 = saveShowtime(m3, roomImax, "IMAX 3D", LocalDateTime.of(2026, 5, 30, 20, 0));
        Showtime st4 = saveShowtime(m1, roomImax, "IMAX 2D", LocalDateTime.of(2026, 5, 30, 18, 30));

        createShowSeats(st1, seatsRoom1, new BigDecimal("85000"));
        createShowSeats(st2, seatsRoom1, new BigDecimal("90000"));
        createShowSeats(st3, seatsImax, new BigDecimal("120000"));
        createShowSeats(st4, seatsImax, new BigDecimal("110000"));
    }

    private List<Seat> createSeats(Room room, int count) {
        return java.util.stream.IntStream.rangeClosed(1, count)
                .mapToObj(i -> seatRepository.save(Seat.builder()
                        .room(room)
                        .rowChar("A")
                        .seatNumber(i)
                        .build()))
                .toList();
    }

    private Movie saveMovie(
            String title,
            MovieStatus status,
            int duration,
            AgeRating rating,
            BigDecimal star,
            String posterUrl,
            LocalDate releaseDate,
            List<Genre> genres) {
        Movie movie = movieRepository.save(Movie.builder()
                .title(title)
                .description("Phim " + title)
                .duration(duration)
                .releaseDate(releaseDate)
                .posterUrl(posterUrl)
                .status(status)
                .rating(rating)
                .star(star)
                .build());
        for (Genre genre : genres) {
            movieGenreRepository.save(MovieGenre.builder()
                    .movieId(movie.getMovieId())
                    .genreId(genre.getGenreId())
                    .build());
        }
        return movie;
    }

    private Showtime saveShowtime(Movie movie, Room room, String format, LocalDateTime start) {
        return showtimeRepository.save(Showtime.builder()
                .movie(movie)
                .room(room)
                .startTime(start)
                .endTime(start.plusMinutes(movie.getDuration()))
                .formatType(format)
                .priceBase(new BigDecimal("85000"))
                .build());
    }

    private void createShowSeats(Showtime showtime, List<Seat> seats, BigDecimal price) {
        for (Seat seat : seats) {
            showSeatRepository.save(ShowSeat.builder()
                    .showtime(showtime)
                    .seat(seat)
                    .status(ShowSeatStatus.Available)
                    .price(price)
                    .build());
        }
    }
}
