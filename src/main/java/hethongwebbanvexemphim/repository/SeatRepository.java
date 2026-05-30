package hethongwebbanvexemphim.repository;

import hethongwebbanvexemphim.entity.Seat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    List<Seat> findByRoomRoomId(Integer roomId);
}
