package hethongwebbanvexemphim.config;

import hethongwebbanvexemphim.entity.Menu;
import hethongwebbanvexemphim.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuDataInitializer implements ApplicationRunner {

    private final MenuRepository menuRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (menuRepository.count() > 0) {
            return;
        }

        menuRepository.save(Menu.builder().menuName("Trang chủ").isActive(true).link("/").menuOrder(1).levels(1).position(1).build());
        menuRepository.save(Menu.builder().menuName("Giới thiệu").isActive(true).link("/gioi-thieu").menuOrder(2).levels(1).position(1).build());
        menuRepository.save(Menu.builder().menuName("Sự kiện").isActive(true).link("/phim-sap-chieu").menuOrder(3).levels(1).position(1).build());
        menuRepository.save(Menu.builder().menuName("Rạp/Giá Vé").isActive(true).link("/phim-dang-chieu").menuOrder(4).levels(1).position(1).build());
        menuRepository.save(Menu.builder().menuName("Liên hệ").isActive(true).link("/lien-he").menuOrder(5).levels(1).position(1).build());
    }
}
