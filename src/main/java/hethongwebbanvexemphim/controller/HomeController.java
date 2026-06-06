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
            @RequestHeader(value = "HX-Request", required = false) String hxRequest,
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
        
        if ("true".equals(hxRequest)) {
            // Return only the schedule section for htmx requests
            return "views/chi-tiet-phim :: schedule-section";
        }
        
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
                // Gọi API Momo thực sự để lấy link thanh toán
                Long amount = checkout.getGrandTotal().longValue();
                String orderInfo = "Thanh toan ve xem phim - " + checkout.getMovieTitle();
                
                System.out.println("=== CALLING MOMO API ===");
                System.out.println("Amount = " + amount);
                System.out.println("Order Info = " + orderInfo);
                
                MomoPaymentResponse momoResponse = momoPaymentService.createPayment(amount, orderInfo);
                
                System.out.println("=== MOMO RESPONSE ===");
                System.out.println("Result Code = " + momoResponse.getResultCode());
                System.out.println("Message = " + momoResponse.getMessage());
                System.out.println("Pay URL = " + momoResponse.getPayUrl());
                
                if (momoResponse.getResultCode() == 0 && momoResponse.getPayUrl() != null) {
                    // Lưu orderId và requestId vào session để dùng khi callback
                    session.setAttribute("momoOrderId", momoResponse.getOrderId());
                    session.setAttribute("momoRequestId", momoResponse.getRequestId());
                    
                    // Redirect sang trang thanh toán MoMo
                    return new RedirectView(momoResponse.getPayUrl());
                } else {
                    String errorMsg = momoResponse.getMessage() != null ? momoResponse.getMessage() : "Lỗi thanh toán Momo";
                    return new RedirectView("/payment/failure?message=" + java.net.URLEncoder.encode(errorMsg, java.nio.charset.StandardCharsets.UTF_8));
                }
            } else if (paymentMethod == PaymentMethod.TEST) {
                // Phương thức thanh toán thử nghiệm: tạo đơn hàng ngay lập tức
                try {
                    User currentUser = userService.getCurrentUser();
                    if (currentUser != null) {
                        List<Integer> showSeatIdsList = BookingService.parseShowSeatIds(showSeatIds);
                        bookingService.createBooking(
                                showtimeId,
                                showSeatIdsList,
                                productIds,
                                quantities,
                                paymentMethod,
                                currentUser
                        );
                        // Đưa checkout vào session để trang success hiện thông tin
                        session.setAttribute("checkout", checkout);
                    }
                    return new RedirectView("/payment/success?resultCode=0");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("=== ERROR TEST PAYMENT: " + e.getMessage());
                    return new RedirectView("/payment/failure?message=" + java.net.URLEncoder.encode(e.getMessage(), java.nio.charset.StandardCharsets.UTF_8));
                }
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
            HttpSession session,
            Model model
    ) {
        if (resultCode == 0) {
            // Lấy dữ liệu từ session
            Object checkout = session.getAttribute("checkout");
            Object uniqueTicketCode = session.getAttribute("uniqueTicketCode");
            PaymentMethod paymentMethod = (PaymentMethod) session.getAttribute("paymentMethod");
            
            // Lưu đơn hàng vào DB CHỈ KHI NÓ LÀ PHƯƠNG THỨC MOMO (không phải TEST)
            if (paymentMethod != PaymentMethod.TEST) {
                try {
                    Integer showtimeId = (Integer) session.getAttribute("showtimeId");
                    String showSeatIdsStr = (String) session.getAttribute("showSeatIds");
                    List<Integer> productIds = (List<Integer>) session.getAttribute("productIds");
                    List<Integer> quantities = (List<Integer>) session.getAttribute("quantities");
                    User currentUser = userService.getCurrentUser();

                    if (showtimeId != null && showSeatIdsStr != null && currentUser != null) {
                        List<Integer> showSeatIds = BookingService.parseShowSeatIds(showSeatIdsStr);
                        bookingService.createBooking(
                                showtimeId,
                                showSeatIds,
                                productIds,
                                quantities,
                                paymentMethod,
                                currentUser
                        );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("=== ERROR SAVING BOOKING: " + e.getMessage());
                }
            }
            
            // Xóa session sau khi thành công
            session.removeAttribute("checkout");
            session.removeAttribute("showtimeId");
            session.removeAttribute("showSeatIds");
            session.removeAttribute("productIds");
            session.removeAttribute("quantities");
            session.removeAttribute("paymentMethod");
            session.removeAttribute("uniqueTicketCode");

            // Thêm dữ liệu vào Model để template có thể sử dụng
            model.addAttribute("checkout", checkout);
            model.addAttribute("uniqueTicketCode", uniqueTicketCode);
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

    // MoMo IPN (Instant Payment Notification)
    @PostMapping("/payment/ipn")
    @ResponseBody
    public String paymentIpn(
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
            HttpSession session
    ) {
        System.out.println("=== MO MO IPN RECEIVED ===");
        System.out.println("orderId = " + orderId);
        System.out.println("resultCode = " + resultCode);
        System.out.println("message = " + message);

        // TODO: Verify signature, update booking status, etc.
        // For now just log it
        return "{\"resultCode\":0,\"message\":\"Success\"}";
    }
}
