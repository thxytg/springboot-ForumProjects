package com.zxz.bbs.controller;

import com.alibaba.fastjson.JSONObject;
import com.zxz.bbs.af.spring.AfRestData;
import com.zxz.bbs.af.spring.AfRestError;
import com.zxz.bbs.pojo.User;
import com.zxz.bbs.mapper.UserMapper;
import com.zxz.bbs.pojo.UserAbility;
import com.zxz.bbs.utils.UserAbilityUtil;
import com.zxz.bbs.utils.UserLoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    //注册页模板
    @RequestMapping("/register")
    public String register(Model model) {
        return "user/register";
    }

    //取得前端注册信息存入数据库
    @Resource
    UserMapper userMapper;

    @PostMapping("/register.do")
    public Object register(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {

        user.name = user.name.trim();
        user.password = user.password.trim();
        user.vip = (byte) 0;
        user.vipName = "";
        user.isAdmin = false;
        user.level = 0;
        System.out.println(user.toString());
        try {
            userMapper.addUser(user);
            UserAbility userAbility = UserAbilityUtil.init(new UserAbility(), user.id);
            userMapper.addUserAbility(userAbility);

        } catch (Exception e) {
            // name重复时抛出异常

            return new AfRestError("用户名已被占用!");
        }


        return new AfRestData("");

    }

    //登录页模板
    @RequestMapping("/login")
    public String login(Model model, String returnUrl) {
        if (returnUrl == null) returnUrl = "";
        model.addAttribute("returnUrl", returnUrl);
        return "user/login";
    }

    //数据库查询登录信息

    @PostMapping("/login.do")
    public Object login(@RequestBody JSONObject jreq, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = jreq.getString("name");
        String password = jreq.getString("password");

        User user = userMapper.getOneUser(name, password);
        if (user == null)
            return new AfRestError("用户名或密码不匹配");

        //设置当前会话
        UserLoginUtil.login(session, user);
        //添加用户权限到会话
        UserAbility userAbility = userMapper.getUserAbilityFromUserId(user.id);
        session.setAttribute("userAbility", userAbility);


        return new AfRestData("");
    }


    //退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        UserLoginUtil.logout(session);

        return "redirect:/message/list";
    }


}
