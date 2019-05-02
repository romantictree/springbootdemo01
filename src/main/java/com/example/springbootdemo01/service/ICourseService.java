package com.example.springbootdemo01.service;

import com.example.springbootdemo01.bean.Course;
import com.example.springbootdemo01.bean.CourseType;

import java.util.List;
import java.util.Set;

public interface ICourseService {

    public Set<String> findAllTeach(Long userId);

    public Set<String> findAllStudy(Long id);

    public List<Course> findFreeCourse();

    public List<Course> findWarCourse();
}
