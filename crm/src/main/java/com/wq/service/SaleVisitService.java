package com.wq.service;

import org.hibernate.criterion.DetachedCriteria;

import com.wq.bean.SaleVisit;
import com.wq.util.PageBean;

public interface SaleVisitService {
    
    public void saveSaleVisit(SaleVisit saleVisit);

    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

    public SaleVisit geSaletVisitById(String visit_id);

    public void updateSaleVisit(SaleVisit saleVisit);

    public void deleteSaleVisit(SaleVisit saleVisit);
    
    
}
