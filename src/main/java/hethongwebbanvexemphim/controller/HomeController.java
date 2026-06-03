package hethongwebbanvexemphim.controller;

import hethongwebbanvexemphim.dto.mapper.DtoMapper;
import hethongwebbanvexemphim.dto.response.MomoPaymentResponse;
import hethongwebbanvexemphim.entity.User;
import hethongwebbanvexemphim.entity.enums.PaymentMethod;
import hethongwebbanvexemphim.repository.RegionRepository;
import hethongwebbanvexemphim.service.BookingService;
import hethongwebbanvexemphim.service.HomePageService;
import hethongwebbanvexemphim.service.MomoPaymentService;
import hethongwebbanvexemphim.service.MovieDetailService;
import hethongwebbanvexemphim.service.MovieService;
import hethongwebbanvexemphim.service.ProductService;
import hethongwebbanvexemphim.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MovieService movieService;
    private final MovieDetailService movieDetailService;
    private final BookingService bookingService;
    private final ProductService productService;
    private final HomePageService homePageService;
    private final RegionRepository regionRepository;
    private final MomoPaymentService momoPaymentService;
    private final UserService userService;

    @GetMapping("/")
    public String home(
            @RequestParam(defaultValue = "dang-chieu") String tab,
            @RequestParam(required = false) Integer regionId,
            @RequestHeader(value = "HX-Request", required = false) String hxRequest,
            Model model) {
        addHomeMovieModel(model, tab, regionId);
        if ("true".equals(hxRequest)) {
            return "views/home :: movie-content";
        }
        model.addAttribute("content", "views/home");
        return "layouts/layout";
    }

    @GetMapping("/phim/{id}")
    public String chiTietPhim(
            @PathVariable Integer id,
            @RequestParam(required = false) Integer regionId,
            @RequestParam(required = false) Integer cinemaId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {
        List<LocalDate> scheduleDates = movieDetailService.getScheduleDates(id, regionId);
        LocalDate selectedDate = resolveScheduleDate(date, scheduleDates);

        model.addAttribute("movie", movieService.getMovieDetail(id));
        model.addAttribute("schedule", movieDetailService.getSchedule(id, regionId, cinemaId, selectedDate));
        model.addAttribute("scheduleDates", scheduleDates);
        model.addAttribute("regions", DtoMapper.toRegions(regionRepository.findAll()));
        model.addAttribute("cinemas", homePageService.getBookingCinemas(regionId));
        model.addAttribute("relatedMovies", movieService.getRelatedMovies(id));
        model.addAttribute("selectedRegionId", regionId);
        model.addAttribute("selectedCinemaId", cinemaId);
        model.addAttribute("selectedDate", selectedDate);
        model.addAttribute("defaultPoster", MovieService.defaultPoster());
        model.addAttribute("content", "views/chi-tiet-phim");
        return "layouts/layout";
    }

    private static LocalDate resolveScheduleDate(LocalDate requested, List<LocalDate> availableDates) {
        if (requested != null) {
            return requested;
        }
        if (availableDates == null || availableDates.isEmpty()) {
            return LocalDate.now();
        }
        LocalDate today = LocalDate.now();
        return availableDates.stream()
                .filter(d -> !d.isBefore(today))
                .findFirst()
                .orElse(availableDates.getFirst());
    }

    private void addHomeMovieModel(Model model, String tab, Integer regionId) {
        var bookingMovies = movieService.getMoviesForBooking();
        var bookingShowtimes = homePageService.getBookingShowtimes(regionId);
        
        System.out.println("=== DEBUG HOME ===");
        System.out.println("bookingMovies size: " + bookingMovies.size());
        bookingMovies.forEach(m -> System.out.println(" - Movie: " + m.getMovieId() + " - " + m.getTitle()));
        System.out.println("bookingShowtimes size: " + bookingShowtimes.size());
        bookingShowtimes.forEach(st -> System.out.println(" - Showtime: " + st.getShowtimeId() + " - MovieId: " + st.getMovieId()));
        
        model.addAttribute("movies", movieService.getHomeMovies(tab, regionId));
        model.addAttribute("bookingMovies", bookingMovies);
        model.addAttribute("bookingCinemas", homePageService.getBookingCinemas(regionId));
        model.addAttribute("bookingDates", homePageService.getBookingDates(regionId));
        model.addAttribute("bookingShowtimes", bookingShowtimes);
        model.addAttribute("regions", DtoMapper.toRegions(regionRepository.findAll()));
        model.addAttribute("activeTab", tab);
        model.addAttribute("selectedRegionId", regionId);
        model.addAttribute("moreMoviesUrl", movieService.getMoreMoviesUrl(tab));
        model.addAttribute("defaultPoster", MovieService.defaultPoster());
    }

    @GetMapping("/dat-ve")
    public String datVe(@RequestParam(required = false) Integer showtimeId, Model model) {
        if (showtimeId != null) {
            model.addAttribute("booking", bookingService.getBookingPage(showtimeId));
        }
        model.addAttribute("defaultPoster", MovieService.defaultPoster());
        model.addAttribute("content", "views/dat-ve");
        return "layouts/layout";
    }

    @GetMapping("/thuc-an")
    public String thucAn(
            @RequestParam(required = false) Integer showtimeId,
            @RequestParam(required = false) String showSeatIds,
            Model model) {
        model.addAttribute("products", productService.getAvailableProducts());
        model.addAttribute("defaultProductImage", ProductService.defaultImage());
        if (showtimeId != null) {
            model.addAttribute("checkout", bookingService.getCheckoutSummary(
                    showtimeId,
                    BookingService.parseShowSeatIds(showSeatIds),
                    null,
                    null));
            model.addAttribute("showSeatIds", showSeatIds);
        }
        model.addAttribute("defaultPoster", MovieService.defaultPoster());
        model.addAttribute("content", "views/thuc-an");
        return "layouts/layout";
    }

    @GetMapping("/thanh-toan")
    public String thanhToan(
            @RequestParam(required = false) Integer showtimeId,
            @RequestParam(required = false) String showSeatIds,
            @RequestParam(required = false) List<Integer> productIds,
            @RequestParam(required = false) List<Integer> quantities,
            Model model) {
        addCheckoutPageModel(model, showtimeId, showSeatIds, productIds, quantities, null);
        model.addAttribute("content", "views/thanh-toan");
        return "layouts/layout";
    }

    @GetMapping("/xac-nhan")
    public String xacNhan(
            @RequestParam(required = false) Integer showtimeId,
            @RequestParam(required = false) String showSeatIds,
            @RequestParam(required = false) List<Integer> productIds,
            @RequestParam(required = false) List<Integer> quantities,
            @RequestParam(required = false) PaymentMethod paymentMethod,
            Model model) {
        addCheckoutPageModel(model, showtimeId, showSeatIds, productIds, quantities, paymentMethod);
        model.addAttribute("content", "views/xac-nhan");
        return "layouts/layout";
    }

    private void addCheckoutPageModel(
            Model model,
            Integer showtimeId,
            String showSeatIds,
            List<Integer> productIds,
            List<Integer> quantities,
            PaymentMethod paymentMethod) {
        model.addAttribute("defaultPoster", MovieService.defaultPoster());
        if (showtimeId == null || showSeatIds == null || showSeatIds.isBlank()) {
            return;
        }
        try {
            var checkout = bookingService.getCheckoutSummary(
                    showtimeId,
                    BookingService.parseShowSeatIds(showSeatIds),
                    productIds,
                    quantities);
            model.addAttribute("checkout", checkout);
            model.addAttribute("showSeatIds", showSeatIds);
            model.addAttribute("productIds", productIds != null ? productIds : List.of());
            model.addAttribute("quantities", quantities != null ? quantities : List.of());
            model.addAttribute("backUrl", bookingService.buildThucAnUrl(showtimeId, showSeatIds));
            model.addAttribute("thanhToanUrl", bookingService.buildThanhToanUrl(
                    showtimeId, showSeatIds, productIds, quantities));
            model.addAttribute("paymentMethod", paymentMethod);
            if (paymentMethod != null) {
                model.addAttribute("paymentMethodLabel", BookingService.paymentMethodLabel(paymentMethod));
            }
        } catch (IllegalArgumentException ex) {
            model.addAttribute("checkoutError", ex.getMessage());
        }
    }

    @GetMapping("/tai-khoan")
    public String taiKhoan(Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("user", currentUser);
        model.addAttribute("content", "views/tai-khoan");
        return "layouts/layout";
    }

    @GetMapping("/gioi-thieu")
    public String gioiThieu(Model model) {
        model.addAttribute("content", "views/gioi-thieu");
        return "layouts/layout";
    }

    @GetMapping("/lien-he")
    public String lienHe(Model model) {
        model.addAttribute("content", "views/lien-he");
        return "layouts/layout";
    }

    @GetMapping("/phim-dang-chieu")
    public String phimDangChieu(
            @RequestParam(required = false) Integer regionId,
            @RequestHeader(value = "HX-Request", required = false) String hxRequest,
            Model model) {
        addMovieListModel(model, "dang-chieu", regionId);
        if ("true".equals(hxRequest)) {
            return "fragments/movie-list-page :: movie-content";
        }
        model.addAttribute("content", "views/phim-dang-chieu");
        return "layouts/layout";
    }

    @GetMapping("/phim-sap-chieu")
    public String phimSapChieu(
            @RequestParam(required = false) Integer regionId,
            @RequestHeader(value = "HX-Request", required = false) String hxRequest,
            Model model) {
        addMovieListModel(model, "sap-chieu", regionId);
        if ("true".equals(hxRequest)) {
            return "fragments/movie-list-page :: movie-content";
        }
        model.addAttribute("content", "views/phim-sap-chieu");
        return "layouts/layout";
    }

    private void addMovieListModel(Model model, String tab, Integer regionId) {
        model.addAttribute("movies", movieService.getMoviesList(tab, regionId));
        model.addAttribute("regions", DtoMapper.toRegions(regionRepository.findAll()));
        model.addAttribute("selectedRegionId", regionId);
        model.addAttribute("defaultPoster", MovieService.defaultPoster());
    }

    @PostMapping("/payment/initiate")
    public RedirectView initiatePayment(
            @RequestParam Integer showtimeId,
            @RequestParam String showSeatIds,
            @RequestParam(required = false) List<Integer> productIds,
            @RequestParam(required = false) List<Integer> quantities,
            @RequestParam PaymentMethod paymentMethod,
            HttpSession session
    ) {
        System.out.println("=== PAYMENT INITIATE DEBUG START ===");
        System.out.println("showtimeId = " + showtimeId);
        System.out.println("showSeatIds = " + showSeatIds);
        System.out.println("productIds = " + productIds);
        System.out.println("quantities = " + quantities);
        System.out.println("paymentMethod = " + paymentMethod);

        try {
            var checkout = bookingService.getCheckoutSummary(
                    showtimeId,
                    BookingService.parseShowSeatIds(showSeatIds),
                    productIds,
                    quantities
            );

            System.out.println("checkout.getGrandTotal() = " + checkout.getGrandTotal());

            // Store checkout info in session for later use
            session.setAttribute("checkout", checkout);
            session.setAttribute("showtimeId", showtimeId);
            session.setAttribute("showSeatIds", showSeatIds);
            session.setAttribute("productIds", productIds);
            session.setAttribute("quantities", quantities);
            session.setAttribute("paymentMethod", paymentMethod);

            if (paymentMethod == PaymentMethod.Momo) {
                // --- SIMULATE PAYMENT SUCCESS (FOR PROJECT DEMO) ---
                // Tạm bỏ qua gọi MoMo API thật để hệ thống chạy được
                String fakeOrderId = "ORD-" + System.currentTimeMillis();
                // Generate unique random QR code content using UUID to ensure no duplicates
                String uniqueTicketCode = "C9-" + UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();
                Long amount = checkout.getGrandTotal().longValue();

                System.out.println("=== SIMULATING PAYMENT SUCCESS ===");
                System.out.println("Order ID = " + fakeOrderId);
                System.out.println("Unique Ticket Code = " + uniqueTicketCode);
                System.out.println("Amount = " + amount);

                // Store checkout data to session to retrieve on success page
                session.setAttribute("uniqueTicketCode", uniqueTicketCode);

                // Redirect to success page directly
                return new RedirectView("/payment/success?partnerCode=MOMO&orderId=" + fakeOrderId + "&requestId=REQ-123&amount=" + amount + "&orderInfo=Thanh+toan+thanh+cong&resultCode=0&message=Thanh+toan+thanh+cong");
            }

            // TODO: Add other payment methods (ZaloPay, VNPay, ATM)
            return new RedirectView("/payment/failure?message=Phương thức thanh toán chưa được hỗ trợ");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("=== PAYMENT INITIATE EXCEPTION: " + e.getMessage());
            return new RedirectView("/payment/failure?message=Lỗi hệ thống: " + e.getMessage());
        }
    }

    @GetMapping("/payment/success")
    public String paymentSuccess(
            @RequestParam(required = false) String partnerCode,
            @RequestParam(required = false) String orderId,
            @RequestParam(required = false) String requestId,
            @RequestParam(required = false) Long amount,
            @RequestParam(required = false) String orderInfo,
            @RequestParam(required = false) String orderType,
            @RequestParam(required = false) Long transId,
            @RequestParam(required = false) Integer resultCode,
            @RequestParam(required = false) String message,
            @RequestParam(required = false) String payType,
            @RequestParam(required = false) Long responseTime,
            @RequestParam(required = false) String extraData,
            @RequestParam(required = false) String signature,
            Model model
    ) {
        if (resultCode == 0) {
            model.addAttribute("message", "Thanh toán thành công!");
            model.addAttribute("orderId", orderId);
            model.addAttribute("amount", amount);
            model.addAttribute("content", "views/payment-success");
        } else {
            model.addAttribute("message", message);
            model.addAttribute("content", "views/payment-failure");
        }
        return "layouts/layout";
    }

    @GetMapping("/payment/failure")
    public String paymentFailure(
            @RequestParam(required = false) String message,
            Model model
    ) {
        model.addAttribute("message", message != null ? message : "Thanh toán thất bại!");
        model.addAttribute("content", "views/payment-failure");
        return "layouts/layout";
    }
}
