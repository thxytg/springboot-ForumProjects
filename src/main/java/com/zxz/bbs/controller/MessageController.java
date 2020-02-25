package com.zxz.bbs.controller;

import com.alibaba.fastjson.JSONObject;
import com.zxz.bbs.af.spring.AfRestData;
import com.zxz.bbs.af.spring.AfRestError;
import com.zxz.bbs.mapper.MessageMapper;
import com.zxz.bbs.mapper.UserMapper;
import com.zxz.bbs.pojo.Message;
import com.zxz.bbs.pojo.User;
import com.zxz.bbs.pojo.UserAbility;
import com.zxz.bbs.utils.MapUtil;
import com.zxz.bbs.utils.MyUtil;
import com.zxz.bbs.utils.SaveRowMessageUtil;
import com.zxz.bbs.utils.TimeStrUtil;
import org.apache.catalina.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {

    //发表主贴
    @GetMapping("/u/message/add")
    public String add(Model model, @SessionAttribute User user) {

        return "/message/add";
    }

    @Resource
    MessageMapper messageMapper;


    @PostMapping("/u/message/save.do")
    public Object save(@RequestBody Message row, @SessionAttribute User user,
                       @SessionAttribute UserAbility userAbility, HttpServletRequest request) {

        //保存之前检查发帖权力
        if(userAbility.msgCount<=0)
            return new AfRestError("今天发帖数量太多，请明天重试，或联系管理员！ MAX="+userAbility.msgMax);
        Message row2 = SaveRowMessageUtil.SaveRowMessageUtil(row, user);
        messageMapper.addRowMessage(row2);

        //扣除当天发帖，数量-1
        userAbility.msgCount -= 1;
        messageMapper.updateUserAbilityMsgCount(user.id);


        return new AfRestData("");

    }

    //查询帖子列表

    @GetMapping("/message/list")
    public String list(Model model, HttpSession session, Integer pageNumber) {
        if (pageNumber == null) pageNumber = 1;

        // count:符合条件的记录一共有多少条
        int count = 0;
        if (true) {
            String[] result = messageMapper.queryMessageCount();
            count = Integer.valueOf(result[0]);

        }

        int pageSize = 10;
        int pageCount = count / pageSize;
        if (count % pageSize != 0) pageCount += 1;

        // 查询
        int startIndex = pageSize * (pageNumber - 1);

        // 返回的每一行记录是一个Map<String,String>
        List<Map> messageList = messageMapper.queryAllMessage(startIndex, pageSize);


        // 需要进一步处理处理成前端需要的格式
//        TimeStrUtil tts = new TimeStrUtil();
//        for(Map m : messageList)
//        {
//            String timeCreate = (String)m.get("timeCreate");
//            m.put("timeCreate", tts.format(timeCreate));
//
//            String replyTime = (String)m.get("replyTime");
//            m.put("replyTime", tts.format(replyTime));
//
//            MapUtil mapu = new MapUtil(m);
//            mapu.asInt("topFlag", 0);
//            mapu.asInt("niceFlag", 0);
//        }

        model.addAttribute("messageList", messageList);
        model.addAttribute("count", count); // 共习题数量
        model.addAttribute("pageCount", pageCount); // 总页数
        model.addAttribute("pageNumber", pageNumber); // 当前页码：1,2,3...
        model.addAttribute("baseIndex", count - pageSize * (pageNumber - 1)); // 共习题数量

        return "message/list";
    }

    //删除帖子
    @PostMapping("/u/message/userSetFlags.do")
    public Object delMessage(@SessionAttribute User user,
                             @RequestBody JSONObject jsonObject) throws Exception {
        long id = jsonObject.getLongValue("msgId");
        String cmd = jsonObject.getString("cmd");
        if (cmd.equals("del")) {
            //用户自己删帖
            Message ref = messageMapper.queryMessageCreator(id);
            if (ref.creator.intValue() != user.id) {
                return new AfRestError("无权删除别人的帖子");
            }
            //删除此贴，以及它的子贴
            long ref1 = id;
            messageMapper.temporaryDelMessage(id, ref1);
        }
        return new AfRestData("");


    }

    //帖子管理：置顶，加精，删除
    @PostMapping("/u/message/suSetFlags.do")
    public Object suSetFlanges(@SessionAttribute User user, @RequestBody JSONObject jsonObject) {
        if (user.isAdmin == null || user.isAdmin == false)
            return new AfRestError("仅管理员可操作");

        long msgId = jsonObject.getLongValue("msgId");
        long ref1 = msgId;
        String cmd = jsonObject.getString("cmd");
        Message ref = messageMapper.queryMessageCreator(msgId);
        if (ref == null)
            return new AfRestError("无此记录，msgId=" + msgId);
        if (cmd.equals("top"))
            messageMapper.updateTopMessage(msgId);
        else if (cmd.equals("nice"))
            messageMapper.updateNiceMessage(msgId);
        else if (cmd.equals("del"))
            messageMapper.temporaryDelMessage(msgId, ref1);

        return new AfRestData("");

    }


    // 附件图片存储路径 示例 201911/01/15725791906031/
    public static String makeStorePath() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM/dd/");
        return sdf.format(new Date()) + MyUtil.guid2() + "/";
    }


}
