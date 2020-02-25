package com.zxz.bbs.controller;

import com.zxz.bbs.af.spring.AfRestData;
import com.zxz.bbs.mapper.UserMapper;
import com.zxz.bbs.pojo.User;
import com.zxz.bbs.utils.MyUtil;
import com.zxz.bbs.utils.TmpFile;
import com.zxz.bbs.utils.UserLoginUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserPhotoController {

    @PostMapping("/u/profile/setPhoto.do")
    public Object setPhoto(HttpServletRequest request
            , @SessionAttribute User user, HttpSession session)throws Exception {
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
//            String tmpUrl = TmpFile.getUrl(tmpFile);

            // 接收上传 ...
            mf.transferTo(tmpFile);
            System.out.println("** file: " + tmpFile.getAbsolutePath());

            if(tmpFile.length()> 500* 1000)
                throw new Exception("图片太大!需小于500KB!");

           String tmpUrl =photo(user,tmpFile,session);

            // 回应给客户端的消息
            result.put("realName", realName);
            result.put("tmpName", tmpName);
            result.put("tmpUrl", tmpUrl);


        }
        return new AfRestData(result);
    }

    @Resource
    UserMapper userMapper;

    private String photo(User user,File tmpFile,HttpSession session) throws Exception {
        String storeDirPath="C:\\img2\\userPhoto\\"+user.id;
        File storeDir = new File(storeDirPath);
        try
        {
            FileUtils.moveFileToDirectory(tmpFile, storeDir, true);

        } catch (IOException e)
        {
            throw new RuntimeException(e.getMessage());
        }
        String path = storeDir.getPath()+"\\"+tmpFile.getName();
        String url =path.substring(2);
        userMapper.replaceUserPhoto(url,user.id);
        //更新User
        User user2= userMapper.getUserFromId(user.id);
        //更新session中的user
        UserLoginUtil.login(session,user2);



        return url;
    }
}
