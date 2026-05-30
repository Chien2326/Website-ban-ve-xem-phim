package hethongwebbanvexemphim.repository;

import hethongwebbanvexemphim.entity.Cinema;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CinemaRepository extends JpaRepository<Cinema, Integer> {

    List<Cinema> findByRegionRegionId(Integer regionId);

    List<Cinema> findByRegionRegionIdOrderByNameAsc(Integer regionId);

    List<Cinema> findAllByOrderByNameAsc();

    @Query("SELECT c FROM Cinema c JOIN FETCH c.region ORDER BY c.name")
    List<Cinema> findAllWithRegion();
}
