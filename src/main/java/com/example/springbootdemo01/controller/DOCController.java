package com.example.springbootdemo01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DOCController {
    @RequestMapping("/test1")
    public String hello(){
        return "hello";
    }
}
