package hethongwebbanvexemphim.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(basePackages = "hethongwebbanvexemphim.controller.admin")
public class AdminLayoutAdvice {

    @ModelAttribute("adminPath")
    public String adminPath(HttpServletRequest request) {
        return request.getRequestURI();
    }
}
