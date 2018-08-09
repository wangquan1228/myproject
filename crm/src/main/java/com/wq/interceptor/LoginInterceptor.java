package com.wq.interceptor;

import java.io.PrintWriter;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wq.bean.User;

public class LoginInterceptor extends MethodFilterInterceptor {

    private static final long serialVersionUID = 1L;

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        // TODO Auto-generated method stub
        // 获取session
        Map<String, Object> session = ActionContext.getContext().getSession();
        // 从session中获取对象
        User user = (User) session.get("user");
        // 判断对象是否存在
        if (user == null) {
            PrintWriter out = ServletActionContext.getResponse().getWriter();
            out.println("<script>alert('您还未登录,请登录!')</script>");
            return "toLogin";
        } else {
            return invocation.invoke();
        }
    }
}
