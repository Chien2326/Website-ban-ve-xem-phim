package hethongwebbanvexemphim.repository;

import hethongwebbanvexemphim.entity.ShowSeat;
import hethongwebbanvexemphim.entity.enums.ShowSeatStatus;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {

    List<ShowSeat> findByShowtimeShowtimeId(Integer showtimeId);

    List<ShowSeat> findByShowtimeShowtimeIdOrderBySeatRowCharAscSeatSeatNumberAsc(Integer showtimeId);

    @Query("""
            SELECT ss FROM ShowSeat ss
            JOIN FETCH ss.seat
            WHERE ss.showtime.showtimeId = :showtimeId
            ORDER BY ss.seat.rowChar ASC, ss.seat.seatNumber ASC
            """)
    List<ShowSeat> findByShowtimeWithSeats(@Param("showtimeId") Integer showtimeId);

    @Query("""
            SELECT ss FROM ShowSeat ss
            JOIN FETCH ss.seat
            JOIN FETCH ss.showtime st
            JOIN FETCH st.movie
            JOIN FETCH st.room r
            JOIN FETCH r.cinema
            WHERE ss.showSeatId IN :showSeatIds
            AND st.showtimeId = :showtimeId
            ORDER BY ss.seat.rowChar ASC, ss.seat.seatNumber ASC
            """)
    List<ShowSeat> findSelectedSeats(
            @Param("showtimeId") Integer showtimeId,
            @Param("showSeatIds") List<Integer> showSeatIds);

    @Query("""
            SELECT ss.seat.seatId FROM ShowSeat ss
            WHERE ss.showtime.showtimeId = :showtimeId
            AND ss.status IN :statuses
            """)
    Set<Integer> findOccupiedSeatIds(
            @Param("showtimeId") Integer showtimeId,
            @Param("statuses") List<ShowSeatStatus> statuses);

    long countByShowtimeShowtimeIdAndStatus(Integer showtimeId, ShowSeatStatus status);

    boolean existsByShowtimeShowtimeIdAndStatusIn(Integer showtimeId, List<ShowSeatStatus> statuses);

    @Query("""
            SELECT COUNT(td) > 0 FROM TicketDetail td
            WHERE td.showSeat.showtime.showtimeId = :showtimeId
            """)
    boolean existsTicketForShowtime(@Param("showtimeId") Integer showtimeId);
}
