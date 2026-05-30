package hethongwebbanvexemphim.repository;

import hethongwebbanvexemphim.entity.Booking;
import hethongwebbanvexemphim.entity.enums.BookingStatus;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByUserUserId(Integer userId);

    List<Booking> findByStatus(BookingStatus status);

    @EntityGraph(attributePaths = "user")
    List<Booking> findAllByOrderByCreatedAtDesc();

    @EntityGraph(attributePaths = "user")
    List<Booking> findByStatusOrderByCreatedAtDesc(BookingStatus status);
}
