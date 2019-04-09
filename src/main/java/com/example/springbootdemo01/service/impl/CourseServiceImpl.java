package com.example.springbootdemo01.service.impl;

import com.example.springbootdemo01.common.RedisCache;
import com.example.springbootdemo01.repository.CourseRepository;
import com.example.springbootdemo01.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Set<String> findAllTeach(Long userId) {
//        Set<String> allTeach = redisCache.getSet("t_user_courseteach"+userId);
//        if(allTeach == null){
//           allTeach = courseRepository.findAllTeach(userId);
//           redisCache.setSet("t_user_courseteach"+userId,allTeach,100);
//        }
//        return allTeach;
        Set<String> allTeach = redisTemplate.opsForSet().members("t_user_courseteach"+userId);
        if(allTeach.isEmpty()){
            allTeach = courseRepository.findAllTeach(userId);
            redisTemplate.opsForSet().add("t_user_courseteach"+userId,allTeach,10,TimeUnit.MINUTES);
        }
        return allTeach;
    }

    @Override
    public Set<String> findAllStudy(Long userId) {
        Set<String> allStudy = courseRepository.findAllStudy(userId);
        return allStudy;
    }
}
