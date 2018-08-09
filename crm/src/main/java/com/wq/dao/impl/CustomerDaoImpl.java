package com.wq.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.wq.bean.Customer;
import com.wq.dao.CustomerDao;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {
    @Resource(name="sessionFactory")
    public void setSF(SessionFactory sf){
        super.setSessionFactory(sf);
    }

    @SuppressWarnings("unchecked")
     
    public List<Object[]> getIndustryCount() {
      List<Object[]> list= getHibernateTemplate().execute(new HibernateCallback<List>() {
 
            public List doInHibernate(Session session) throws HibernateException {
                String sql = "select bd.dict_item_name,COUNT(*) total from cst_customer c,base_dict bd where c.cust_industry = bd.dict_id group by c.cust_industry";
                SQLQuery sqlQuery = session.createSQLQuery(sql);
                List list = sqlQuery.list();
                return list;
            }
        });
        return list;

    }
    
    
    /**
     * 查询客户来源
     */
    @SuppressWarnings("unchecked")
   
    public List<Object[]> getSourceCount() {
        List<Object[]> list= getHibernateTemplate().execute(new HibernateCallback<List>() {
            
            public List doInHibernate(Session session) throws HibernateException {
                String sql = " select bd.dict_item_name,COUNT(*) total from cst_customer c,base_dict bd where c.cust_source = bd.dict_id group by c.cust_source;";
                SQLQuery sqlQuery = session.createSQLQuery(sql);
                List list = sqlQuery.list();
                return list;
            }
        });
        return list;
    }

}
