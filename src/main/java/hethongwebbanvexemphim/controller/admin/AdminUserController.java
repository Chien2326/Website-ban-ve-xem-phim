package hethongwebbanvexemphim.controller.admin;

import hethongwebbanvexemphim.dto.admin.UserForm;
import hethongwebbanvexemphim.entity.enums.Gender;
import hethongwebbanvexemphim.service.admin.AdminUserService;
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
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", adminUserService.findAll());
        return adminView(model, "views/admin/users");
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        prepareFormModel(model, new UserForm());
        return adminView(model, "views/admin/user-form");
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        prepareFormModel(model, adminUserService.getForm(id));
        return adminView(model, "views/admin/user-form");
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute UserForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", ")));
            prepareFormModel(model, form);
            return adminView(model, "views/admin/user-form");
        }

        try {
            adminUserService.save(form);
            redirectAttributes.addFlashAttribute("successMessage", "Lưu người dùng thành công");
            return "redirect:/admin/users";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            prepareFormModel(model, form);
            return adminView(model, "views/admin/user-form");
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            adminUserService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa người dùng");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/admin/users";
    }

    private void prepareFormModel(Model model, UserForm form) {
        model.addAttribute("form", form);
        model.addAttribute("roles", adminUserService.findAllRoles());
        model.addAttribute("genders", Gender.values());
    }

    private static String adminView(Model model, String content) {
        model.addAttribute("content", content);
        return "layouts/layout-admin";
    }
}
