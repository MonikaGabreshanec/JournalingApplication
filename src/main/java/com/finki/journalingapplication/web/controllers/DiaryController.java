package com.finki.journalingapplication.web.controllers;

import com.finki.journalingapplication.model.User;
import com.finki.journalingapplication.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping
public class DiaryController {
    private final UserRepository userRepository;
    @GetMapping("/mainpage")
    private String getDiaryPage(HttpSession session,
                                Model model,
                                @RequestParam(required = false) Long id){
        User currentUser = (User) session.getAttribute("User");
        model.addAttribute("user", userRepository.findById(currentUser.getId()).get());
        return "mainpage";
    }
}
