package hethongwebbanvexemphim.controller.admin;

import hethongwebbanvexemphim.dto.admin.ShowtimeForm;
import hethongwebbanvexemphim.entity.enums.ShowSeatStatus;
import hethongwebbanvexemphim.service.admin.AdminSeatMonitorService;
import hethongwebbanvexemphim.service.admin.AdminShowtimeService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/showtimes")
@RequiredArgsConstructor
public class AdminShowtimeController {

    private final AdminShowtimeService adminShowtimeService;
    private final AdminSeatMonitorService adminSeatMonitorService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", adminShowtimeService.findAll());
        return adminView(model, "views/admin/showtimes");
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        prepareFormModel(model, new ShowtimeForm());
        return adminView(model, "views/admin/showtime-form");
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        prepareFormModel(model, adminShowtimeService.getForm(id));
        return adminView(model, "views/admin/showtime-form");
    }

    @GetMapping("/{id}/seats")
    public String seatMonitor(@PathVariable Integer id, Model model) {
        model.addAttribute("monitor", adminSeatMonitorService.getMonitorPage(id));
        model.addAttribute("seatStatuses", ShowSeatStatus.values());
        return adminView(model, "views/admin/showtime-seats");
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute ShowtimeForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", ")));
            prepareFormModel(model, form);
            return adminView(model, "views/admin/showtime-form");
        }

        try {
            adminShowtimeService.save(form);
            redirectAttributes.addFlashAttribute("successMessage", "Lưu suất chiếu thành công");
            return "redirect:/admin/showtimes";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            prepareFormModel(model, form);
            return adminView(model, "views/admin/showtime-form");
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            adminShowtimeService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa suất chiếu");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/admin/showtimes";
    }

    @PostMapping("/{id}/seats/{showSeatId}/status")
    public String updateSeatStatus(
            @PathVariable Integer id,
            @PathVariable Integer showSeatId,
            @RequestParam ShowSeatStatus status,
            RedirectAttributes redirectAttributes) {
        try {
            adminSeatMonitorService.updateSeatStatus(id, showSeatId, status);
            redirectAttributes.addFlashAttribute("successMessage", "Đã cập nhật trạng thái ghế");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/admin/showtimes/" + id + "/seats";
    }

    private void prepareFormModel(Model model, ShowtimeForm form) {
        model.addAttribute("form", form);
        model.addAttribute("movies", adminShowtimeService.findMovies());
        model.addAttribute("rooms", adminShowtimeService.findRooms());
    }

    private static String adminView(Model model, String content) {
        model.addAttribute("content", content);
        return "layouts/layout-admin";
    }
}
