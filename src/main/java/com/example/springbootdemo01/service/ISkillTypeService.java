package com.example.springbootdemo01.service;

import com.example.springbootdemo01.bean.Skill;
import com.example.springbootdemo01.bean.SkillType;

import java.util.List;
import java.util.Set;

public interface ISkillTypeService {
    public void addSkillType(String sname);
    public void delSkilllType(Long i);
    public List<SkillType> findSkillTypes(Long[] ids);
    public SkillType findSkillType(Long id);
}
