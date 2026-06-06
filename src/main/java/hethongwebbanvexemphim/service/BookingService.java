package hethongwebbanvexemphim.service;

import hethongwebbanvexemphim.dto.mapper.DtoMapper;
import hethongwebbanvexemphim.dto.response.BookingPageDto;
import hethongwebbanvexemphim.dto.response.CheckoutSummaryDto;
import hethongwebbanvexemphim.dto.response.SeatDto;
import hethongwebbanvexemphim.dto.response.SeatRowDto;
import hethongwebbanvexemphim.dto.response.SelectedProductSummaryDto;
import hethongwebbanvexemphim.dto.response.SelectedSeatSummaryDto;
import hethongwebbanvexemphim.dto.response.ShowtimeDto;
import hethongwebbanvexemphim.entity.Booking;
import hethongwebbanvexemphim.entity.BookingProduct;
import hethongwebbanvexemphim.entity.Product;
import hethongwebbanvexemphim.entity.ShowSeat;
import hethongwebbanvexemphim.entity.Showtime;
import hethongwebbanvexemphim.entity.TicketDetail;
import hethongwebbanvexemphim.entity.User;
import hethongwebbanvexemphim.entity.enums.AgeRating;
import hethongwebbanvexemphim.entity.enums.BookingStatus;
import hethongwebbanvexemphim.entity.enums.PaymentMethod;
import hethongwebbanvexemphim.entity.enums.ProductStatus;
import hethongwebbanvexemphim.entity.enums.ShowSeatStatus;
import hethongwebbanvexemphim.repository.BookingProductRepository;
import hethongwebbanvexemphim.repository.BookingRepository;
import hethongwebbanvexemphim.repository.ProductRepository;
import hethongwebbanvexemphim.repository.ShowSeatRepository;
import hethongwebbanvexemphim.repository.ShowtimeRepository;
import hethongwebbanvexemphim.repository.TicketDetailRepository;
import hethongwebbanvexemphim.repository.UserRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final ShowtimeRepository showtimeRepository;
    private final ShowSeatRepository showSeatRepository;
    private final ProductRepository productRepository;
    private final BookingRepository bookingRepository;
    private final TicketDetailRepository ticketDetailRepository;
    private final BookingProductRepository bookingProductRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public BookingPageDto getBookingPage(Integer showtimeId) {
        Showtime showtime = showtimeRepository.findDetailById(showtimeId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy suất chiếu"));

        List<ShowSeat> showSeats = showSeatRepository.findByShowtimeWithSeats(showtimeId);
        List<SeatRowDto> seatRows = groupSeatsByRow(showSeats);

        Integer movieId = showtime.getMovie().getMovieId();
        Integer cinemaId = showtime.getRoom().getCinema().getCinemaId();
        List<ShowtimeDto> alternateShowtimes = DtoMapper.toShowtimes(
                showtimeRepository.findUpcomingByMovieAndCinema(movieId, cinemaId, LocalDateTime.now()));

        AgeRating ageRating = showtime.getMovie().getRating();
        String ratingLabel = ageRating == null ? null : ageRating.name();

        return BookingPageDto.builder()
                .showtimeId(showtime.getShowtimeId())
                .movieId(movieId)
                .movieTitle(showtime.getMovie().getTitle())
                .posterUrl(showtime.getMovie().getPosterUrl())
                .rating(ratingLabel)
                .ratingBadgeClass(ratingBadgeClass(ratingLabel))
                .formatType(showtime.getFormatType())
                .cinemaName(showtime.getRoom().getCinema().getName())
                .roomName(showtime.getRoom().getName())
                .startTime(showtime.getStartTime())
                .alternateShowtimes(alternateShowtimes)
                .seatRows(seatRows)
                .build();
    }

    @Transactional(readOnly = true)
    public CheckoutSummaryDto getCheckoutSummary(
            Integer showtimeId,
            List<Integer> showSeatIds,
            List<Integer> productIds,
            List<Integer> quantities) {
        if (showSeatIds == null || showSeatIds.isEmpty()) {
            throw new IllegalArgumentException("Chưa chọn ghế");
        }

        Showtime showtime = showtimeRepository.findDetailById(showtimeId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy suất chiếu"));

        List<ShowSeat> selectedSeats = showSeatRepository.findSelectedSeats(showtimeId, showSeatIds);
        if (selectedSeats.size() != showSeatIds.size()) {
            throw new IllegalArgumentException("Ghế đã chọn không hợp lệ");
        }

        List<SelectedSeatSummaryDto> seatSummaries = selectedSeats.stream()
                .map(ss -> {
                    if (ss.getStatus() != ShowSeatStatus.Available) {
                        throw new IllegalArgumentException("Ghế " + ss.getSeat().getRowChar()
                                + ss.getSeat().getSeatNumber() + " không còn trống");
                    }
                    return SelectedSeatSummaryDto.builder()
                            .showSeatId(ss.getShowSeatId())
                            .seatLabel(ss.getSeat().getRowChar() + ss.getSeat().getSeatNumber())
                            .price(ss.getPrice())
                            .build();
                })
                .toList();

        BigDecimal seatTotal = seatSummaries.stream()
                .map(SelectedSeatSummaryDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<SelectedProductSummaryDto> productSummaries = loadSelectedProducts(productIds, quantities);
        BigDecimal productTotal = productSummaries.stream()
                .map(SelectedProductSummaryDto::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        String seatLabels = seatSummaries.stream()
                .map(SelectedSeatSummaryDto::getSeatLabel)
                .collect(Collectors.joining(", "));

        AgeRating ageRating = showtime.getMovie().getRating();
        String ratingLabel = ageRating == null ? null : ageRating.name();

        return CheckoutSummaryDto.builder()
                .showtimeId(showtime.getShowtimeId())
                .movieId(showtime.getMovie().getMovieId())
                .movieTitle(showtime.getMovie().getTitle())
                .posterUrl(showtime.getMovie().getPosterUrl())
                .rating(ratingLabel)
                .ratingBadgeClass(ratingBadgeClass(ratingLabel))
                .formatType(showtime.getFormatType())
                .cinemaName(showtime.getRoom().getCinema().getName())
                .roomName(showtime.getRoom().getName())
                .startTime(showtime.getStartTime())
                .selectedSeats(seatSummaries)
                .selectedProducts(productSummaries)
                .seatTotal(seatTotal)
                .productTotal(productTotal)
                .grandTotal(seatTotal.add(productTotal))
                .seatLabels(seatLabels)
                .paymentMethods(Arrays.asList(PaymentMethod.values()))
                .build();
    }

    private List<SelectedProductSummaryDto> loadSelectedProducts(
            List<Integer> productIds,
            List<Integer> quantities) {
        if (productIds == null || productIds.isEmpty()) {
            return List.of();
        }

        List<Product> products = productRepository.findAllById(productIds);
        Map<Integer, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductId, p -> p));

        List<SelectedProductSummaryDto> result = new ArrayList<>();
        for (int i = 0; i < productIds.size(); i++) {
            Integer productId = productIds.get(i);
            Product product = productMap.get(productId);
            if (product == null || product.getStatus() != ProductStatus.AVAILABLE) {
                continue;
            }
            int qty = (quantities != null && i < quantities.size() && quantities.get(i) > 0)
                    ? quantities.get(i)
                    : 1;
            BigDecimal lineTotal = product.getPrice().multiply(BigDecimal.valueOf(qty));
            result.add(SelectedProductSummaryDto.builder()
                    .productId(product.getProductId())
                    .productName(product.getProductName())
                    .quantity(qty)
                    .unitPrice(product.getPrice())
                    .lineTotal(lineTotal)
                    .build());
        }
        return result;
    }

    public String buildThucAnUrl(Integer showtimeId, String showSeatIds) {
        return UriComponentsBuilder.fromPath("/thuc-an")
                .queryParam("showtimeId", showtimeId)
                .queryParam("showSeatIds", showSeatIds)
                .build()
                .toUriString();
    }

    public String buildThanhToanUrl(
            Integer showtimeId,
            String showSeatIds,
            List<Integer> productIds,
            List<Integer> quantities) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/thanh-toan")
                .queryParam("showtimeId", showtimeId)
                .queryParam("showSeatIds", showSeatIds);
        appendProductParams(builder, productIds, quantities);
        return builder.build().toUriString();
    }

    public String buildConfirmUrl(
            Integer showtimeId,
            String showSeatIds,
            List<Integer> productIds,
            List<Integer> quantities,
            PaymentMethod paymentMethod) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/xac-nhan")
                .queryParam("showtimeId", showtimeId)
                .queryParam("showSeatIds", showSeatIds);
        appendProductParams(builder, productIds, quantities);
        if (paymentMethod != null) {
            builder.queryParam("paymentMethod", paymentMethod.name());
        }
        return builder.build().toUriString();
    }

    private static void appendProductParams(
            UriComponentsBuilder builder,
            List<Integer> productIds,
            List<Integer> quantities) {
        if (productIds == null || productIds.isEmpty()) {
            return;
        }
        for (Integer productId : productIds) {
            builder.queryParam("productIds", productId);
        }
        if (quantities != null) {
            for (Integer quantity : quantities) {
                builder.queryParam("quantities", quantity);
            }
        }
    }

    public static String paymentMethodLabel(PaymentMethod method) {
        return switch (method) {
            case Momo -> "Ví MoMo";
            case ZaloPay -> "ZaloPay";
            case VNPAY -> "VNPay";
            case ATM -> "Thẻ ATM / OnePay";
            case TEST -> "Thanh toán thử nghiệm";
        };
    }

    public static String ageConfirmationMessage(String rating) {
        if (rating == null || rating.isBlank()) {
            return "Vui lòng xác nhận bạn đủ điều kiện độ tuổi theo quy định khi xem phim.";
        }
        return switch (rating.toUpperCase()) {
            case "P" -> "Phim được phổ biến cho mọi đối tượng khán giả.";
            case "K" ->
                    "Phim được phổ biến đến người xem dưới 13 tuổi với điều kiện xem cùng cha, mẹ hoặc người giám hộ "
                            + "và đồng ý cung cấp giấy tờ tuỳ thân để xác minh độ tuổi.";
            case "T13" -> "Phim được phổ biến cho người từ đủ 13 tuổi trở lên.";
            case "T16" ->
                    "Phim được phổ biến cho người từ đủ 16 tuổi trở lên. Khán giả từ 13 đến dưới 16 tuổi "
                            + "xem cùng người giám hộ theo quy định.";
            case "T18" -> "Phim được phổ biến cho người từ đủ 18 tuổi trở lên.";
            default -> "Vui lòng xác nhận bạn đủ điều kiện độ tuổi theo quy định khi xem phim.";
        };
    }

    public static List<Integer> parseShowSeatIds(String showSeatIds) {
        if (showSeatIds == null || showSeatIds.isBlank()) {
            return List.of();
        }
        return java.util.Arrays.stream(showSeatIds.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private static List<SeatRowDto> groupSeatsByRow(List<ShowSeat> showSeats) {
        Map<String, List<SeatDto>> grouped = new LinkedHashMap<>();
        showSeats.stream()
                .sorted(Comparator
                        .comparing((ShowSeat ss) -> ss.getSeat().getRowChar())
                        .thenComparing(ss -> ss.getSeat().getSeatNumber()))
                .forEach(showSeat -> {
                    String rowChar = showSeat.getSeat().getRowChar();
                    grouped.computeIfAbsent(rowChar, key -> new ArrayList<>())
                            .add(DtoMapper.fromShowSeat(showSeat));
                });

        return grouped.entrySet().stream()
                .map(entry -> SeatRowDto.builder()
                        .rowChar(entry.getKey())
                        .seats(entry.getValue())
                        .build())
                .toList();
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

    @Transactional
    public Booking createBooking(
            Integer showtimeId,
            List<Integer> showSeatIds,
            List<Integer> productIds,
            List<Integer> quantities,
            PaymentMethod paymentMethod,
            User user
    ) {
        // 1. Lấy và kiểm tra showtime
        Showtime showtime = showtimeRepository.findDetailById(showtimeId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy suất chiếu"));

        // 2. Tạo Booking
        Booking booking = Booking.builder()
                .user(user)
                .totalAmount(BigDecimal.ZERO)
                .paymentMethod(paymentMethod)
                .status(BookingStatus.Paid)
                .build();
        booking = bookingRepository.save(booking);

        BigDecimal totalAmount = BigDecimal.ZERO;

        // 3. Xử lý ghế: cập nhật ShowSeat và tạo TicketDetail
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        for (ShowSeat showSeat : showSeats) {
            // Cập nhật trạng thái ghế
            showSeat.setStatus(ShowSeatStatus.Booked);
            showSeatRepository.save(showSeat);

            // Tạo TicketDetail
            TicketDetail ticketDetail = TicketDetail.builder()
                    .booking(booking)
                    .showSeat(showSeat)
                    .priceActual(showSeat.getPrice())
                    .build();
            ticketDetailRepository.save(ticketDetail);

            totalAmount = totalAmount.add(showSeat.getPrice());
        }

        // 4. Xử lý sản phẩm (nếu có)
        if (productIds != null && !productIds.isEmpty()) {
            List<Product> products = productRepository.findAllById(productIds);
            Map<Integer, Product> productMap = products.stream()
                    .collect(Collectors.toMap(Product::getProductId, p -> p));

            for (int i = 0; i < productIds.size(); i++) {
                Integer productId = productIds.get(i);
                Product product = productMap.get(productId);
                if (product == null) continue;

                int qty = (quantities != null && i < quantities.size() && quantities.get(i) > 0)
                        ? quantities.get(i)
                        : 1;

                BookingProduct bookingProduct = BookingProduct.builder()
                        .booking(booking)
                        .product(product)
                        .quantity(qty)
                        .unitPrice(product.getPrice())
                        .build();
                bookingProductRepository.save(bookingProduct);

                totalAmount = totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(qty)));
            }
        }

        // 5. Cập nhật tổng tiền cho Booking
        booking.setTotalAmount(totalAmount);
        booking = bookingRepository.save(booking);

        return booking;
    }
}
