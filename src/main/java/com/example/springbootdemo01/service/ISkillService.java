package com.example.springbootdemo01.service;

import com.example.springbootdemo01.bean.Skill;
import com.example.springbootdemo01.bean.SkillType;

import java.util.Set;

public interface ISkillService {
    public Set<Skill> findAllSkill(Long id);

    public Set<String> findAllByUser(Long userId);

    public Set<String> findAllWant(Long userId);
}
