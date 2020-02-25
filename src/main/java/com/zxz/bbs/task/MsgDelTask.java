package com.zxz.bbs.task;

import com.zxz.bbs.mapper.MessageMapper;
import com.zxz.bbs.pojo.Message;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
清理帖子进程
 */
public class MsgDelTask implements Task {

    @Override
    public void execute() throws Exception {
        System.out.println("***清理帖子（delFlag=1）......");
        List<Map> msgList=TokenUtil.tokenUtil.messageMapper.queryMessageFromDelFlag();
        System.out.println("exit1");

       for(Map msg:msgList)
       {
           try {
               System.out.println("exit");
               clearMessage(msg);
           }
           catch (Exception e){

           }

       }
    }

    private void clearMessage(Map msg)throws  Exception
    {
        File storeDir =new File("C:\\img2\\"+msg.get("storePath"));
        if(storeDir.exists())
        {
            try{
                FileUtils.deleteQuietly(storeDir);
            }
            catch (Exception e){
            }
        }
        TokenUtil.tokenUtil.messageMapper.delMessageFromId((Long) msg.get("id"));

    }
}
