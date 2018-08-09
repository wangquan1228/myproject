package com.wq.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wq.bean.Customer;
import com.wq.bean.LinkMan;

import com.wq.service.LinkManService;
import com.wq.util.PageBean;

@Controller("linkManAction")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // 模型驱动
    LinkMan linkMan = new LinkMan();

    
    public LinkMan getModel() {
        return linkMan;
    }

    @Resource(name="linkManService")
    private LinkManService linkManService;

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
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
        DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
        if (StringUtils.isNotBlank(linkMan.getLkm_name())) {
            dc.add(Restrictions.like("lkm_name", "%" + linkMan.getLkm_name() + "%"));
        }
        // 1 调用service的查询分页数据(pageBean)
        PageBean pb = linkManService.getPageBean(dc, currentPage, pageSize);
        List<LinkMan> list = pb.getList();

        // 2 将pageBean放入request域中,转发到列表页面
        ActionContext.getContext().put("list", list);
        ActionContext.getContext().put("pageBean", pb);
        return "list";
    }

    /**
     * 存储用户
     * 
     * @return
     */
    public String saveLinkMan() {
        linkManService.saveLinkMan(linkMan);
        return "success";
    }

    /**
     * 根据对象删除用户
     * 
     * @return
     */
    public String deleteLinkMan() {
        linkManService.deleteLinkMan(linkMan);
        return "success";
    }

    /**
     * 根据联系人id查找联系人
     * 
     * @return
     */
    public String getLinkManById() {
        LinkMan linkMan1 = linkManService.getLinkManById(linkMan.getLkm_id());
        ActionContext.getContext().put("linkMan", linkMan1);
        return "toEdit";
    }

    public String updateLinkMan() {
        linkManService.updateLinkMan(linkMan);
        return "success";
    }
}
