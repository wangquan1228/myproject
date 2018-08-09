package com.wq.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.wq.bos.utils.PageBean;

public interface BaseDao<T> {
    //增
    void save(T t);
    //使用对象删除
    void delete(T t);
    //删除Serializable接口实现所有的类型
    void delete(Serializable id);
    //改
    void update(T t);
    //查
    T getById(Serializable id);
    //查询所有的
    List<T> findAll();
    //分页查询
    void pageQuery(PageBean pageBean);
    
    //离线查询
    List<T> findByCriteria(DetachedCriteria detachedCriteria);
     
    
}
