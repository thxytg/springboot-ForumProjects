package com.zxz.bbs.utils;

import com.zxz.bbs.pojo.UserAbility;

public class UserAbilityUtil {
    public static UserAbility init(UserAbility row,int userId)
    {
        row.userId=userId;
        row.banFlag=0;
        row.banDate=null;

        //每天十张图片，5个帖子，20条回复；
        row.imageCount=row.imageMax=10;
        row.msgCount=row.msgMax=5;
        row.replyCount=row.replyMax=20;

        return row;

    }
}
