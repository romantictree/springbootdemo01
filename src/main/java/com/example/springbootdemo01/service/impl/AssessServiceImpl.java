package com.example.springbootdemo01.service.impl;

import com.example.springbootdemo01.bean.Assess;
import com.example.springbootdemo01.repository.AssessRepository;
import com.example.springbootdemo01.service.IAssessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import java.util.*;


@Service
public class AssessServiceImpl implements IAssessService {

    @Autowired
    private AssessRepository assessRepository;
    @Override
    public Page<Assess> findByAssessIds(Assess assess,int pageSize,int pageNum) {
        Pageable pageRequest = new PageRequest(pageNum, pageSize);
        Specification<Assess> specification = new Specification<Assess>() {
            @Override
            public Predicate toPredicate(Root<Assess> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (assess.getCreateTime() != null) {
                    Predicate predicate = (Predicate) cb.equal(root.get("createTime").as(Date.class), assess.getCreateTime());
                    list.add(predicate);
                }
                Predicate[] parr = new Predicate[list.size()];
                //把集合中的属性复制到数组中
                parr = list.toArray(parr);
                return cb.and(parr);
            }
        };
        Page<Assess> assesses = assessRepository.findAll(specification,pageRequest);
        return  assesses;
    }

    @Override
    public Set<Assess> findAllAssessesByCourseId(Long courseId) {
        Set<Long> assessesByCourseId = assessRepository.findAssessesByCourseId(courseId);
        Set<Assess> assesses = new HashSet<>();
        for(Long a:assessesByCourseId){
            Assess assess = assessRepository.findByAssessId(a);
            assesses.add(assess);
        }
        return assesses;
    }
}
