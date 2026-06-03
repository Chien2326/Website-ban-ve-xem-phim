package hethongwebbanvexemphim.security;

import hethongwebbanvexemphim.entity.User;
import hethongwebbanvexemphim.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("=== Trying to load user with email: " + email + " ===");
        
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        
        System.out.println("=== User found! ===");
        System.out.println("  Email: " + user.getEmail());
        System.out.println("  Password hash: " + user.getPasswordHash());
        System.out.println("  Role: " + user.getRole().getRoleName());
        
        // Convert our User entity to Spring Security's UserDetails
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPasswordHash())
                .roles(user.getRole().getRoleName())
                .build();
    }
}
