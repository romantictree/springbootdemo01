package com.example.springbootdemo01.controller;

import com.example.springbootdemo01.common.Result;
import com.example.springbootdemo01.common.StatusCode;
import com.example.springbootdemo01.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @GetMapping("/findAllTeach")
    public ModelAndView findAllTeach(@RequestParam("userId") Long userId){
        Set<String> allTeach = courseService.findAllTeach(userId);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/collect");
        mv.addObject("pageForm1",allTeach);
        return mv;
    }

    @GetMapping("/findAllStudy")
    public ModelAndView findAllStudy(@RequestParam("userId") Long userId){
        Set<String> allStudy = courseService.findAllStudy(userId);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/course");
        mv.addObject("pageForm",allStudy);
        return mv;
    }
}
