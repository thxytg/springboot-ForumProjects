package com.zxz.bbs.controller;

import com.alibaba.fastjson.JSONObject;
import com.zxz.bbs.af.spring.AfRestData;
import com.zxz.bbs.mapper.UserMapper;
import com.zxz.bbs.pojo.User;
import com.zxz.bbs.pojo.UserAbility;
import com.zxz.bbs.utils.UserLoginUtil;
import javafx.concurrent.Worker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserProfileController {

    //用户个人信息
    @GetMapping("/u/profile/edit")
    public String UserProfile(@SessionAttribute User user,
                              @SessionAttribute UserAbility userAbility, Model model)
    {

        model.addAttribute("user",user);
        model.addAttribute("userAbility",userAbility);
        return "/user/profile";
    }

    //用户密码修改
    @GetMapping("/u/profile/password")
    public String passWord(Model model,@SessionAttribute User user)
    {
        model.addAttribute("user",user);

        return "/user/passwordChange";
    }

    @Resource
    UserMapper userMapper;
    @PostMapping("/u/password.do")
    public Object passWordChange(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response, @SessionAttribute User user)
    {
        int id = user.id;
        String password = jsonObject.getString("password");
        userMapper.changePassWord(password,id);

        return new  AfRestData("");
    }






}
