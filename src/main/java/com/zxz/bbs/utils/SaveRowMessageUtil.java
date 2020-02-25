package com.zxz.bbs.utils;

import com.alibaba.fastjson.JSONObject;
import com.zxz.bbs.controller.MessageController;
import com.zxz.bbs.pojo.Message;
import com.zxz.bbs.pojo.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveRowMessageUtil {

    //创建一条主贴对象
    public static Message SaveRowMessageUtil(Message row, User user) {
        // title, content字段由前端传送过来
        row.creator = user.id; // 创建者
        row.cat1 = row.cat2 = row.cat3 = 0;
        row.ref1 = 0L; // ref1=0表示这一条为主帖(楼主)
        row.ref2 = 0L;
        row.timeCreate = new Date(); // 发贴时间
        row.numReply = 0;
        row.numLike = 0;
        row.imgCount = 0;
        row.niceFlag = 0;
        row.topFlag = 0;
        row.banFlag = false;
        row.delFlag = false;
        row.closeFlag = false;
        row.replyUser = user.id;
        row.replyTime = row.timeCreate;
        row.replyText = row.title;
        row.replyName = user.name;
        row.storePath = MessageController.makeStorePath();
        row.img1=TmpFile.moveTmpToStore(row.img1,row.storePath);
        row.img2=TmpFile.moveTmpToStore(row.img2,row.storePath);
        row.img3=TmpFile.moveTmpToStore(row.img3,row.storePath);

        return row;
    }

    //创建一条回复主贴对象
    public static Message SaveRowReplyMessage( Message ref, JSONObject jreq, User user)
    {
        Message row = new Message();
        row.creator = user.id; // 创建者
        row.cat1 = ref.cat1;
        row.cat2 = ref.cat2;
        row.cat3 = ref.cat3;
        row.title = jreq.getString("title"); // title即缩略显示
        row.content = jreq.getString("content"); // 回复内容
        row.ref1= ref.getId(); // 父级ID (即1楼原文ID)
        row.ref2=0L;
        row.timeCreate=new Date();
        row.numReply=0;
        row.numLike =0;
        row.imgCount = 0;
        row.niceFlag = 0;
        row.topFlag = 0;
        row.banFlag = false;
        row.delFlag = false;
        row.closeFlag = false;
        row.timeCreate = new Date();
        row.storePath = MessageController.makeStorePath();

        if(row.img1==null) row.img1="";
        if(row.img2==null) row.img2="";
        if(row.img3==null) row.img3="";

        return row;
    }
}
