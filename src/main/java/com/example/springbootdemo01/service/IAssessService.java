package com.example.springbootdemo01.service;

import com.example.springbootdemo01.bean.Assess;
import com.example.springbootdemo01.bean.Course;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface IAssessService {
    public Page<Assess> findByAssessIds(Assess assess,int pageSize,int pageNum);

    public Set<Assess> findAllAssessesByCourseId(Long courseId);
}
