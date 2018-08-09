package com.wq.bos.dao;

import java.util.List;

import com.wq.bos.dao.base.BaseDao;
import com.wq.bos.domain.Region;

public interface RegionDao extends BaseDao<Region> {
   
    //条件查询
    List<Region> findAll(String term);

     

}
