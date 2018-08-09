package com.wq.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wq.bos.domain.Noticebill;
import com.wq.bos.service.NoticebillService;
import com.wq.bos.web.action.base.BaseAction;
import com.wq.crm.utils.Customer;
import com.wq.crm.utils.CustomerService;

/** 
*
* @author : wangquan
* @date ：2018年8月4日 下午3:18:53
* 
*/
@Controller("noticebillAction")
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
    //注入noticebillService
    @Autowired
    private NoticebillService noticebillService;
    @Autowired
    private CustomerService customerService;
    
  //远程调用crm服务,根据手机号码查询客户信息
    public String findCustomerByTelephone() {
        //获取手机号
        
        String telephone = model.getTelephone();
        System.out.println(telephone);
        Customer customer = customerService.findCustomerByTelephone(telephone);
        System.out.println(customer);
        this.javaToJson(customer, new String[] {});
        return NONE;
    }
    //保存一个业务通知单,    并且尝试分单处理(自动分单,人工分单)
    public String add() {
        noticebillService.save(model);
        return "noticebill_add" ;
    }
    

}
