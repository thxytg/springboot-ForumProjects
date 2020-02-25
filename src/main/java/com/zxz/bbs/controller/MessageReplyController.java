package com.zxz.bbs.controller;

import com.alibaba.fastjson.JSONObject;
import com.zxz.bbs.af.spring.AfRestData;
import com.zxz.bbs.mapper.MessageMapper;
import com.zxz.bbs.mapper.MessageReplyMapper;
import com.zxz.bbs.pojo.Message;
import com.zxz.bbs.pojo.User;
import com.zxz.bbs.utils.MapUtil;
import com.zxz.bbs.utils.SaveRowMessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MessageReplyController {

    //回复主贴
    @Resource
    MessageReplyMapper messageReplyMapper;
    @Resource
    MessageMapper messageMapper;

    @GetMapping("/u/reply/add")
    public String add(Model model, @SessionAttribute User user,
                      long messageId) throws Exception {
        //取出原帖
        Message ref = messageReplyMapper.getReplyMessage(messageId);
        model.addAttribute("ref", ref);
        if (ref == null)
            throw new Exception("无此记录：messageId=" + messageId);


        return "/reply/add";
    }

    //保存回复帖子
    @PostMapping("/u/reply/save.do")

    public Object save(@RequestBody JSONObject jreq, @SessionAttribute User user) {
        long msgId = jreq.getLongValue("msgId");
        Message ref = messageReplyMapper.getReplyMessage(msgId);

        //
        Message row = SaveRowMessageUtil.SaveRowReplyMessage(ref, jreq, user);
        messageMapper.addRowMessage(row);


        // 更新统计数据
        if (true) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 回复数+1，并且记录最后一次回复数据
            HashMap<String,Object> map = new HashMap<>();
            map.put("numReply","numReply+1");
            map.put("replyUser",row.creator);
            map.put("replyText", row.title);
            map.put("replyName",user.name);
            map.put("replyTime",sdf.format(new Date()));
            map.put("id",msgId);

            messageReplyMapper.upDataMessage(map);


        }


        return new AfRestData("");
    }

    @GetMapping("/reply/list")
    public String list(int messageId,Model model,Integer pageNumber)throws Exception
    {
        if(pageNumber==null) pageNumber = 1;
        HashMap<String,Object> ref = messageReplyMapper.queryReplyList(messageId);

        if(ref == null)
            throw new Exception("无此记录 ，messageId=" + messageId);
        else
        {
//            MapUtil mapu = new MapUtil(ref);
//            mapu.asInt("creator", 0);


            model.addAttribute("ref", ref);
        }

        // 查询总记录数
        int count = 0;
        if(true) {

            String[] result = messageReplyMapper.queryReplyMessageCount(messageId);
            count = Integer.valueOf( result[0]);
        }

        int pageSize = 20;
        int pageCount = count / pageSize;
        if(count % pageSize != 0) pageCount += 1;
        // 查询
        int startIndex = pageSize *(pageNumber-1);
        List<Map> messageList =messageReplyMapper.queryReplyList2(messageId,startIndex,pageSize);

        model.addAttribute("messageList", messageList);
        model.addAttribute("count", count); // 共习题数量
        model.addAttribute("pageCount", pageCount); // 总页数
        model.addAttribute("pageNumber", pageNumber); // 当前页码：1,2,3...
        model.addAttribute("baseIndex", pageSize * (pageNumber-1)); // 共习题数量



        return "/reply/list";
    }


}
