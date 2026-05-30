package hethongwebbanvexemphim.repository;

import hethongwebbanvexemphim.entity.Menu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByIsActiveTrueOrderByMenuOrderAsc();

    List<Menu> findByIsActiveTrueAndParentIdIsNullOrderByMenuOrderAsc();
}
