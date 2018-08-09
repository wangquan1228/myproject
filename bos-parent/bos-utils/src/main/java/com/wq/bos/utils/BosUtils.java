package com.wq.bos.utils;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.wq.bos.domain.User;

public class BosUtils {

    public static User getUser() {
        // 获得原生的session
        Map<String, Object> session = ActionContext.getContext().getSession();
        // 从session域中获取对象
        User user = (User) session.get("loginUser");
        return user;
    }

}
