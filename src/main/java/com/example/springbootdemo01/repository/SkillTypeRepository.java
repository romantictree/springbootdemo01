package com.example.springbootdemo01.repository;

import com.example.springbootdemo01.bean.Skill;
import com.example.springbootdemo01.bean.SkillType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SkillTypeRepository extends JpaRepository<SkillType,Long>, JpaSpecificationExecutor<SkillType> {
}
