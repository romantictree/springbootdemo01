package com.example.springbootdemo01.service;

import com.example.springbootdemo01.bean.User;
import com.example.springbootdemo01.bean.dto.UserDTO;

import java.util.List;

public interface IUserService {

    public List<User> findAllUser();

    public List<User> findUserWithCourse();

    public User findByUsername(String username);

    public User findUserByUsernameAndPassword(User user);

    public void createSelectivity(User user);
}
