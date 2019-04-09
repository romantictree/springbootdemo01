package com.example.springbootdemo01.repository;

import com.example.springbootdemo01.bean.Assess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AssessRepository extends JpaRepository<Assess,Long>, JpaSpecificationExecutor<Assess> {

    @Query(value = "select assess_id from t_assess inner join t_course on t_course.course_id = t_assess.course_id where t_course.course_id=:course_id",
            nativeQuery = true)
    public Set<Long> findAssessesByCourseId(@Param("course_id") Long courseId);

    public Assess findByAssessId(Long assessId);
}
