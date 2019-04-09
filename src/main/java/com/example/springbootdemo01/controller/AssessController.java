package com.example.springbootdemo01.controller;

import com.example.springbootdemo01.bean.Assess;
import com.example.springbootdemo01.bean.Course;
import com.example.springbootdemo01.common.Result;
import com.example.springbootdemo01.common.StatusCode;
import com.example.springbootdemo01.service.IAssessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/assess")
public class AssessController {
    @Autowired
    private IAssessService assessService;

    @RequestMapping("/getAllAssesses/{pageSize}/{pageNum}")
    public Result findByAssessIds(@RequestBody Assess assess,@PathVariable int pageSize,@PathVariable int pageNum){
        Page<Assess> byAssessIds = assessService.findByAssessIds(assess,pageSize,pageNum);
        return new Result(true, StatusCode.OK,"成功",byAssessIds);
    }

    @GetMapping("/getByCourseId")
    public Result findAllAssessesByCouresId(Long courseId){
        Set<Assess> assesses = assessService.findAllAssessesByCourseId(courseId);
        return new Result(true,StatusCode.OK,"成功",assesses);
    }
}
