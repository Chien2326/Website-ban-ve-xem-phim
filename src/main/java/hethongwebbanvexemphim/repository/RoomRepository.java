package hethongwebbanvexemphim.repository;

import hethongwebbanvexemphim.entity.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findByCinemaCinemaId(Integer cinemaId);

    @Query("SELECT r FROM Room r JOIN FETCH r.cinema ORDER BY r.name")
    List<Room> findAllWithCinema();
}
