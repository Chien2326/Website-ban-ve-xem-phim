package hethongwebbanvexemphim.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        String adminPassword = "admin123";
        String customerPassword = "123456";
        
        String adminHash = encoder.encode(adminPassword);
        String customerHash = encoder.encode(customerPassword);
        
        System.out.println("Admin password hash (admin123): " + adminHash);
        System.out.println("Customer password hash (123456): " + customerHash);
    }
}
