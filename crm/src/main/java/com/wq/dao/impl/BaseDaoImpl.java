package com.wq.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;
import com.wq.bean.Customer;
import com.wq.dao.BaseDao;


public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    private Class  clazz;// 勇于接受运行期的泛型类型

    public BaseDaoImpl() {
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type，然后将其转换ParameterizedType。(子类的父类)
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        //ParameterizedType是判断genericSuperclass是否被参数化的接口
        ParameterizedType parameterizedType=null;
        //判断genericSupperclass是否被参数化
        if(genericSuperclass instanceof ParameterizedType) {
            //将父类强转为ParameterizedType类型,即将genericSuperclass参数化
            parameterizedType=(ParameterizedType) genericSuperclass;
        }
        //返回一个Type的数组,里面装的是泛型T
         Type[] typeArray = parameterizedType.getActualTypeArguments();
         if (typeArray != null && typeArray.length > 0) {  
             //获取第一泛型值
             clazz=(Class)typeArray[0];  
       }  
        
    }

     
    public void save(T t) {
        getHibernateTemplate().save(t);
    }
 
    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

   
    public void delete(Serializable id) {
        T t = this.getById(id);// 先取后删
        getHibernateTemplate().delete(t);
    }

    
    public void update(T t) {
        getHibernateTemplate().update(t);
    }

     
    public T getById(Serializable id) {
        return   (T) getHibernateTemplate().get(clazz, id);
    }

     
    public Integer getByTotalCount(DetachedCriteria dc) {
            // 设置查询的聚合函数,总记录数
            dc.setProjection(Projections.rowCount());
            List list = getHibernateTemplate().findByCriteria(dc);
            // 清空之前的聚合函数
            dc.setProjection(null);
            if (list != null && list.size() > 0) {
                Long count = (Long) list.get(0);
                return count.intValue();
            } else {
                return null;
            }
        }
    
     
    public List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize) {
        List<T> list = (List<T>) getHibernateTemplate().findByCriteria(dc, start, pageSize);
        return list;
    }

}
