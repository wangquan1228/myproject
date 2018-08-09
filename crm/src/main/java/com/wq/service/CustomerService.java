package com.wq.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.wq.bean.Customer;
import com.wq.util.PageBean;

public interface CustomerService {
    PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

    void saveCust(Customer customer);

    void deleteCust(Serializable id);

    void updateCust(Customer customer);

    Customer getById(Serializable id);

    List<Object[]> getIndustryCount();
    
    List<Object[]> getSourceCount();

    
}
