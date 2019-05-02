package com.example.springbootdemo01.service.impl;

import com.example.springbootdemo01.bean.User;
import com.example.springbootdemo01.bean.dto.UserDTO;
import com.example.springbootdemo01.repository.UserRepository;
import com.example.springbootdemo01.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUser() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public List<User> findUserWithCourse() {
        return userRepository.findUserWithCourse();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByUsernameAndPassword(User user) {
        return userRepository.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void createSelectivity(User user) {
        userRepository.createSelectivity(user.getUsername(),user.getPassword());
    }


}
