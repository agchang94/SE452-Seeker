package se452.group9.seeker.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "pword5";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);

        
    }
    
}
