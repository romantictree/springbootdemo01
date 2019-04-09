package com.example.springbootdemo01.service;

import java.util.Set;

public interface ICourseService {

    public Set<String> findAllTeach(Long userId);

    public Set<String> findAllStudy(Long id);
}
