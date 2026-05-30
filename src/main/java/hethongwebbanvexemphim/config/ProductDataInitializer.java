package hethongwebbanvexemphim.config;

import hethongwebbanvexemphim.entity.Product;
import hethongwebbanvexemphim.entity.enums.ProductStatus;
import hethongwebbanvexemphim.entity.enums.ProductType;
import hethongwebbanvexemphim.repository.ProductRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDataInitializer implements ApplicationRunner {

    private static final String DEFAULT_IMAGE =
            "https://images.unsplash.com/photo-1578849278619-e73505e9610f?q=80&w=200";

    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (productRepository.count() > 0) {
            return;
        }

        saveProduct("Combo 1 Big Extra Premium", ProductType.COMBO,
                new BigDecimal("104000"),
                "\"Thỏa mãn cơn thèm\" với 1 phần bắp rang bơ thơm ngon, 1 Pepsi mát lạnh và 1 gói snack Premium tuỳ chọn!");
        saveProduct("Combo 4", ProductType.COMBO,
                new BigDecimal("199000"),
                "\"Thêm bạn, thêm vui! Combo 4 mang đến 3 bắp rang bơ, 4 Pepsi mát lạnh - tiết kiệm hơn 95,000!");
        saveProduct("Combo 3", ProductType.COMBO,
                new BigDecimal("139000"),
                "\"Chia sẻ niềm vui với bạn bè! Combo 3 gồm 2 bắp rang bơ, 3 Pepsi mát lạnh - tiết kiệm hơn 52,000!");
        saveProduct("Combo 2 Big Extra Premium", ProductType.COMBO,
                new BigDecimal("124000"),
                "\"Nhân đôi sự sảng khoái! Combo gồm 1 bắp rang bơ lớn, 2 Pepsi cỡ lớn + 1 snack Premium tuỳ chọn - tiết kiệm hơn 33,000!");
        saveProduct("Combo 1 Ovaltine Extra Premium", ProductType.COMBO,
                new BigDecimal("115000"),
                "1 Bắp ngọt + 1 Ovaltine 22oz + 1 Snack Premium");
        saveProduct("Pepsi Size L", ProductType.DRINK,
                new BigDecimal("45000"),
                "Pepsi cỡ lớn mát lạnh");
        saveProduct("Bắp rang bơ Size M", ProductType.FOOD,
                new BigDecimal("55000"),
                "Bắp rang bơ thơm ngon size vừa");
    }

    private void saveProduct(String name, ProductType type, BigDecimal price, String description) {
        productRepository.save(Product.builder()
                .productName(name)
                .description(description)
                .price(price)
                .imageUrl(DEFAULT_IMAGE)
                .productType(type)
                .status(ProductStatus.AVAILABLE)
                .build());
    }
}
