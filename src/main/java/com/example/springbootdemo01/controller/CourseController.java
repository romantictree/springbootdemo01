package com.example.springbootdemo01.controller;

import com.example.springbootdemo01.common.Result;
import com.example.springbootdemo01.common.StatusCode;
import com.example.springbootdemo01.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @GetMapping("/findAllTeach")
    public Result findAllTeach(@RequestParam("userId") Long userId){
        Set<String> allTeach = courseService.findAllTeach(userId);
        return new Result(true, StatusCode.OK,"成功",allTeach);
    }

    @GetMapping("/findAllStudy")
    public Result findAllStudy(@RequestParam("userId") Long userId){
        Set<String> allStudy = courseService.findAllStudy(userId);
        return new Result(true,StatusCode.OK,"成功",allStudy);
    }
}
