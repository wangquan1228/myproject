package com.wq.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;


import com.wq.bos.dao.base.BaseDao;
import com.wq.bos.utils.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    
    @Resource(name="sessionFactory")
    public void setMysessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    private Class clazz;// 勇于接受运行期的泛型类型

    public BaseDaoImpl() {
        // 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的
        // Type，然后将其转换ParameterizedType。(子类的父类)
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        // ParameterizedType是判断genericSuperclass是否被参数化的接口
        ParameterizedType parameterizedType = null;
        // 判断genericSupperclass是否被参数化
        if (genericSuperclass instanceof ParameterizedType) {
            // 将父类强转为ParameterizedType类型,即将genericSuperclass参数化
            parameterizedType = (ParameterizedType) genericSuperclass;
        }
        // 返回一个Type的数组,里面装的是泛型T
        Type[] typeArray = parameterizedType.getActualTypeArguments();
        if (typeArray != null && typeArray.length > 0) {
            // 获取第一泛型值
            clazz = (Class) typeArray[0];
        }

    }

    public void save(T t) {
        getHibernateTemplate().save(t);
    }

    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    public void delete(Serializable id) {
        T t = this.getById(id);// 先获取后删除
        getHibernateTemplate().delete(id);
    }

    public void update(T t) {
        getHibernateTemplate().update(t);
    }

    public T getById(Serializable id) {

        return (T) getHibernateTemplate().get(clazz, id);
    }

     
   
    /**
     *分页查询
     */
    @Override
    public void pageQuery(PageBean pageBean) {
        //提出当前页
        int currentPage = pageBean.getCurrentPage();
        //提出每页的条数
        int pageSize = pageBean.getPageSize();
        //提出离线查询对象
        DetachedCriteria mDetachedCriteria = pageBean.getDetachedCriteria();
        
        //total rows(当前集合数据)
        //获取total
        //指定hibernate发出sql的形式 select count(*) from xx 
        mDetachedCriteria.setProjection(Projections.rowCount());
        //利用模板获得查询数据
        List<Long> countList = (List<Long>) this.getHibernateTemplate().findByCriteria(mDetachedCriteria);
        //第一个元素是我们需要的总数
        Long count = countList.get(0);
        //set
        pageBean.setTotal(count.intValue());
        
        
        //rows
        //切换sql形式
        mDetachedCriteria.setProjection(null);
      //指定hibernate 返回结果集的方式
        mDetachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        //开始的条数
        int firstResult = (currentPage - 1) * pageSize;
        //每页的页数
        int maxResults = pageSize;
        //使用模板对象
        List rows = this.getHibernateTemplate().findByCriteria(mDetachedCriteria, firstResult, maxResults);
        //设置结果集
        pageBean.setRows(rows);
        

    }
    /**
     * 查询所有的对象
     */
    public List<T> findAll() {
        String hql="from "+clazz.getSimpleName();
        return (List<T>) getHibernateTemplate().find(hql);
    }
    /* (non-Javadoc)
     * @see com.tz.bos.dao.base.IBaseDao#findByCriteria(org.hibernate.criterion.DetachedCriteria)
     */
    @Override
    public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
        
        return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
     
     
}
