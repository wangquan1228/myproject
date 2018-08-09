package com.wq.service;

import java.io.Serializable;

import com.wq.bean.User;

public interface UserService {
    //登录方法
    User getUSerByCodePassword(User user );
    //注册用户
    void saveUser(User user);
    //修改密码
    void updateUserPwd(User user);
    //根据id查询对象
    User getById(Serializable id);
  
}
