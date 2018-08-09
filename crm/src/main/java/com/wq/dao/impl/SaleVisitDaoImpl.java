package com.wq.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wq.bean.SaleVisit;
import com.wq.dao.SaleVisitDao;

@Repository("saleVisitDao")
public class SaleVisitDaoImpl  extends BaseDaoImpl<SaleVisit> implements SaleVisitDao{
    
    @Resource(name="sessionFactory")
    public void setSF(SessionFactory sf){
        super.setSessionFactory(sf);
    }

}
