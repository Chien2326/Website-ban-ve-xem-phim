package hethongwebbanvexemphim.controller.admin;

import hethongwebbanvexemphim.dto.admin.MovieForm;
import hethongwebbanvexemphim.service.admin.AdminMovieService;
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
@RequestMapping("/admin/movies")
@RequiredArgsConstructor
public class AdminMovieController {

    private final AdminMovieService adminMovieService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", adminMovieService.findAll());
        return adminView(model, "views/admin/movies");
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        prepareFormModel(model, new MovieForm());
        return adminView(model, "views/admin/movie-form");
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        prepareFormModel(model, adminMovieService.getForm(id));
        return adminView(model, "views/admin/movie-form");
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute MovieForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", ")));
            prepareFormModel(model, form);
            return adminView(model, "views/admin/movie-form");
        }

        try {
            adminMovieService.save(form);
            redirectAttributes.addFlashAttribute("successMessage", "Lưu phim thành công");
            return "redirect:/admin/movies";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            prepareFormModel(model, form);
            return adminView(model, "views/admin/movie-form");
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            adminMovieService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa phim");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/admin/movies";
    }

    private void prepareFormModel(Model model, MovieForm form) {
        model.addAttribute("form", form);
        model.addAttribute("genres", adminMovieService.findAllGenres());
        model.addAttribute("movieStatuses", hethongwebbanvexemphim.entity.enums.MovieStatus.values());
        model.addAttribute("ageRatings", hethongwebbanvexemphim.entity.enums.AgeRating.values());
    }

    private static String adminView(Model model, String content) {
        model.addAttribute("content", content);
        return "layouts/layout-admin";
    }
}
