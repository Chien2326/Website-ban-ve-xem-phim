package hethongwebbanvexemphim.config;

import hethongwebbanvexemphim.dto.mapper.DtoMapper;
import hethongwebbanvexemphim.dto.response.MenuNavDto;
import hethongwebbanvexemphim.repository.MenuRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(basePackages = "hethongwebbanvexemphim.controller")
@RequiredArgsConstructor
public class MenuLayoutAdvice {

    private final MenuRepository menuRepository;

    @ModelAttribute("navMenus")
    public List<MenuNavDto> navMenus() {
        return DtoMapper.fromMenus(menuRepository.findByIsActiveTrueAndParentIdIsNullOrderByMenuOrderAsc());
    }
}
