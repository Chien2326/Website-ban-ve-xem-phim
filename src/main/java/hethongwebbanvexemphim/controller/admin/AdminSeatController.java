package hethongwebbanvexemphim.controller.admin;

import hethongwebbanvexemphim.dto.admin.SeatForm;
import hethongwebbanvexemphim.entity.enums.SeatType;
import hethongwebbanvexemphim.service.admin.AdminSeatService;
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
@RequestMapping("/admin/seats")
@RequiredArgsConstructor
public class AdminSeatController {

    private final AdminSeatService adminSeatService;

    @GetMapping
    public String list() {
        return "redirect:/admin/rooms";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        prepareFormModel(model, new SeatForm());
        return adminView(model, "views/admin/seat-form");
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        prepareFormModel(model, adminSeatService.getForm(id));
        return adminView(model, "views/admin/seat-form");
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute SeatForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", ")));
            prepareFormModel(model, form);
            return adminView(model, "views/admin/seat-form");
        }

        try {
            adminSeatService.save(form);
            redirectAttributes.addFlashAttribute("successMessage", "Lưu ghế thành công");
            return "redirect:/admin/rooms";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            prepareFormModel(model, form);
            return adminView(model, "views/admin/seat-form");
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            adminSeatService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa ghế");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/admin/rooms";
    }

    private void prepareFormModel(Model model, SeatForm form) {
        model.addAttribute("form", form);
        model.addAttribute("rooms", adminSeatService.findAllRooms());
        model.addAttribute("seatTypes", SeatType.values());
    }

    private static String adminView(Model model, String content) {
        model.addAttribute("content", content);
        return "layouts/layout-admin";
    }
}
