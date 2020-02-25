package com.zxz.bbs.controller;

import com.zxz.bbs.af.spring.AfRestData;
import com.zxz.bbs.pojo.User;
import com.zxz.bbs.utils.MyUtil;
import com.zxz.bbs.utils.TmpFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MesgImgController {

    @PostMapping(value = "/u/message/imageUp.do")
    public Object upload(HttpServletRequest request,
                         @SessionAttribute User user)throws Exception {
        MultipartHttpServletRequest mhr = (MultipartHttpServletRequest) request;
        // 从表单里获取其他参数
        String argValue = mhr.getParameter("your_arg_name");

        MultipartFile mf = mhr.getFile("file"); // 表单里的 name='file'
        Map<String, Object> result = new HashMap<>();

        if (mf != null && !mf.isEmpty()) {
            //获取文件名
            String realName = mf.getOriginalFilename();
            //获取文件名后缀
            String suffix = MyUtil.getSuffix(realName);
            //文件重命名
            String tmpName = MyUtil.guid2() + suffix;

            //文件储存到本地
            File tmpFile = TmpFile.getFile(tmpName);
            String tmpUrl=TmpFile.getUrl(tmpFile);

            // 接收上传 ...
            mf.transferTo(tmpFile);
            System.out.println("** file: " + tmpFile.getAbsolutePath());

            if (tmpFile.length() > 1000000)
                throw new Exception("图片太大!需小于1M!");

            // 回应给客户端的消息
            result.put("realName", realName);
            result.put("tmpName", tmpName);
            result.put("tmpUrl", tmpUrl);


        }

        return new AfRestData(result);
    }
}
