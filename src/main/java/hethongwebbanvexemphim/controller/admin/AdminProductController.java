package hethongwebbanvexemphim.controller.admin;

import hethongwebbanvexemphim.dto.admin.ProductForm;
import hethongwebbanvexemphim.entity.enums.ProductStatus;
import hethongwebbanvexemphim.entity.enums.ProductType;
import hethongwebbanvexemphim.service.admin.AdminProductService;
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
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", adminProductService.findAll());
        return adminView(model, "views/admin/products");
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        prepareFormModel(model, new ProductForm());
        return adminView(model, "views/admin/product-form");
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        prepareFormModel(model, adminProductService.getForm(id));
        return adminView(model, "views/admin/product-form");
    }

    @PostMapping("/save")
    public String save(@ModelAttribute ProductForm form, Model model, RedirectAttributes redirectAttributes) {
        try {
            adminProductService.save(form);
            redirectAttributes.addFlashAttribute("successMessage", "Lưu sản phẩm thành công");
            return "redirect:/admin/products";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            prepareFormModel(model, form);
            return adminView(model, "views/admin/product-form");
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            adminProductService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa sản phẩm");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/admin/products";
    }

    private void prepareFormModel(Model model, ProductForm form) {
        model.addAttribute("form", form);
        model.addAttribute("productTypes", ProductType.values());
        model.addAttribute("productStatuses", ProductStatus.values());
    }

    private static String adminView(Model model, String content) {
        model.addAttribute("content", content);
        return "layouts/layout-admin";
    }
}
