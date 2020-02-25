package com.zxz.bbs.task;

import com.zxz.bbs.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component  // 关键1，将该工具类注册为组件，   加粗！！！
public class TokenUtil {
    @Resource
    public  MessageMapper messageMapper;


    public static TokenUtil tokenUtil;  // 关键2

    public TokenUtil() {
    }

    @PostConstruct
    public void init() {
        tokenUtil = this;
        tokenUtil.messageMapper = this.messageMapper;
    }
}



