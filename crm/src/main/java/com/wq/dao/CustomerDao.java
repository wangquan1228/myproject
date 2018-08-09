package com.wq.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.wq.bean.Customer;
import com.wq.util.PageBean;

public interface CustomerDao extends BaseDao<Customer> {

    public List<Object[]> getIndustryCount();
    
    public List<Object[]> getSourceCount();

    

    

   
 
}
