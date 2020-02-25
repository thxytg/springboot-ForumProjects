package com.zxz.bbs.utils;

import com.zxz.bbs.mapper.UserMapper;
import com.zxz.bbs.pojo.User;
import com.zxz.bbs.pojo.UserAbility;
import com.zxz.bbs.task.TokenUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/*
用户登录相关的工具集
 */
public class UserLoginUtil {
    @Resource
    UserMapper userMapper;
    //登录以后，设置Session
    public static void login(HttpSession session , User user)throws Exception
    {
        session.setAttribute("user",user);
        //获取User_Ability

    }

    public static void logout(HttpSession session)
    {
        session.removeAttribute("user");
    }
}
