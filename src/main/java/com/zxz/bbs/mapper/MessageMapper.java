package com.zxz.bbs.mapper;

import com.zxz.bbs.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface MessageMapper {
    //添加发表一条主贴
    Void addRowMessage(Message row);

    //查询帖子的数量
    String[] queryMessageCount();

    //查询所有的帖子
    List<Map> queryAllMessage(int startIndex, int pageSize);

    //按id查询Croeator判断是不是自己的帖子
   Message queryMessageCreator(long id);

   //临时删除帖子
    void temporaryDelMessage(long id,long ref1);

    //清理任务，帖子从数据库中按delFlog=1定时删除
     List<Map> queryMessageFromDelFlag();

     //按id删除帖子
    void delMessageFromId(long id);

    //按id置顶帖子
    void updateTopMessage(long id);

    //按id加精帖子
    void updateNiceMessage(long id);

    //用户发帖权限数量减一
    void updateUserAbilityMsgCount(long id);

    //每天凌晨回复用户发帖回复的权限，暂时先放在messageMapper中
    void userAbilityRestore();



}
