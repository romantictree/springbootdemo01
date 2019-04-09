package com.example.springbootdemo01.service.impl;

import com.example.springbootdemo01.bean.Skill;
import com.example.springbootdemo01.bean.SkillType;
import com.example.springbootdemo01.repository.SkillTypeRepository;
import com.example.springbootdemo01.service.ISkillTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SkillTypeServiceImpl implements ISkillTypeService {

    @Autowired
    private SkillTypeRepository skillTypeRepository;

    @Override
    public void addSkillType(String sname) {
        SkillType skillType = new SkillType();
        skillType.setTypeName(sname);
        skillTypeRepository.save(skillType);
    }

    @Override
    public void delSkilllType(Long i) {
        SkillType skillType = skillTypeRepository.findById(i).get();
        skillTypeRepository.save(skillType);
    }

    @Override
    public List<SkillType> findSkillTypes(Long[] ids) {
        return skillTypeRepository.findAll();
    }

    @Override
    public SkillType findSkillType(Long id) {
        return skillTypeRepository.findById(id).get();
    }
}
