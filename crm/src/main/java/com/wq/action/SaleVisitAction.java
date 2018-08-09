package com.wq.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wq.bean.LinkMan;
import com.wq.bean.SaleVisit;
import com.wq.bean.User;
import com.wq.service.SaleVisitService;
import com.wq.util.PageBean;

@Controller("saleVisitAction")
public class SaleVisitAction  extends  ActionSupport implements ModelDriven<SaleVisit>{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Resource(name="saleVisitService")
    private SaleVisitService saleVisitService;

    public void setSaleVisitService(SaleVisitService saleVisitService) {
        this.saleVisitService = saleVisitService;
    }
    
   SaleVisit saleVisit= new SaleVisit();
   
    public SaleVisit getModel() {
        // TODO Auto-generated method stub
        return saleVisit;
    }
    
    // 属性驱动
    private Integer currentPage;

    private Integer pageSize;

   
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
        DetachedCriteria dc = DetachedCriteria.forClass(SaleVisit.class);
        if(saleVisit.getCustomer() != null &&saleVisit.getCustomer().getCust_id()!=null){
            dc.add(Restrictions.eq("customer.cust_id", saleVisit.getCustomer().getCust_id()));
        }
        
        // 1 调用service的查询分页数据(pageBean)
        PageBean pb = saleVisitService.getPageBean(dc, currentPage, pageSize);
        List<SaleVisit> list = pb.getList();

        // 2 将pageBean放入request域中,转发到列表页面
        ActionContext.getContext().put("list", list);
        ActionContext.getContext().put("pageBean", pb);
        return "list";
    }

    
    /**
     * 添加拜访人
     * @return
     */
    public String saveSaleVisit() {
      //0、取出登录用户，放入SaleVisit实体，表达关系
        User u = (User) ActionContext.getContext().getSession().get("user");
        saleVisit.setUser(u);
        saleVisitService.saveSaleVisit(saleVisit);
        return "success";
    }
    
    /**
     * 根据visit_id查询拜访记录
     * @return
     */
    public String getSaleVisitById() {
        SaleVisit saletVisit = saleVisitService.geSaletVisitById(saleVisit.getVisit_id());
        ActionContext.getContext().put("saleVisit",saletVisit);
        return "toEdit";
    }
    /**
     * 根据查找的用户然后进行修改
     * @return
     */
    public String updateSaleVisit() {
        saleVisitService.updateSaleVisit(saleVisit);
        return "success";
    }
    
    public String deleteSaleVisit() {
        saleVisitService.deleteSaleVisit(saleVisit);
        return "success";
    }

}
