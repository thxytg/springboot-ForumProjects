package com.zxz.bbs;

import com.zxz.bbs.mapper.MessageMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BbsApplicationTests {

    @Resource
    MessageMapper messageMapper;
    @Test
    void contextLoads() {
        List<Map> msgList=messageMapper.queryMessageFromDelFlag();

        System.out.println(msgList);

    }

}
