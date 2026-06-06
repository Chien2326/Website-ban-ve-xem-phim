package hethongwebbanvexemphim.controller;

import hethongwebbanvexemphim.service.admin.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminDashboardService adminDashboardService;

    @GetMapping({"", "/home"})
    public String adminHome(
            Authentication authentication,
            @RequestParam(name = "period", required = false, defaultValue = "day") String period,
            Model model) {
        model.addAttribute("stats", adminDashboardService.getDashboard(period));
        model.addAttribute("content", "views/admin/home");
        return "layouts/layout-admin";
    }
}