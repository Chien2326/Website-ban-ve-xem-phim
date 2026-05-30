package hethongwebbanvexemphim.dto.admin;

import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DashboardStatsDto {

    private final String period;
    private final String periodLabel;
    private final BigDecimal totalRevenue;
    private final BigDecimal periodRevenue;
    private final long paidBookings;
    private final long pendingBookings;
    private final long cancelledBookings;
    private final long ticketsSold;
    private final List<RevenuePointDto> revenueChart;
    private final List<TopMovieDto> topMovies;
    private final List<TopProductDto> topProducts;
}
