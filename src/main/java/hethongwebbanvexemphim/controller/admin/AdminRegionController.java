package hethongwebbanvexemphim.controller.admin;

import hethongwebbanvexemphim.dto.admin.RegionForm;
import hethongwebbanvexemphim.service.admin.AdminRegionService;
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
@RequestMapping("/admin/regions")
@RequiredArgsConstructor
public class AdminRegionController {

    private final AdminRegionService adminRegionService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", adminRegionService.findAll());
        return adminView(model, "views/admin/regions");
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new RegionForm());
        return adminView(model, "views/admin/region-form");
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("form", adminRegionService.getForm(id));
        return adminView(model, "views/admin/region-form");
    }

    @PostMapping("/save")
    public String save(@ModelAttribute RegionForm form, Model model, RedirectAttributes redirectAttributes) {
        try {
            adminRegionService.save(form);
            redirectAttributes.addFlashAttribute("successMessage", "Lưu khu vực thành công");
            return "redirect:/admin/regions";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("form", form);
            return adminView(model, "views/admin/region-form");
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            adminRegionService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa khu vực");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/admin/regions";
    }

    private static String adminView(Model model, String content) {
        model.addAttribute("content", content);
        return "layouts/layout-admin";
    }
}
