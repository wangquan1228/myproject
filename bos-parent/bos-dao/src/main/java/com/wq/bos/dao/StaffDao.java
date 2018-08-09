package com.wq.bos.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.wq.bos.dao.base.BaseDao;
import com.wq.bos.domain.Staff;
import com.wq.bos.utils.PageBean;

public interface StaffDao extends BaseDao<Staff> {
  //修改某个字段
    void executeUpdate(Serializable id);
    //还原用户信息
    void doRestore(Serializable id);
    
     
}
