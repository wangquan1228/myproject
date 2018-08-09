package com.wq.bos.web.action;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.wq.bos.domain.User;
import com.wq.bos.service.UserService;
import com.wq.bos.utils.BosUtils;
import com.wq.bos.utils.MD5Utils;
import com.wq.bos.web.action.base.BaseAction;
import com.wq.crm.utils.Customer;
import com.wq.crm.utils.CustomerService;

@Controller("userAction")
@Scope("prototype")
public class UserAction  extends BaseAction<User>{

    /**
     * 
     */
    private static final long serialVersionUID = -89510077575118231L;
    //属性驱动,接收页面的验证码
    private String checkcode;
    public void setCheckcode(String checkcode ) {
        this.checkcode = checkcode;
    }
     
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    //用户登录
   /* public String login() {
        Customer customer = customerService.findCustomerByTelephone("13788888888");
        System.out.println(customer);
        //从Session中获取生成的验证码
        String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        //检验是否正确
        
        //返回一个user的model
        
        if (StringUtils.isNotBlank(checkcode)&&checkcode.equals(validatecode)) {
            
            System.out.println(checkcode+validatecode);
            User user = userService.login(model);
            //输入的验证码是正确验证用户
            if(user != null) {
                //登录成功,将用户放入session中
                ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
            
                //跳转到Home
                return HOME;                
            }else {
                //登录失败,设置提示信息,跳转到登录页面
                ActionContext.getContext().put("msg", "用户名或者密码错误");
                return LOGIN;
            }
            
        }else {
            //输入的验证码错误,设置提示信息,跳转登录页面
            ActionContext.getContext().put("msg", "您输入的验证码错误");
            return LOGIN;
        }
    }*/
    //使用shiro验证登录
    public String login() {
      //从Session中获取生成的验证码
        String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        //检验是否正确    
        //返回一个user的model    
        if (StringUtils.isNotBlank(checkcode)&&checkcode.equals(validatecode)) {
            //使用shiro框架提供的方式进行认证
            //获得当前用户的对象,并且状态是没有认证
            Subject subject = SecurityUtils.getSubject();
            //创建用户名密码令牌对象
            UsernamePasswordToken token = new UsernamePasswordToken(model.getUsername(),MD5Utils.md5(model.getPassword()));
            try {
                //登录验证 转到Realm
                subject.login(token);
            } catch (Exception e) {
                e.printStackTrace();
                return LOGIN;
            }
            //Realm返回一个成功验证的用户
            User user = (User) subject.getPrincipal();
            ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
            return HOME;
        }else {
            //输入的验证码错误,设置提示信息,跳转登录页面
            this.addActionError("验证码输入错误");
            return LOGIN;
        }
        
    }
    /*
     * 安全退出
     */
    public String logout() {
        // 通过ServletActionContext获取session域中
        HttpSession session = ServletActionContext.getRequest().getSession();
            session.invalidate();// 销毁session
            return  LOGIN;
        
    }
    
    /*
     * 修改密码
     * 
     */
    public String editPassword() throws Exception {

        
       String f = "1";
       //获取当前登录用户
       User user = BosUtils.getUser();
       System.out.println(user.getId()+model.getPassword());
       user.setPassword(model.getPassword());
       try {
           userService.updatePwd(user);
       } catch (Exception e) {
           //出现异常返回0
           f = "0";
           e.printStackTrace();
       }
       //设置标准code格式
       ServletActionContext.getResponse().setContentType("text/html,charset=utf-8");
       ServletActionContext.getResponse().getWriter().print(f);
       return NONE;
    }
    

    //属性驱动，接收多个角色id
        private String[] roleIds;
        public void setRoleIds(String[] roleIds) {
            this.roleIds = roleIds;
        }
        
        
        //添加用户
        public String add(){
            userService.add(model,roleIds);
            return "list";
        }

}
