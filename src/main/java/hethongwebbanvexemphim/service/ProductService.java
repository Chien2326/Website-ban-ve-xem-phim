package hethongwebbanvexemphim.service;

import hethongwebbanvexemphim.dto.mapper.DtoMapper;
import hethongwebbanvexemphim.dto.response.ProductDto;
import hethongwebbanvexemphim.entity.enums.ProductStatus;
import hethongwebbanvexemphim.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final String DEFAULT_IMAGE =
            "https://images.unsplash.com/photo-1578849278619-e73505e9610f?q=80&w=200";

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductDto> getAvailableProducts() {
        return DtoMapper.toProducts(productRepository.findByStatusOrderByProductNameAsc(ProductStatus.AVAILABLE));
    }

    public static String defaultImage() {
        return DEFAULT_IMAGE;
    }
}
