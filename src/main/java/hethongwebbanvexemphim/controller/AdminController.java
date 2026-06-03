package hethongwebbanvexemphim.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping({"", "/home"})
    public String adminHome(Authentication authentication, Model model) {
        // Get logged-in username
        String username = authentication.getName();
        
        // Đưa các giá trị mặc định cho template để không lỗi
        model.addAttribute("stats", new Object() {
            public String getPeriod() { return "day"; }
            public String getPeriodLabel() { return "Hôm nay"; }
            public double getTotalRevenue() { return 0; }
            public double getPeriodRevenue() { return 0; }
            public int getTicketsSold() { return 0; }
            public int getPaidBookings() { return 0; }
            public int getPendingBookings() { return 0; }
            public int getCancelledBookings() { return 0; }
            public java.util.List<Object> getTopMovies() { return java.util.Collections.emptyList(); }
            public java.util.List<Object> getTopProducts() { return java.util.Collections.emptyList(); }
            public java.util.List<Object> getRevenueChart() { return java.util.Collections.emptyList(); }
        });
        
        model.addAttribute("content", "views/admin/home");
        return "layouts/layout-admin";
    }
}