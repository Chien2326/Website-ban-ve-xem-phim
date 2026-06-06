package hethongwebbanvexemphim.controller.admin;

import hethongwebbanvexemphim.dto.admin.RoomForm;
import hethongwebbanvexemphim.repository.SeatRepository;
import hethongwebbanvexemphim.service.admin.AdminRoomService;
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
@RequestMapping("/admin/rooms")
@RequiredArgsConstructor
public class AdminRoomController {

    private final AdminRoomService adminRoomService;
    private final SeatRepository seatRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", adminRoomService.findAll());
        model.addAttribute("seats", seatRepository.findAll());
        return adminView(model, "views/admin/rooms");
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        prepareFormModel(model, new RoomForm());
        return adminView(model, "views/admin/room-form");
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        prepareFormModel(model, adminRoomService.getForm(id));
        return adminView(model, "views/admin/room-form");
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute RoomForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", ")));
            model.addAttribute("form", form);
            return adminView(model, "views/admin/room-form");
        }

        try {
            adminRoomService.save(form);
            redirectAttributes.addFlashAttribute("successMessage", "Lưu phòng thành công");
            return "redirect:/admin/rooms";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            prepareFormModel(model, form);
            return adminView(model, "views/admin/room-form");
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            adminRoomService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa phòng");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/admin/rooms";
    }

    private void prepareFormModel(Model model, RoomForm form) {
        model.addAttribute("form", form);
        model.addAttribute("cinemas", adminRoomService.findAllCinemas());
    }

    private static String adminView(Model model, String content) {
        model.addAttribute("content", content);
        return "layouts/layout-admin";
    }
}
