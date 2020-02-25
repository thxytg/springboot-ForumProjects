package com.zxz.bbs.mapper;

import com.zxz.bbs.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Mapper
public interface MessageReplyMapper {
    //查询一条原帖用来回复引用
    Message getReplyMessage(long messageId);

    //回复后更新主帖
    void upDataMessage(HashMap<String,Object> map);

    //查询主贴及回复贴
    HashMap <String,Object> queryReplyList(int id);

    //查询回复帖子的数量
    String[] queryReplyMessageCount(int msgId);

    //查询回复贴，wocao起名要气疯
    List<Map> queryReplyList2(int messageId, int startIndex, int pageSize);
}
