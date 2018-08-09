package com.wq.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wq.bean.BaseDict;
import com.wq.bean.Customer;

import com.wq.service.CustomerService;
import com.wq.service.LinkManService;
import com.wq.util.PageBean;
@Repository("customerAction")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
    
    @Resource(name="customerService")
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    // 注入linkManService
    private LinkManService linkManService;

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }

    // 模型驱动
    private Customer customer = new Customer();

    
    public Customer getModel() {
        // TODO Auto-generated method stub
        return customer;
    }

    // 属性驱动
    private Integer currentPage;

    private Integer pageSize;
    
    private  String select;

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    
    public String list() throws Exception {
        // 封装离线查询
        DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
        if (StringUtils.isNotBlank(customer.getCust_name())) {
            dc.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
        }
        // 1 调用service的查询分页数据(pageBean)
        PageBean pb = customerService.getPageBean(dc, currentPage, pageSize);
        List<Customer> list = pb.getList();
        // 2 将pageBean放入request域中,转发到列表页面
        ActionContext.getContext().put("list", list);
        ActionContext.getContext().put("pageBean", pb);
        System.out.println(select);
        return "list";
    }

    /*
     * 添加用户
     */
    public String saveCust() throws Exception {
       /* System.out.println(customer.getCust_level());
        System.out.println(customer.getCust_industry());
        System.out.println(customer.getCust_source());

        System.out.println("customer.getCust_moblie():" + customer.getCust_moblie());
        System.out.println("customer.getCust_name()" + customer.getCust_name());
        System.out.println("customer.getCust_phone():" + customer.getCust_phone());
        System.out.println("customer.getCust_phone():" + customer.getCust_phone());
*/
        if (customer.getCust_moblie().equals("") || customer.getCust_phone().equals("") || customer.getCust_name().equals("") || customer.getCust_industry().getDict_id().equals("")
                || customer.getCust_level().getDict_id().equals("") || customer.getCust_source().getDict_id().equals("")) {
            ActionContext.getContext().put("msg", "惊不惊喜,还是我");
            return "toadd";

        }

        customerService.saveCust(customer);
        return "success";

    }

    /*
     * 删除用户
     */
    public String deleteCust() throws Exception {
        linkManService.updateLinkMan(customer.getCust_id()); 
        customerService.deleteCust(customer.getCust_id());
        return "success";
    }

    /*
     * 根据id查询对象
     */
    public String getCustById() {
        Customer customer1 = customerService.getById(customer.getCust_id());
        ActionContext.getContext().put("customer", customer1);
        return "edit";
    }

    /*
     * 修改用户
     */
    public String updateCust() throws Exception {
        customerService.updateCust(customer);
        return "success";
    }
    
    /*
     * 查询客户行业信息
     */
    public String getIndustryCount() {
       List<Object[]>list= customerService.getIndustryCount();
       ActionContext.getContext().put("list", list);
        return "industry";
    }
    
    /**
     * 查询客户来源
     * @return
     */
    public String getSourceCount() {
        List<Object[]>list= customerService.getSourceCount();
        ActionContext.getContext().put("list", list);
        return "source";
    }
}
