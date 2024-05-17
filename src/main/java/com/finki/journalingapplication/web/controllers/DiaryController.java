package com.finki.journalingapplication.web.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping
public class DiaryController {
    @GetMapping("/mainpage")
    private String getDiaryPage(){
        return "mainpage";
    }


}
