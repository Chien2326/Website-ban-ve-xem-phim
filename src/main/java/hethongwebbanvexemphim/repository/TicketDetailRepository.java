package hethongwebbanvexemphim.repository;

import hethongwebbanvexemphim.entity.TicketDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketDetailRepository extends JpaRepository<TicketDetail, Integer> {

    List<TicketDetail> findByBookingBookingId(Integer bookingId);

    @Query("""
            SELECT td FROM TicketDetail td
            JOIN FETCH td.booking b
            JOIN FETCH b.user
            JOIN FETCH td.showSeat ss
            JOIN FETCH ss.seat
            JOIN FETCH ss.showtime st
            JOIN FETCH st.movie
            ORDER BY b.createdAt DESC, td.ticketId DESC
            """)
    List<TicketDetail> findAllForAdmin();
}
