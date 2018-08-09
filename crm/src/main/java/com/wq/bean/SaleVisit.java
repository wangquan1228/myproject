package com.wq.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class SaleVisit {
    /**
         * CREATE TABLE `sale_visit` (
      `visit_id` varchar(32) NOT NULL,
      `visit_cust_id` bigint(32) DEFAULT NULL COMMENT '客户id',
      `visit_user_id` bigint(32) DEFAULT NULL COMMENT '负责人id',
      `visit_time` date DEFAULT NULL COMMENT '拜访时间',
      `visit_interviewee` varchar(32) DEFAULT NULL COMMENT '被拜访人',
      `visit_addr` varchar(128) DEFAULT NULL COMMENT '拜访地点',
      `visit_detail` varchar(256) DEFAULT NULL COMMENT '拜访详情',
      `visit_nexttime` date DEFAULT NULL COMMENT '下次拜访时间',
      PRIMARY KEY (`visit_id`),
      KEY `FK_sale_visit_cust_id` (`visit_cust_id`),
      KEY `FK_sale_visit_user_id` (`visit_user_id`),
      CONSTRAINT `FK_sale_visit_cust_id` FOREIGN KEY (`visit_cust_id`) REFERENCES `cst_customer` (`cust_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
      CONSTRAINT `FK_sale_visit_user_id` FOREIGN KEY (`visit_user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
     */
    
    private String visit_id;//拜访人id
    private String visit_interviewee;//被拜访人
    private String visit_addr;//拜访地点
    private String visit_detail;//拜访详情
    private Date visit_time;//拜访时间
    private Date visit_nexttime;//下次拜访时间
    //表达所属客户对象 多对一
    private Customer customer;
    //表达所属用户对象 多对一
    private User user;
    public String getVisit_id() {
        return visit_id;
    }
    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }
    public String getVisit_interviewee() {
        return visit_interviewee;
    }
    public void setVisit_interviewee(String visit_interviewee) {
        this.visit_interviewee = visit_interviewee;
    }
    public String getVisit_addr() {
        return visit_addr;
    }
    public void setVisit_addr(String visit_addr) {
        this.visit_addr = visit_addr;
    }
    public String getVisit_detail() {
        return visit_detail;
    }
    public void setVisit_detail(String visit_detail) {
        this.visit_detail = visit_detail;
    }
    public Date getVisit_time() {
        return visit_time;
    }
    public void setVisit_time(Date visit_time) {
        this.visit_time = visit_time;
    }
    public Date getVisit_nexttime() {
        return visit_nexttime;
    }
    public void setVisit_nexttime(Date visit_nexttime) {
        this.visit_nexttime = visit_nexttime;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public String toString() {
        return "SaleVisit [visit_id=" + visit_id + ", visit_interviewee=" + visit_interviewee + ", visit_addr=" + visit_addr
                + ", visit_detail=" + visit_detail + ", visit_time=" + visit_time + ", visit_nexttime=" + visit_nexttime
                + ", customer=" + customer + ", user=" + user + "]";
    }
     
    
    
}
