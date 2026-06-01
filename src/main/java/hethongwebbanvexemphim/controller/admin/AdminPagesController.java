package hethongwebbanvexemphim.controller.admin;

import hethongwebbanvexemphim.entity.enums.BookingStatus;
import hethongwebbanvexemphim.repository.RoomRepository;
import hethongwebbanvexemphim.repository.SeatRepository;
import hethongwebbanvexemphim.repository.TicketDetailRepository;
import hethongwebbanvexemphim.service.admin.AdminBookingService;
import hethongwebbanvexemphim.service.admin.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminPagesController {

    private final AdminDashboardService adminDashboardService;
    private final AdminBookingService adminBookingService;
    private final RoomRepository roomRepository;
    private final SeatRepository seatRepository;
    private final TicketDetailRepository ticketDetailRepository;

    @GetMapping
    public String dashboard(@RequestParam(defaultValue = "week") String period, Model model) {
        model.addAttribute("stats", adminDashboardService.getDashboard(period));
        return adminView(model, "views/admin/home");
    }



    @GetMapping("/bookings")
    public String bookings(
            @RequestParam(required = false) BookingStatus status,
            @RequestParam(required = false) String keyword,
            Model model) {
        model.addAttribute("items", adminBookingService.searchBookings(status, keyword));
        model.addAttribute("filterStatus", status);
        model.addAttribute("filterKeyword", keyword);
        return adminView(model, "views/admin/bookings");
    }

    @PostMapping("/bookings/{id}/status")
    public String updateBookingStatus(
            @PathVariable Integer id,
            @RequestParam BookingStatus status) {
        adminBookingService.updateStatus(id, status);
        return "redirect:/admin/bookings";
    }

    @GetMapping("/tickets")
    public String tickets(Model model) {
        model.addAttribute("items", ticketDetailRepository.findAllForAdmin());
        return adminView(model, "views/admin/tickets");
    }

    private static String adminView(Model model, String content) {
        model.addAttribute("content", content);
        return "layouts/layout-admin";
    }
}
