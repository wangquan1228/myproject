package com.wq.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.wq.bean.User;
import com.wq.service.UserService;

@Controller("userAction")
public class UserAction extends ActionSupport implements ModelDriven<User> {

    private static final long serialVersionUID = 1L;

    @Resource(name="userService")
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // 模型驱动
    User user = new User();

    
    public User getModel() {
        // TODO Auto-generated method stub
        return user;
    }

    // 属性驱动
    // 原来的密码
    private String olduserPassword;

    private String txtCode;

    public String getOlduserPassword() {
        return olduserPassword;
    }

    public void setOlduserPassword(String olduserPassword) {
        this.olduserPassword = olduserPassword;
    }

    public String getTxtCode() {
        return txtCode;
    }

    public void setTxtCode(String txtCode) {
        this.txtCode = txtCode;
    }

    public String login() {
        //从session中获取验证码
        String kaptchaExpected = (String) ActionContext.getContext().getSession()
                .get(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if(txtCode.equals(kaptchaExpected)) {
            // 1 调用service 的业务逻辑
            User user1 = userService.getUSerByCodePassword(user);
            // 2 将返回的user对象放在session域中
            ActionContext.getContext().getSession().put("user", user1);
            // 3 重定向你想要得页面
            return "toHome";
        }else {

            ActionContext.getContext().put("msg", "验证码错误,请重新输入");
            return "error";
        }
         
    }

    /*
     * 安全退出
     */
    public String logout() {
        // 通过ServletActionContext获取session域中
        HttpSession session = ServletActionContext.getRequest().getSession();
         
            session.invalidate();// 销毁session
            return "logout";
        
        
    }

    /*
     * 修改密码
     * 
     */
    public String updatePwd() throws Exception {

        User user1 = userService.getById(user.getUser_id());
        String pwd = user1.getUser_password();
        if (pwd.equals(olduserPassword)) {
            user1.setUser_password(user.getUser_password());
            userService.updateUserPwd(user1);
            return "login";
        }
        ActionContext.getContext().put("msg", "你输入的原密码有误...");
        return "toupdate";
    }

    public String saveUser() {
        // 1、调用Service保存注册用户
        if (user.getUser_code().equals("") || user.getUser_name().equals("") || user.getUser_password().equals("")) {
            ActionContext.getContext().put("error", "注册对象不能为空");
            return "regist";
        }
        try {
            userService.saveUser(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ActionContext.getContext().put("error", e.getMessage());
            return "regist";
        }
        // 2、重定向到登录页面
        return "toLogin";
    }
}
