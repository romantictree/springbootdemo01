package com.example.springbootdemo01.service.impl;

import com.example.springbootdemo01.bean.User;
import com.example.springbootdemo01.bean.dto.UserDTO;
import com.example.springbootdemo01.repository.UserRepository;
import com.example.springbootdemo01.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User login(UserDTO userDTO) {
        User userLogin = userRepository.findUserByUsername(userDTO.getUsername());
        if(userLogin!=null&&bCryptPasswordEncoder.matches(userDTO.getPassword(),userLogin.getPassword())){
            return userLogin;
        }
        return null;
    }

    @Override
    public User register(User user){
        if(user.getUsername()!=null&&user.getPassword()!=null){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        else {
            user=null;
        }
        return user;
    }
}
