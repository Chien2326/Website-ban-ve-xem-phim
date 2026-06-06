package hethongwebbanvexemphim.config;

import hethongwebbanvexemphim.security.CustomUserDetailsService;
import hethongwebbanvexemphim.security.Sha256PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // Public pages - everyone can access
                .requestMatchers("/", "/dang-nhap", "/dang-ky", "/phim-dang-chieu", "/phim-sap-chieu", "/gioi-thieu", "/lien-he", "/test/**").permitAll()
                // Static resources
                .requestMatchers("/css/**", "/js/**", "/images/**", "/admin/assets/**").permitAll()
                // Admin pages - only ADMIN can access
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // All other requests need authentication
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/dang-nhap")
                .loginProcessingUrl("/dang-nhap")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler((request, response, authentication) -> {
                    // Check if user has ADMIN role
                    boolean isAdmin = authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
                    if (isAdmin) {
                        response.sendRedirect("/admin/home");
                    } else {
                        response.sendRedirect("/");
                    }
                })
                .failureUrl("/dang-nhap?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/dang-xuat")
                .logoutSuccessUrl("/dang-nhap?logout=true")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use custom SHA-256 encoder to match the database
        return new Sha256PasswordEncoder();
    }
}
