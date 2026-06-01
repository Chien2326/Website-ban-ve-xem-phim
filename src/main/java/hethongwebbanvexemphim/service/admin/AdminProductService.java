package hethongwebbanvexemphim.service.admin;

import hethongwebbanvexemphim.dto.admin.ProductForm;
import hethongwebbanvexemphim.entity.Product;
import hethongwebbanvexemphim.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ProductForm getForm(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm"));
        ProductForm form = new ProductForm();
        form.setProductId(product.getProductId());
        form.setProductName(product.getProductName());
        form.setDescription(product.getDescription());
        form.setPrice(product.getPrice());
        form.setImageUrl(product.getImageUrl());
        form.setProductType(product.getProductType());
        form.setStatus(product.getStatus());
        return form;
    }

    @Transactional
    public Product save(ProductForm form) {
        validate(form);
        Product product;
        if (form.getProductId() == null) {
            product = new Product();
        } else {
            product = productRepository.findById(form.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm"));
        }
        product.setProductName(form.getProductName().trim());
        product.setDescription(form.getDescription() != null ? form.getDescription().trim() : null);
        product.setPrice(form.getPrice());
        product.setImageUrl(form.getImageUrl() != null ? form.getImageUrl().trim() : null);
        product.setProductType(form.getProductType());
        product.setStatus(form.getStatus() != null ? form.getStatus() : product.getStatus());
        return productRepository.save(product);
    }

    @Transactional
    public void delete(Integer productId) {
        if (!productRepository.existsById(productId)) {
            throw new IllegalArgumentException("Không tìm thấy sản phẩm");
        }
        productRepository.deleteById(productId);
    }

    private static void validate(ProductForm form) {
        if (form.getProductName() == null || form.getProductName().isBlank()) {
            throw new IllegalArgumentException("Tên sản phẩm không được để trống");
        }
        if (form.getPrice() == null || form.getPrice().signum() < 0) {
            throw new IllegalArgumentException("Giá không hợp lệ");
        }
        if (form.getProductType() == null) {
            throw new IllegalArgumentException("Vui lòng chọn loại sản phẩm");
        }
    }
}
