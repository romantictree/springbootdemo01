package com.example.springbootdemo01.service;

import com.example.springbootdemo01.bean.User;
import com.example.springbootdemo01.bean.dto.UserDTO;

public interface IUserService {
    public User login(UserDTO userDTO);

    public User register(User user);
}
