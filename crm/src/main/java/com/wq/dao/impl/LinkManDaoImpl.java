package com.wq.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.wq.bean.LinkMan;
import com.wq.bean.User;
import com.wq.dao.LinkManDao;

@Repository("linkManDao")
public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao {
    
    @Resource(name="sessionFactory")
    public void setSF(SessionFactory sf){
        super.setSessionFactory(sf);
    }

     
    public void updateLinkMan(Long cust_id) {
        // TODO Auto-generated method stub
        getHibernateTemplate().execute(new HibernateCallback<List<LinkMan> >() {
            
            public List<LinkMan> doInHibernate(Session session) throws HibernateException {
                String hql = "from LinkMan  where lkm_cust_id=?";
                Query query = session.createQuery(hql);
                query.setParameter(0, cust_id);
                List<LinkMan> list = query.list();
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setCustomer(null);
                    }
                }
                return list;
            }

        });

    }

}
