package hethongwebbanvexemphim.controller;

import hethongwebbanvexemphim.entity.User;
import hethongwebbanvexemphim.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;

// @Controller
public class TaiKhoanController {

    private final UserService userService;

    public TaiKhoanController(UserService userService) {
        this.userService = userService;
    }

    // @GetMapping("/tai-khoan")
    // @PreAuthorize("isAuthenticated()")
    public String taiKhoan(Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("user", currentUser);
        return "views/tai-khoan";
    }
}
