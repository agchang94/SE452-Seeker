package se452.group9.seeker.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import se452.group9.seeker.model.Student;
import se452.group9.seeker.repo.StudentRepository;

@Controller
public class MainController {
    
    @Autowired
    private DaoAuthenticationProvider test;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
