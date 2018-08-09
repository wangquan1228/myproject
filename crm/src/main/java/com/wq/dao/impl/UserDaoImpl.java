package com.wq.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.wq.bean.User;
import com.wq.dao.UserDao;

//HibernateDaoSupport 为dao注入sessionFactory
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    
    @Resource(name="sessionFactory")
    public void setSF(SessionFactory sf){
        super.setSessionFactory(sf);
    }

 
    public User getUserByCode(String code) {
        // TODO Auto-generated method stub
        return getHibernateTemplate().execute(new HibernateCallback<User>() {
           
            public User doInHibernate(Session session) throws HibernateException {
                // hql语句
                String hql = "from User where user_code = ?";
                 Query query = session.createQuery(hql);
                query.setParameter(0, code);
                User user = (User) query.uniqueResult();
                return user;
                /*
                 * ////Criteria DetachedCriteria dc = DetachedCriteria.forClass(User.class);
                 * dc.add(Restrictions.eq("user_code", usercode)); List<User> list =
                 * (List<User>) getHibernateTemplate().findByCriteria(dc);
                 * 
                 * if(list !=null &&list.size()>0){ return list.get(0); }else{ return null; }
                 */ }
        });
    }

   /* @Override
    public User getUserByName(String name) {
        return getHibernateTemplate().execute(new HibernateCallback<User>() {
            @Override
            public User doInHibernate(Session session) throws HibernateException {
                Criteria criteria = session.createCriteria(User.class);
                criteria.add(Restrictions.eq("user_name", name));
                User user = (User) criteria.uniqueResult();
                return user;
            }
        });
    }*/

}
