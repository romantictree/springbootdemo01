package com.example.springbootdemo01.controller;

import com.example.springbootdemo01.bean.Skill;
import com.example.springbootdemo01.common.Result;
import com.example.springbootdemo01.common.StatusCode;
import com.example.springbootdemo01.service.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    private ISkillService skillService;

    @GetMapping("/findAll/{skillType}")
    public Result findAll(@PathVariable("skillType") Long id){
        Set<Skill> allSkill = skillService.findAllSkill(id);
        return new Result(true, StatusCode.OK,"成功",allSkill);
    }

    @GetMapping("/findAllByUser")
    public Result findAllByUser(@RequestParam("userId") Long id){   //后面那个才是别名
        Set<String> allSkills = skillService.findAllByUser(id);
        return  new Result(true,StatusCode.OK,"成功",allSkills);
    }

    @GetMapping("/fillAllWant")
    public Result findAllWant(@RequestParam("userId") Long id){
        Set<String> allSkills = skillService.findAllWant(id);
        return new Result(true,StatusCode.OK,"成功",allSkills);
    }
}
