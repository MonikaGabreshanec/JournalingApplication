package com.finki.journalingapplication.web.controllers;

import com.finki.journalingapplication.model.Diary;
import com.finki.journalingapplication.service.DiaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping
public class MainPageController {

    private final DiaryService diaryService;

    @GetMapping("/mainpage")
    private String getDiaryPage(Model model){


        List<Diary> diaries = this.diaryService.findAll();
        model.addAttribute("diaries", diaries);
        return "mainpage";

    }




}
