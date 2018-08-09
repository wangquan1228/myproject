package com.wq.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wq.bos.dao.UserDao;
import com.wq.bos.domain.Role;
import com.wq.bos.domain.User;
import com.wq.bos.service.UserService;
import com.wq.bos.utils.MD5Utils;

@Service("userService")
@Transactional
public class UserServiceImpl  implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
      //使用MD5加密密码
         String password = MD5Utils.md5(user.getPassword());
        return  userDao.login(user.getUsername(), password);
    }

    @Override
    public void updatePwd(User user) {
        //密码加密
      String password = MD5Utils.md5(user.getPassword()); 
      user.setPassword(password);
      userDao.update(user);
    }

   

    @Override
    public void add(User model, String[] roleIds) {
        String password = model.getPassword();
        password = MD5Utils.md5(password);
        model.setPassword(password);
        userDao.save(model);
        if(roleIds != null && roleIds.length > 0){
            for (String roleId : roleIds) {
                //手动构造托管对象
                Role role = new Role(roleId);
                //用户对象关联角色对象
                model.getRoles().add(role);
            }
        }
        
    }

}
