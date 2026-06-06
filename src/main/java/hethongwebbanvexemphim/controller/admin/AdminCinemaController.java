package hethongwebbanvexemphim.controller.admin;

import hethongwebbanvexemphim.dto.admin.CinemaForm;
import hethongwebbanvexemphim.service.admin.AdminCinemaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/cinemas")
@RequiredArgsConstructor
public class AdminCinemaController {

    private final AdminCinemaService adminCinemaService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", adminCinemaService.findAll());
        return adminView(model, "views/admin/cinemas");
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        prepareFormModel(model, new CinemaForm());
        return adminView(model, "views/admin/cinema-form");
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        prepareFormModel(model, adminCinemaService.getForm(id));
        return adminView(model, "views/admin/cinema-form");
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute CinemaForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", ")));
            model.addAttribute("form", form);
            return adminView(model, "views/admin/cinema-form");
        }

        try {
            adminCinemaService.save(form);
            redirectAttributes.addFlashAttribute("successMessage", "Lưu rạp thành công");
            return "redirect:/admin/cinemas";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            prepareFormModel(model, form);
            return adminView(model, "views/admin/cinema-form");
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            adminCinemaService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa rạp");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/admin/cinemas";
    }

    private void prepareFormModel(Model model, CinemaForm form) {
        model.addAttribute("form", form);
        model.addAttribute("regions", adminCinemaService.findAllRegions());
    }

    private static String adminView(Model model, String content) {
        model.addAttribute("content", content);
        return "layouts/layout-admin";
    }
}
