package com.matjongchan.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class mainController {

    @GetMapping("/main")
    public String mainPage(){
        System.out.println("111");
        return "mainPage";
    }

}
