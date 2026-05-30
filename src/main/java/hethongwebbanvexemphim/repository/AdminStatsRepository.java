package hethongwebbanvexemphim.repository;

import hethongwebbanvexemphim.entity.Booking;
import hethongwebbanvexemphim.entity.enums.BookingStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminStatsRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT COALESCE(SUM(b.totalAmount), 0) FROM Booking b WHERE b.status = :status")
    BigDecimal sumTotalAmountByStatus(@Param("status") BookingStatus status);

    @Query("""
            SELECT COALESCE(SUM(b.totalAmount), 0) FROM Booking b
            WHERE b.status = :status AND b.createdAt >= :from
            """)
    BigDecimal sumTotalAmountByStatusSince(
            @Param("status") BookingStatus status,
            @Param("from") Instant from);

    long countByStatus(BookingStatus status);

    @Query("""
            SELECT COUNT(td.ticketId) FROM TicketDetail td
            JOIN td.booking b
            WHERE b.status = :status
            """)
    long countTicketsByBookingStatus(@Param("status") BookingStatus status);

    @Query(value = """
            SELECT DATE(b.created_at) AS day_label,
                   COALESCE(SUM(b.total_amount), 0) AS revenue
            FROM Bookings b
            WHERE b.status = 'Paid' AND b.created_at >= :from
            GROUP BY DATE(b.created_at)
            ORDER BY day_label
            """, nativeQuery = true)
    List<Object[]> revenueByDaySince(@Param("from") Instant from);

    @Query("""
            SELECT st.movie.movieId, st.movie.title, COUNT(td.ticketId)
            FROM TicketDetail td
            JOIN td.booking b
            JOIN td.showSeat ss
            JOIN ss.showtime st
            WHERE b.status = :status
            GROUP BY st.movie.movieId, st.movie.title
            ORDER BY COUNT(td.ticketId) DESC
            """)
    List<Object[]> findTopMoviesByTicketSales(@Param("status") BookingStatus status, Pageable pageable);

    @Query("""
            SELECT p.productId, p.productName, SUM(bp.quantity), COALESCE(SUM(bp.quantity * bp.unitPrice), 0)
            FROM BookingProduct bp
            JOIN bp.booking b
            JOIN bp.product p
            WHERE b.status = :status
            GROUP BY p.productId, p.productName
            ORDER BY SUM(bp.quantity) DESC
            """)
    List<Object[]> findTopProductsByQuantity(@Param("status") BookingStatus status, Pageable pageable);
}
