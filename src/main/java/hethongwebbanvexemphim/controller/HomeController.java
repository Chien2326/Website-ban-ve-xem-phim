package hethongwebbanvexemphim.controller;

import hethongwebbanvexemphim.dto.mapper.DtoMapper;
import hethongwebbanvexemphim.dto.response.MomoPaymentResponse;
import hethongwebbanvexemphim.entity.enums.PaymentMethod;
import hethongwebbanvexemphim.repository.RegionRepository;
import hethongwebbanvexemphim.service.BookingService;
import hethongwebbanvexemphim.service.HomePageService;
import hethongwebbanvexemphim.service.MomoPaymentService;
import hethongwebbanvexemphim.service.MovieDetailService;
import hethongwebbanvexemphim.service.MovieService;
import hethongwebbanvexemphim.service.ProductService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/")
    public String home(
            @RequestParam(defaultValue = "dang-chieu") String tab,
            @RequestParam(required = false) Integer regionId,
            Model model) {
        addHomeMovieModel(model, tab, regionId);
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
        model.addAttribute("movies", movieService.getHomeMovies(tab, regionId));
        model.addAttribute("bookingMovies", movieService.getMoviesForBooking());
        model.addAttribute("bookingCinemas", homePageService.getBookingCinemas(regionId));
        model.addAttribute("bookingDates", homePageService.getBookingDates(regionId));
        model.addAttribute("bookingShowtimes", homePageService.getBookingShowtimes(regionId));
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
            Model model) {
        addMovieListModel(model, "dang-chieu", regionId);
        model.addAttribute("content", "views/phim-dang-chieu");
        return "layouts/layout";
    }

    @GetMapping("/phim-sap-chieu")
    public String phimSapChieu(
            @RequestParam(required = false) Integer regionId,
            Model model) {
        addMovieListModel(model, "sap-chieu", regionId);
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
        var checkout = bookingService.getCheckoutSummary(
                showtimeId,
                BookingService.parseShowSeatIds(showSeatIds),
                productIds,
                quantities
        );

        // Store checkout info in session for later use
        session.setAttribute("checkout", checkout);
        session.setAttribute("showtimeId", showtimeId);
        session.setAttribute("showSeatIds", showSeatIds);
        session.setAttribute("productIds", productIds);
        session.setAttribute("quantities", quantities);
        session.setAttribute("paymentMethod", paymentMethod);

        if (paymentMethod == PaymentMethod.Momo) {
            Long amount = checkout.getGrandTotal().longValue();
            String orderInfo = "Thanh toán vé xem phim: " + checkout.getMovieTitle();
            MomoPaymentResponse response = momoPaymentService.createPayment(amount, orderInfo);

            if (response.getResultCode() == 0) {
                return new RedirectView(response.getPayUrl());
            } else {
                // Handle error
                return new RedirectView("/payment/failure?message=" + response.getMessage());
            }
        }

        // TODO: Add other payment methods (ZaloPay, VNPay, ATM)
        return new RedirectView("/payment/failure?message=Phương thức thanh toán chưa được hỗ trợ");
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
