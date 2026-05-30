package hethongwebbanvexemphim.repository;

import hethongwebbanvexemphim.entity.Product;
import hethongwebbanvexemphim.entity.enums.ProductStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByStatusOrderByProductNameAsc(ProductStatus status);
}
