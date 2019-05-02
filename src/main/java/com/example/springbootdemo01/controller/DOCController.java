package com.example.springbootdemo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DOCController {
    @RequestMapping("/test1")
    public String hello(){
        return "/user/home";
    }
}
