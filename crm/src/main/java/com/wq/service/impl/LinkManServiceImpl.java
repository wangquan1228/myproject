package com.wq.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wq.bean.Customer;
import com.wq.bean.LinkMan;
import com.wq.dao.LinkManDao;
import com.wq.service.LinkManService;
import com.wq.util.PageBean;

@Service("linkManService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=true)
public class LinkManServiceImpl  implements LinkManService{
    
    @Resource(name="linkManDao")
    private LinkManDao linkManDao;

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }

  
    public void saveLinkMan(LinkMan linkMan) {
       linkManDao.save(linkMan);
    }

    /**
     * 根据id 修改用户
     */
    public void updateLinkMan(Long cust_id) {
        // TODO Auto-generated method stub
        linkManDao.updateLinkMan(cust_id);
    }

     /**
      * 删除联系人
      */
    public void deleteLinkMan(LinkMan linkMan) {
        linkManDao.delete(linkMan);
        
    }
    
    /**
     * 根据对象删除用户
     */
    public void updateLinkMan(LinkMan linkMan) {
        linkManDao.update(linkMan);
        
    }

    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        // 1 调用Dao的查询总记录数
        Integer totalCount = linkManDao.getByTotalCount(dc);
        // 2 创建PageBean
        PageBean pb = new PageBean(currentPage, totalCount, pageSize);
        // 3 调用 Dao的查询分页列表数据

        List<LinkMan> list = linkManDao.getPageList(dc, pb.getStart(), pb.getPageSize());
        // 4 列表数据放入pageBean中,并返回
        pb.setList(list);
        return pb;
    }

 
    public LinkMan getLinkManById(Long lkm_id) {
       return linkManDao.getById(lkm_id);
    }
}
