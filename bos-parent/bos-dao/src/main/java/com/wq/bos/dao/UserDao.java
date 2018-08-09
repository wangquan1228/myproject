package com.wq.bos.dao;

import com.wq.bos.dao.base.BaseDao;
import com.wq.bos.domain.User;

public interface UserDao extends BaseDao<User> {
    public User login(String userName, String password);
    /**
     * 根据用户名查询用户信息
     * @param userName
     */
    public User findUserByUserName(String userName);
}
