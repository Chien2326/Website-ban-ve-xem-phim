package hethongwebbanvexemphim.repository;

import hethongwebbanvexemphim.entity.MenuSidebar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuSidebarRepository extends JpaRepository<MenuSidebar, Integer> {

    List<MenuSidebar> findByIsActiveTrueOrderByItemOrderAsc();
}
