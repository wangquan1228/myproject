package com.wq.service;

import org.hibernate.criterion.DetachedCriteria;

import com.wq.bean.LinkMan;
import com.wq.util.PageBean;

public interface LinkManService {
    //添加联系人
    public void saveLinkMan(LinkMan linkMan);

    public void updateLinkMan(Long cust_id);

    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

    public void deleteLinkMan(LinkMan linkMan);

    public void updateLinkMan(LinkMan linkMan);

    public LinkMan getLinkManById(Long lkm_id);

     
}
