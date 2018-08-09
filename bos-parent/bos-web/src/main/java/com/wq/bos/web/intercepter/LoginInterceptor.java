package com.wq.bos.web.intercepter;

import java.io.PrintWriter;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wq.bos.domain.User;
import com.wq.bos.utils.BosUtils;
 

public class LoginInterceptor extends MethodFilterInterceptor {

    private static final long serialVersionUID = 1L;

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        // TODO Auto-generated method stub
        User user = BosUtils.getUser();
        // 判断对象是否存在
        if (user == null) {
            return "login";
        } else {
            return invocation.invoke();
        }
    }
}
