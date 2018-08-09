package com.wq.dao;

import com.wq.bean.User;

public interface UserDao extends BaseDao<User>{
   
    //根据用户名查询对象
   User getUserByCode(String code);

    
}
