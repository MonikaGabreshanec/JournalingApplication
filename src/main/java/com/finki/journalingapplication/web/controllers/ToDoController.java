package com.finki.journalingapplication.web.controllers;

import com.finki.journalingapplication.model.User;
import com.finki.journalingapplication.repository.UserRepository;
import com.finki.journalingapplication.service.ToDoService;
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
public class ToDoController {
    private final UserRepository userRepository;
    private final ToDoService toDoService;

    @GetMapping("/todos")
    private String getNotes(Model model, HttpSession session){
        User currentUser = (User) session.getAttribute("User");
        model.addAttribute("user", userRepository.findById(currentUser.getId()).get());
        model.addAttribute("todos", toDoService.getAllTodos(currentUser));
        return "todo.html";
    }
    @PostMapping("/todos")
    public String addTodo(@RequestParam String title, HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("User");
        toDoService.save(title,currentUser);
        return "redirect:/todos";
    }
}

