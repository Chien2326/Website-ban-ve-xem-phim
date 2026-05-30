package hethongwebbanvexemphim.repository;

import hethongwebbanvexemphim.entity.BookingProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingProductRepository extends JpaRepository<BookingProduct, Integer> {

    List<BookingProduct> findByBookingBookingId(Integer bookingId);

    @Query("""
            SELECT bp FROM BookingProduct bp
            JOIN FETCH bp.booking
            JOIN FETCH bp.product
            ORDER BY bp.bookingProductId DESC
            """)
    List<BookingProduct> findAllForAdmin();
}
