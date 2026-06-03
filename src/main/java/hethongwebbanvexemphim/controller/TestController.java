package hethongwebbanvexemphim.controller;

import hethongwebbanvexemphim.entity.User;
import hethongwebbanvexemphim.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private final UserRepository userRepository;

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/test/users")
    public List<User> getAllUsers() {
        System.out.println("=== TestController: Fetching all users ===");
        List<User> users = userRepository.findAllWithRole();
        for (User u : users) {
            System.out.println("  User: " + u.getEmail() + " | Pass: " + u.getPasswordHash() + " | Role: " + u.getRole().getRoleName());
        }
        return users;
    }
}
