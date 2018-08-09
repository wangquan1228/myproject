package com.wq.bos.service;

import com.wq.bos.domain.User;

public interface UserService {
    public User login(User user);

    public void updatePwd(User user);

    public void add(User model,String[] roleIds);

}
