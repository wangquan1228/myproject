package com.wq.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opensymphony.xwork2.ActionContext;
import com.wq.bean.Customer;
import com.wq.bean.User;
import com.wq.dao.CustomerDao;
import com.wq.dao.UserDao;
import com.wq.dao.impl.CustomerDaoImpl;
import com.wq.service.CustomerService;
import com.wq.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)//配置容器
@ContextConfiguration("classpath:applicationContext.xml")//指定路径
public class Demotest {
    @Resource(name="sessionFactory")//注入获取bean
    private SessionFactory sf;
    @Test
    public  void fun1() {
        Session session = sf.openSession();
        Transaction tx=session.beginTransaction();
        User user=new User();
        user.setUser_code("Jerr");
        user.setUser_id(3l);
        user.setUser_name("杰瑞");
        user.setUser_password("123");
        user.getUser_state();
        session.save(user);
        tx.commit();
        session.clear();
        sf.close();
    }
    
    @Test
    public  void fun2() {
        Session session = sf.openSession();
        Transaction tx=session.beginTransaction();
        User user=new User();
        user.setUser_code("Rose");
        user.setUser_id(3l);
        user.setUser_name("柔丝");
        user.setUser_password("123");
        user.getUser_state();
        session.save(user);
        tx.commit();
        session.clear();
        sf.close();
    }
    
  //测试Dao Hibernate模板
    @Resource(name="userDao")
    private  UserDao userDao;
    @Test
    public void fun5(){
        
        User u = userDao.getUserByCode("wq");
        
        System.out.println(u);
        
    }
      
    //测试事务
    @Resource(name="userService")
    private UserService userService;
    @Test
    public  void fun6() {
        User user=new User();
        user.setUser_code("Jack");
        user.setUser_name("杰克");
        user.setUser_password("123");
        userService.saveUser(user);
    }
    
    
    @Resource(name="customerService")
    private CustomerService customerService;
    @Test
    public  void  save() {
        Customer customer=new Customer();
        customer.setCust_name("黄龙");
        customerService.saveCust(customer);
    }
    
    
    
    
    @Test
    public  void update() {
        User user=new User();
        user.setUser_id(1L);
        user.setUser_password("234");
        userService.updateUserPwd(user); 
        System.out.println("login");
    }
    
     
   
}
