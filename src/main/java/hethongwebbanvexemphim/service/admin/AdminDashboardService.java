package hethongwebbanvexemphim.service.admin;

import hethongwebbanvexemphim.dto.admin.DashboardStatsDto;
import hethongwebbanvexemphim.dto.admin.RevenuePointDto;
import hethongwebbanvexemphim.dto.admin.TopMovieDto;
import hethongwebbanvexemphim.dto.admin.TopProductDto;
import hethongwebbanvexemphim.entity.enums.BookingStatus;
import hethongwebbanvexemphim.repository.AdminStatsRepository;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private static final ZoneId ZONE = ZoneId.of("Asia/Ho_Chi_Minh");
    private static final DateTimeFormatter CHART_DAY = DateTimeFormatter.ofPattern("dd/MM");

    private final AdminStatsRepository adminStatsRepository;

    @Transactional(readOnly = true)
    public DashboardStatsDto getDashboard(String period) {
        String normalized = normalizePeriod(period);
        LocalDateTime from = periodStart(normalized);

        BigDecimal totalRevenue = adminStatsRepository.sumTotalAmountByStatus(BookingStatus.Paid);
        BigDecimal periodRevenue = adminStatsRepository.sumTotalAmountByStatusSince(BookingStatus.Paid, from);

        List<RevenuePointDto> chart = mapRevenueChart(adminStatsRepository.revenueByDaySince(from));
        List<TopMovieDto> topMovies = mapTopMovies(
                adminStatsRepository.findTopMoviesByTicketSales(
                        BookingStatus.Paid, PageRequest.of(0, 5)));
        List<TopProductDto> topProducts = mapTopProducts(
                adminStatsRepository.findTopProductsByQuantity(
                        BookingStatus.Paid, PageRequest.of(0, 5)));

        return DashboardStatsDto.builder()
                .period(normalized)
                .periodLabel(periodLabel(normalized))
                .totalRevenue(nullToZero(totalRevenue))
                .periodRevenue(nullToZero(periodRevenue))
                .paidBookings(adminStatsRepository.countByStatus(BookingStatus.Paid))
                .pendingBookings(adminStatsRepository.countByStatus(BookingStatus.Pending))
                .cancelledBookings(adminStatsRepository.countByStatus(BookingStatus.Cancelled))
                .ticketsSold(adminStatsRepository.countTicketsByBookingStatus(BookingStatus.Paid))
                .revenueChart(chart)
                .topMovies(topMovies)
                .topProducts(topProducts)
                .build();
    }

    private static String normalizePeriod(String period) {
        if (period == null) {
            return "week";
        }
        return switch (period.toLowerCase()) {
            case "day", "week", "month" -> period.toLowerCase();
            default -> "week";
        };
    }

    private static LocalDateTime periodStart(String period) {
        LocalDate today = LocalDate.now(ZONE);
        LocalDate start = switch (period) {
            case "day" -> today;
            case "month" -> today.withDayOfMonth(1);
            default -> today.minusDays(6);
        };
        return start.atStartOfDay();
    }

    private static String periodLabel(String period) {
        return switch (period) {
            case "day" -> "Hôm nay";
            case "month" -> "Tháng này";
            default -> "7 ngày qua";
        };
    }

    private static List<RevenuePointDto> mapRevenueChart(List<Object[]> rows) {
        List<RevenuePointDto> result = new ArrayList<>();
        for (Object[] row : rows) {
            LocalDate day;
            if (row[0] instanceof Date sqlDate) {
                day = sqlDate.toLocalDate();
            } else if (row[0] instanceof LocalDate localDate) {
                day = localDate;
            } else {
                day = LocalDate.parse(row[0].toString());
            }
            BigDecimal amount = row[1] instanceof BigDecimal bd ? bd : new BigDecimal(row[1].toString());
            result.add(RevenuePointDto.builder()
                    .label(day.format(CHART_DAY))
                    .amount(amount)
                    .build());
        }
        return result;
    }

    private static List<TopMovieDto> mapTopMovies(List<Object[]> rows) {
        List<TopMovieDto> result = new ArrayList<>();
        for (Object[] row : rows) {
            result.add(TopMovieDto.builder()
                    .movieId((Integer) row[0])
                    .title((String) row[1])
                    .ticketsSold(((Number) row[2]).longValue())
                    .build());
        }
        return result;
    }

    private static List<TopProductDto> mapTopProducts(List<Object[]> rows) {
        List<TopProductDto> result = new ArrayList<>();
        for (Object[] row : rows) {
            BigDecimal revenue = row[3] instanceof BigDecimal bd ? bd : new BigDecimal(row[3].toString());
            result.add(TopProductDto.builder()
                    .productId((Integer) row[0])
                    .productName((String) row[1])
                    .quantitySold(((Number) row[2]).longValue())
                    .revenue(revenue)
                    .build());
        }
        return result;
    }

    private static BigDecimal nullToZero(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }
}
