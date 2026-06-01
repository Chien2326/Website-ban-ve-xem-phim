package hethongwebbanvexemphim.controller.admin;

import hethongwebbanvexemphim.dto.admin.RoleForm;
import hethongwebbanvexemphim.service.admin.AdminRoleService;
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
@RequestMapping("/admin/roles")
@RequiredArgsConstructor
public class AdminRoleController {

    private final AdminRoleService adminRoleService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", adminRoleService.findAll());
        return adminView(model, "views/admin/roles");
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new RoleForm());
        return adminView(model, "views/admin/role-form");
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("form", adminRoleService.getForm(id));
        return adminView(model, "views/admin/role-form");
    }

    @PostMapping("/save")
    public String save(@ModelAttribute RoleForm form, Model model, RedirectAttributes redirectAttributes) {
        try {
            adminRoleService.save(form);
            redirectAttributes.addFlashAttribute("successMessage", "Lưu vai trò thành công");
            return "redirect:/admin/roles";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("form", form);
            return adminView(model, "views/admin/role-form");
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            adminRoleService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa vai trò");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/admin/roles";
    }

    private static String adminView(Model model, String content) {
        model.addAttribute("content", content);
        return "layouts/layout-admin";
    }
}
