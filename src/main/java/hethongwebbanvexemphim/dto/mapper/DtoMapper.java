package hethongwebbanvexemphim.dto.mapper;

import hethongwebbanvexemphim.dto.response.BookingSummaryDto;
import hethongwebbanvexemphim.dto.response.CinemaDto;
import hethongwebbanvexemphim.dto.response.MenuNavDto;
import hethongwebbanvexemphim.dto.response.MovieDetailDto;
import hethongwebbanvexemphim.dto.response.MovieSummaryDto;
import hethongwebbanvexemphim.dto.response.ProductDto;
import hethongwebbanvexemphim.dto.response.RegionDto;
import hethongwebbanvexemphim.dto.response.SeatDto;
import hethongwebbanvexemphim.dto.response.ShowtimeDto;
import hethongwebbanvexemphim.dto.response.UserProfileDto;
import hethongwebbanvexemphim.entity.Booking;
import hethongwebbanvexemphim.entity.Cinema;
import hethongwebbanvexemphim.entity.Menu;
import hethongwebbanvexemphim.entity.MenuSidebar;
import hethongwebbanvexemphim.entity.Movie;
import hethongwebbanvexemphim.entity.Product;
import hethongwebbanvexemphim.entity.Region;
import hethongwebbanvexemphim.entity.Seat;
import hethongwebbanvexemphim.entity.ShowSeat;
import hethongwebbanvexemphim.entity.Showtime;
import hethongwebbanvexemphim.entity.TicketDetail;
import hethongwebbanvexemphim.entity.User;
import hethongwebbanvexemphim.entity.enums.AgeRating;
import hethongwebbanvexemphim.entity.enums.Gender;
import hethongwebbanvexemphim.entity.enums.MovieStatus;
import hethongwebbanvexemphim.entity.enums.SeatType;
import hethongwebbanvexemphim.entity.enums.ShowSeatStatus;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class DtoMapper {

    private DtoMapper() {
    }

    public static MovieSummaryDto toSummary(Movie movie, List<String> genres) {
        String ratingLabel = ratingLabel(movie.getRating());
        return MovieSummaryDto.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .posterUrl(movie.getPosterUrl())
                .rating(ratingLabel)
                .ratingBadgeClass(ratingBadgeClass(ratingLabel))
                .star(movie.getStar())
                .statusLabel(statusLabel(movie.getStatus()))
                .durationMinutes(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .detailUrl("/phim/" + movie.getMovieId())
                .genres(genres == null ? List.of() : genres)
                .build();
    }

    public static List<MovieSummaryDto> toMovieSummaries(Collection<Movie> movies, java.util.function.Function<Integer, List<String>> genreLoader) {
        return movies.stream()
                .map(movie -> toSummary(movie, genreLoader.apply(movie.getMovieId())))
                .toList();
    }

    public static MovieDetailDto toDetail(Movie movie, List<String> genres) {
        String ratingLabel = ratingLabel(movie.getRating());
        return MovieDetailDto.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .durationMinutes(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .posterUrl(movie.getPosterUrl())
                .trailerUrl(movie.getTrailerUrl())
                .star(movie.getStar())
                .statusLabel(statusLabel(movie.getStatus()))
                .rating(ratingLabel)
                .ratingBadgeClass(ratingBadgeClass(ratingLabel))
                .genres(genres == null ? List.of() : genres)
                .build();
    }

    public static ShowtimeDto toShowtime(Showtime showtime) {
        return ShowtimeDto.builder()
                .showtimeId(showtime.getShowtimeId())
                .movieId(showtime.getMovie().getMovieId())
                .movieTitle(showtime.getMovie().getTitle())
                .cinemaId(showtime.getRoom().getCinema().getCinemaId())
                .roomId(showtime.getRoom().getRoomId())
                .roomName(showtime.getRoom().getName())
                .cinemaName(showtime.getRoom().getCinema().getName())
                .startTime(showtime.getStartTime())
                .endTime(showtime.getEndTime())
                .formatType(showtime.getFormatType())
                .priceBase(showtime.getPriceBase())
                .build();
    }

    public static List<ShowtimeDto> toShowtimes(Collection<Showtime> showtimes) {
        return showtimes.stream().map(DtoMapper::toShowtime).toList();
    }

    public static SeatDto fromShowSeat(ShowSeat showSeat) {
        Seat seat = showSeat.getSeat();
        ShowSeatStatus status = showSeat.getStatus();
        boolean unavailable = status != ShowSeatStatus.Available;
        return SeatDto.builder()
                .showSeatId(showSeat.getShowSeatId())
                .seatId(seat.getSeatId())
                .rowChar(seat.getRowChar())
                .seatNumber(seat.getSeatNumber())
                .seatType(seat.getSeatType())
                .status(status)
                .price(showSeat.getPrice())
                .unavailable(unavailable)
                .cssClass(seatCssClass(unavailable, seat.getSeatType()))
                .build();
    }

    private static String seatCssClass(boolean unavailable, SeatType seatType) {
        if (unavailable) {
            return "seat-sold bg-gray-300 border-gray-300 text-gray-500 cursor-not-allowed";
        }
        if (seatType == SeatType.VIP) {
            return "bg-white border-yellow-500 text-gray-600 cursor-pointer hover:bg-yellow-50";
        }
        return "bg-white border-gray-300 text-gray-600 cursor-pointer hover:bg-gray-100";
    }

    public static List<SeatDto> fromShowSeats(Collection<ShowSeat> showSeats) {
        return showSeats.stream().map(DtoMapper::fromShowSeat).toList();
    }

    public static ProductDto toProduct(Product product) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .productType(product.getProductType())
                .typeLabel(product.getProductType() == null ? null : product.getProductType().getDisplayLabel())
                .build();
    }

    public static List<ProductDto> toProducts(Collection<Product> products) {
        return products.stream().map(DtoMapper::toProduct).toList();
    }

    public static RegionDto toRegion(Region region) {
        return RegionDto.builder()
                .regionId(region.getRegionId())
                .regionName(region.getRegionName())
                .build();
    }

    public static List<RegionDto> toRegions(Collection<Region> regions) {
        return regions.stream().map(DtoMapper::toRegion).toList();
    }

    public static CinemaDto toCinema(Cinema cinema) {
        return CinemaDto.builder()
                .cinemaId(cinema.getCinemaId())
                .regionId(cinema.getRegion().getRegionId())
                .regionName(cinema.getRegion().getRegionName())
                .name(cinema.getName())
                .address(cinema.getAddress())
                .build();
    }

    public static List<CinemaDto> toCinemas(Collection<Cinema> cinemas) {
        return cinemas.stream().map(DtoMapper::toCinema).toList();
    }

    public static UserProfileDto toProfile(User user) {
        return UserProfileDto.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .genderLabel(genderLabel(user.getGender()))
                .birthday(user.getBirthday())
                .roleName(user.getRole().getRoleName())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static BookingSummaryDto toBookingSummary(Booking booking, List<TicketDetail> tickets) {
        Showtime showtime = null;
        if (tickets != null && !tickets.isEmpty()) {
            showtime = tickets.getFirst().getShowSeat().getShowtime();
        }
        return BookingSummaryDto.builder()
                .bookingId(booking.getBookingId())
                .movieTitle(showtime != null ? showtime.getMovie().getTitle() : "—")
                .cinemaName(showtime != null ? showtime.getRoom().getCinema().getName() : "—")
                .showtimeStart(showtime != null ? showtime.getStartTime() : null)
                .totalAmount(booking.getTotalAmount())
                .bookingTime(booking.getBookingTime())
                .paymentMethod(booking.getPaymentMethod())
                .status(booking.getStatus())
                .ticketCount(tickets == null ? 0 : tickets.size())
                .build();
    }

    public static MenuNavDto fromMenu(Menu menu) {
        return MenuNavDto.builder()
                .id(menu.getMenuId())
                .label(menu.getMenuName())
                .link(resolveMenuLink(menu))
                .controllerName(menu.getControllerName())
                .actionName(menu.getActionName())
                .parentId(menu.getParentId())
                .order(menu.getMenuOrder())
                .build();
    }

    public static MenuNavDto fromMenuSidebar(MenuSidebar item) {
        return MenuNavDto.builder()
                .id(item.getAdminMenuId())
                .label(item.getItemName())
                .link(buildAdminLink(item))
                .controllerName(item.getControllerName())
                .actionName(item.getActionName())
                .parentId(item.getParentLevel())
                .order(item.getItemOrder())
                .icon(item.getIcon())
                .build();
    }

    public static List<MenuNavDto> fromMenus(Collection<Menu> menus) {
        return menus.stream().map(DtoMapper::fromMenu).collect(Collectors.toList());
    }

    public static List<MenuNavDto> fromMenuSidebars(Collection<MenuSidebar> items) {
        return items.stream().map(DtoMapper::fromMenuSidebar).collect(Collectors.toList());
    }

    private static String ratingLabel(AgeRating rating) {
        return rating == null ? null : rating.name();
    }

    private static String statusLabel(MovieStatus status) {
        return status == null ? null : status.getDisplayLabel();
    }

    private static String ratingBadgeClass(String rating) {
        if (rating == null || rating.isBlank()) {
            return "bg-gray-500";
        }
        return switch (rating.toUpperCase()) {
            case "T18" -> "bg-red-600";
            case "T16" -> "bg-orange-500";
            case "P", "K" -> "bg-green-600";
            default -> "bg-blue-600";
        };
    }

    private static String genderLabel(Gender gender) {
        if (gender == null) {
            return null;
        }
        return switch (gender) {
            case MALE -> "Nam";
            case FEMALE -> "Nữ";
            case OTHER -> "Khác";
        };
    }

    private static String buildAdminLink(MenuSidebar item) {
        if (item.getControllerName() == null || item.getActionName() == null) {
            return "#";
        }
        return "/admin/" + item.getControllerName().toLowerCase() + "/" + item.getActionName().toLowerCase();
    }

    private static String resolveMenuLink(Menu menu) {
        if (menu.getLink() != null && !menu.getLink().isBlank()) {
            String link = menu.getLink().trim();
            return link.startsWith("/") ? link : "/" + link;
        }
        if (menu.getControllerName() != null && menu.getActionName() != null) {
            return "/" + menu.getControllerName().toLowerCase() + "/" + menu.getActionName().toLowerCase();
        }
        return "#";
    }
}
