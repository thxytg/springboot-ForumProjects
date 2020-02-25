package com.zxz.bbs.mapper;

import com.zxz.bbs.pojo.User;
import com.zxz.bbs.pojo.UserAbility;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    //添加一个用户
    void addUser(User user);

    //添加用户同时关联用户权限表
  UserAbility addUserAbility(UserAbility userAbility);

    //按姓名查询一个用户
    User getOneUser(String name,String password);

    //更改用户密码
    void changePassWord(String password,int id);

    //用户更换头像
   void replaceUserPhoto(String thumb,int id);

   //按id查找一个用户
    User getUserFromId(int id);

    //按UserId查询用户权限表
       UserAbility getUserAbilityFromUserId(long id);



}
