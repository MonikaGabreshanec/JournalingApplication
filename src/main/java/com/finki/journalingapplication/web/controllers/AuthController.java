package com.finki.journalingapplication.web.controllers;

import com.finki.journalingapplication.model.User;
import com.finki.journalingapplication.model.exceptionPassword.InvalidPasswordException;
import com.finki.journalingapplication.model.exceptionPassword.types.PasswordNotMatchException;
import com.finki.journalingapplication.model.exceptionPassword.types.Username0rPasswordDoesntMatchException;
import com.finki.journalingapplication.model.exceptionPassword.types.UsernameExistsException;
import com.finki.journalingapplication.model.exceptionPassword.types.UsernameInPasswordException;
import com.finki.journalingapplication.repository.UserRepository;
import com.finki.journalingapplication.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    private String login() {
        return "login";
    }
    @GetMapping("/register")
    private String register() {
        return "register";
    }
    @PostMapping("/register")
    private String registerAccount(Model model, @RequestParam String name, @RequestParam String surname, @RequestParam String username, @RequestParam String password, @RequestParam String rpassword) {
        try {
            userService.registerAccount(name, surname, username, password, rpassword);
        } catch (PasswordNotMatchException | UsernameExistsException | InvalidPasswordException |
                 UsernameInPasswordException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
        return "redirect:/login";
    }
    @PostMapping("/login")
    private String loginAccount(Model model, HttpSession session, @RequestParam String username, @RequestParam String password) {
        try {
            User currentUser = userService.loginAccount(username, password);
            session.setAttribute("User", currentUser);
        } catch (Username0rPasswordDoesntMatchException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
        return "redirect:/mainpage";
    }
    @PostMapping("/auth-status")
    private String authStatus(HttpSession session) {
        User currentUser = (User) session.getAttribute("User");
        if (currentUser != null) {
            return "redirect:/mainpage";
        }
        return "redirect:/login";
    }
    @GetMapping("/logout")
    private String logoutAccount(HttpSession session) {
        session.invalidate();
        return "redirect:/homepage";
    }

}
