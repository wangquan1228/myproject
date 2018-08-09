package com.wq.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wq.bos.domain.Decidedzone;
import com.wq.bos.service.DecidedzoneService;
import com.wq.bos.web.action.base.BaseAction;
import com.wq.crm.utils.Customer;
import com.wq.crm.utils.CustomerService;

/**
 * 
 * @author wangquan
 * @date 2018年7月29日
 *
 */
@Controller("decidedzoneAction")
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
    // 注入service
    @Autowired
    private DecidedzoneService decidedzoneService;
    // 注入外部的customerService
    @Autowired
    private CustomerService customerService;
    // 属性驱动接收分取数组id
    private String[] subareaid;

    public String[] getSubareaid() {
        return subareaid;
    }

    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }

    // 添加
    public String add() throws Exception {
        // 两个参数,一个是decidedzone 一个是往subareal注入外键id
        decidedzoneService.add(model, subareaid);
        return "list";
    }

    /**
     * 页面显示
     */
    public String pageQuery() {
        decidedzoneService.pageQuery(pageBean);
        System.out.println("测试:" + pageBean);
        this.javaToJson(pageBean, new String[] { "currentPage", "detachedCriteria", "pageSize", "decidedzones", "subareas" });
        return NONE;
    }

    /**
     * 获取未关联的定区客户
     */
    public String findListNotAssociation() {
        List<Customer> list = customerService.findListNotAssociation();
        javaToJson(list, new String[] {});
        return NONE;
    }

    /**
     * 获得已经关联的客户
     */
    public String findListHasAssociation() {
        // 首先先获得id
        String id = model.getId();
        List<Customer> list = customerService.findListHasAssociation(id);
        this.javaToJson(list, new String[] {});
        return NONE;
    }

    // 属性驱动,接收页面提交的多个客户id
    private List<Integer> customerIds;

    public List<Integer> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }

    // 将选择的客户关联到定区
    public String assignCustomerStoDecidedzone() {
        customerService.assigncustomerstodecidedzone(model.getId(), customerIds);
        return "list";
    }
    
   
}
