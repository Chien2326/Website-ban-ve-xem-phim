package hethongwebbanvexemphim.controller.admin;

import hethongwebbanvexemphim.dto.admin.GenreForm;
import hethongwebbanvexemphim.service.admin.AdminGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/genres")
@RequiredArgsConstructor
public class AdminGenreController {

    private final AdminGenreService adminGenreService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", adminGenreService.findAll());
        return adminView(model, "views/admin/genres");
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new GenreForm());
        return adminView(model, "views/admin/genre-form");
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("form", adminGenreService.getForm(id));
        return adminView(model, "views/admin/genre-form");
    }

    @PostMapping("/save")
    public String save(@ModelAttribute GenreForm form, Model model, RedirectAttributes redirectAttributes) {
        try {
            adminGenreService.save(form);
            redirectAttributes.addFlashAttribute("successMessage", "Lưu thể loại thành công");
            return "redirect:/admin/genres";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("form", form);
            return adminView(model, "views/admin/genre-form");
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            adminGenreService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa thể loại");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/admin/genres";
    }

    private static String adminView(Model model, String content) {
        model.addAttribute("content", content);
        return "layouts/layout-admin";
    }
}
