package hethongwebbanvexemphim.controller.admin;

import hethongwebbanvexemphim.entity.enums.BookingStatus;
import hethongwebbanvexemphim.repository.BookingProductRepository;
import hethongwebbanvexemphim.repository.CinemaRepository;
import hethongwebbanvexemphim.repository.GenreRepository;
import hethongwebbanvexemphim.repository.MovieRepository;
import hethongwebbanvexemphim.repository.ProductRepository;
import hethongwebbanvexemphim.repository.RegionRepository;
import hethongwebbanvexemphim.repository.RoleRepository;
import hethongwebbanvexemphim.repository.RoomRepository;
import hethongwebbanvexemphim.repository.SeatRepository;
import hethongwebbanvexemphim.repository.ShowtimeRepository;
import hethongwebbanvexemphim.repository.TicketDetailRepository;
import hethongwebbanvexemphim.repository.UserRepository;
import hethongwebbanvexemphim.service.admin.AdminBookingService;
import hethongwebbanvexemphim.service.admin.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminPagesController {

    private final AdminDashboardService adminDashboardService;
    private final AdminBookingService adminBookingService;
    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;
    private final RegionRepository regionRepository;
    private final CinemaRepository cinemaRepository;
    private final RoomRepository roomRepository;
    private final SeatRepository seatRepository;
    private final BookingProductRepository bookingProductRepository;
    private final ProductRepository productRepository;
    private final TicketDetailRepository ticketDetailRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @GetMapping
    public String dashboard(@RequestParam(defaultValue = "week") String period, Model model) {
        model.addAttribute("stats", adminDashboardService.getDashboard(period));
        return adminView(model, "views/admin/home");
    }

    @GetMapping("/genres")
    public String genres(Model model) {
        model.addAttribute("items", genreRepository.findAll());
        return adminView(model, "views/admin/genres");
    }

    @GetMapping("/movies")
    public String movies(Model model) {
        model.addAttribute("items", movieRepository.findAll());
        return adminView(model, "views/admin/movies");
    }

    @GetMapping("/showtimes")
    public String showtimes(Model model) {
        model.addAttribute("items", showtimeRepository.findAllForAdmin());
        return adminView(model, "views/admin/showtimes");
    }

    @GetMapping("/regions")
    public String regions(Model model) {
        model.addAttribute("items", regionRepository.findAll());
        return adminView(model, "views/admin/regions");
    }

    @GetMapping("/cinemas")
    public String cinemas(Model model) {
        model.addAttribute("items", cinemaRepository.findAllWithRegion());
        return adminView(model, "views/admin/cinemas");
    }

    @GetMapping("/rooms")
    public String rooms(Model model) {
        model.addAttribute("items", roomRepository.findAllWithCinema());
        model.addAttribute("seats", seatRepository.findAll());
        return adminView(model, "views/admin/rooms");
    }

    @GetMapping("/bookings")
    public String bookings(
            @RequestParam(required = false) BookingStatus status,
            @RequestParam(required = false) String keyword,
            Model model) {
        model.addAttribute("items", adminBookingService.searchBookings(status, keyword));
        model.addAttribute("filterStatus", status);
        model.addAttribute("filterKeyword", keyword);
        return adminView(model, "views/admin/bookings");
    }

    @PostMapping("/bookings/{id}/status")
    public String updateBookingStatus(
            @PathVariable Integer id,
            @RequestParam BookingStatus status) {
        adminBookingService.updateStatus(id, status);
        return "redirect:/admin/bookings";
    }

    @GetMapping("/tickets")
    public String tickets(Model model) {
        model.addAttribute("items", ticketDetailRepository.findAllForAdmin());
        return adminView(model, "views/admin/tickets");
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("items", productRepository.findAll());
        model.addAttribute("bookingProducts", bookingProductRepository.findAllForAdmin());
        return adminView(model, "views/admin/products");
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("items", userRepository.findAllWithRole());
        return adminView(model, "views/admin/users");
    }

    @GetMapping("/roles")
    public String roles(Model model) {
        model.addAttribute("items", roleRepository.findAll());
        return adminView(model, "views/admin/roles");
    }

    private static String adminView(Model model, String content) {
        model.addAttribute("content", content);
        return "layouts/layout-admin";
    }
}
