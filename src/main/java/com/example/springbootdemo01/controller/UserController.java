package com.example.springbootdemo01.controller;

import com.example.springbootdemo01.bean.User;
import com.example.springbootdemo01.common.JsonView;
import com.example.springbootdemo01.service.IUserService;
import com.example.springbootdemo01.utils.EncryptUtil;
import com.example.springbootdemo01.utils.SessionContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 登录页面
     */
    @RequestMapping(value = "/login")
    public  ModelAndView login(){
        if(SessionContext.isLogin()){
            return new ModelAndView("redirect:templates/index.ftl");
        }
        return new ModelAndView("auth/login");
    }

    @RequestMapping(value = "/doLogin")
    public ModelAndView doLogin(User user, String identiryCode, HttpServletRequest request){

        //如果已经登录过
        if(SessionContext.getAuthUser() != null){
            return new ModelAndView("redirect:/");
        }

        //验证码判断
//        if(identiryCode!=null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))){
//            ModelAndView mv = new ModelAndView("auth/login");
//            mv.addObject("errcode", 1);
//            return mv;
//        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), EncryptUtil.encodedByMD5(user.getPassword()));
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);//shiro实现登录
            return new ModelAndView("redirect:/");
        }catch(AuthenticationException e){ //登录失败
            ModelAndView mv = new ModelAndView("/user/login");
            mv.addObject("errcode", 2);
            return mv;
        }
    }

    /**
     * 注册页面
     */
    @RequestMapping(value = "/register")
    public  ModelAndView register(){
        if(SessionContext.isLogin()){
            return new ModelAndView("redirect:/index.ftl");
        }
        return new ModelAndView("auth/register");
    }

    /**
     * 实现注册
     */
    @RequestMapping(value = "/doRegister")
    @ResponseBody
    public String doRegister(User authUser, String identiryCode, HttpServletRequest request) {
        //验证码判断
//        if(identiryCode!=null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))){
//            return JsonView.render(2);
//        }

        User tmpUser = userService.findByUsername(authUser.getUsername());
        if(tmpUser != null){
            return JsonView.render(1);
        }else{
            authUser.setPassword(EncryptUtil.encodedByMD5(authUser.getPassword()));
            userService.createSelectivity(authUser);
            return JsonView.render(0);
        }
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        SessionContext.shiroLogout();
        return new ModelAndView("redirect:/");
    }
}
