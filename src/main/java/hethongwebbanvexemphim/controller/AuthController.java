package hethongwebbanvexemphim.controller;

import hethongwebbanvexemphim.dto.request.RegisterRequest;
import hethongwebbanvexemphim.entity.Role;
import hethongwebbanvexemphim.entity.User;
import hethongwebbanvexemphim.entity.enums.Gender;
import hethongwebbanvexemphim.repository.RoleRepository;
import hethongwebbanvexemphim.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/dang-nhap")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        if (error != null) {
            model.addAttribute("error", "Email hoặc mật khẩu không đúng!");
        }
        if (logout != null) {
            model.addAttribute("message", "Đăng xuất thành công!");
        }
        return "views/dang-nhap";
    }

    @GetMapping("/dang-ky")
    public String registerPage(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "views/dang-ky";
    }

    @PostMapping("/dang-ky")
    public String register(@Valid @ModelAttribute RegisterRequest request, BindingResult bindingResult, Model model) {
        // Check if password and confirm password match
        if (request.getPassword() != null && !request.getPassword().equals(request.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Mật khẩu nhập lại không khớp");
        }

        // Kiểm tra email đã tồn tại
        if (userRepository.existsByEmail(request.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "Email đã được sử dụng");
        }

        // Kiểm tra số điện thoại đã tồn tại
        if (userRepository.existsByPhone(request.getPhone())) {
            bindingResult.rejectValue("phone", "error.phone", "Số điện thoại đã được sử dụng");
        }

        if (bindingResult.hasErrors()) {
            return "views/dang-ky";
        }

        // Lấy role CUSTOMER
        Optional<Role> customerRoleOpt = roleRepository.findByRoleName("CUSTOMER");
        if (customerRoleOpt.isEmpty()) {
            model.addAttribute("error", "Có lỗi hệ thống, vui lòng thử lại sau");
            return "views/dang-ky";
        }

        // Tạo user mới với mật khẩu đã mã hóa SHA-256
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .gender(request.getGender())
                .birthday(request.getBirthday())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(customerRoleOpt.get())
                .build();

        userRepository.save(user);

        // Chuyển hướng đến trang đăng nhập với thông báo thành công
        return "redirect:/dang-nhap?success=true";
    }
}
