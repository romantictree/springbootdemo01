package com.example.springbootdemo01.service.impl;

import com.example.springbootdemo01.bean.Skill;
import com.example.springbootdemo01.bean.SkillType;
import com.example.springbootdemo01.repository.SkillRepository;
import com.example.springbootdemo01.repository.SkillTypeRepository;
import com.example.springbootdemo01.service.ISkillService;
import com.example.springbootdemo01.service.ISkillTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SkillServiceImpl implements ISkillService {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private SkillTypeRepository skillTypeRepository;

    @Override
    public Set<Skill> findAllSkill(Long id) {
        SkillType skillType = skillTypeRepository.findById(id).get();
        return skillRepository.findBySkillType(skillType);
    }

    @Override
    public Set<String> findAllByUser(Long userId) {
        Set<String> allSkills = skillRepository.findAllByUser(userId);
        return allSkills;
    }

    @Override
    public Set<String> findAllWant(Long userId) {
        Set<String> allSkills = skillRepository.findAllWantByUser(userId);
        return allSkills;
    }
}
