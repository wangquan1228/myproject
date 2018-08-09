package com.wq.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

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
    //查符合条件的总记录数
    Integer getByTotalCount(DetachedCriteria dc);
    //查寻分页列表数据
    List<T> getPageList(DetachedCriteria dc, Integer start,Integer pageSize);
}
