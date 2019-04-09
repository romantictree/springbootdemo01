package com.example.springbootdemo01.controller;

import com.example.springbootdemo01.bean.User;
import com.example.springbootdemo01.bean.dto.UserDTO;
import com.example.springbootdemo01.common.Result;
import com.example.springbootdemo01.common.StatusCode;
import com.example.springbootdemo01.service.IUserService;
import com.example.springbootdemo01.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        User userLogin = userService.login(userDTO);
        if(userLogin==null){
            return new Result(false, StatusCode.LOGINERROR,"登录失败");
        }
        String token = jwtUtil.createJWT(userLogin.getUserId().toString(),userLogin.getUsername(),"vip");
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("role","vip");
        return new Result(true,StatusCode.OK,"登录成功",map);
    }


    @PostMapping("/register")
    public Result register(@RequestBody User user){
        try{
            User userRegister = userService.register(user);
            return new Result(true,StatusCode.OK,"注册成功");
        }catch(Exception e) {
            return new Result(false,StatusCode.ERROR,"注册失败");
        }

    }

}
