package com.wq.service.impl;

 

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wq.bean.User;
import com.wq.dao.UserDao;
import com.wq.service.UserService;

@Service("userService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class UserServiceImpl implements UserService {
    @Resource(name="userDao")
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    
    public User getUSerByCodePassword(User user) {
        //1 根据用户名查找对象
        User existUser = userDao.getUserByCode(user.getUser_code());
        //2 判断查询的对象是否存在
        if(existUser==null) {
            throw new RuntimeException("该用户不存在");
        }
        //3 判断获取的对象的密码与输入的密码是否一致
        if (!existUser.getUser_password().equals(user.getUser_password())) {
            throw new RuntimeException("输入的密码有误!,请重新输入");
        }
        //4 返回查询的对象
        return existUser;
    }

    
    public void saveUser(User user) {
        User  user1 = userDao.getUserByCode(user.getUser_code());
        if(user1!=null) {
            throw new RuntimeException("该用户名已经被使用,请重新输入");
        }
        userDao.save(user);
    }
    /*
     * 修改用户密码
     * @see com.wq.service.UserService#updatePwd(com.wq.bean.User)
     */
    
    public void updateUserPwd(User user) {
      userDao.update(user);
        
    }

 
    public User getById(Serializable id) {
        return userDao.getById(id);
    }

     
     

}
