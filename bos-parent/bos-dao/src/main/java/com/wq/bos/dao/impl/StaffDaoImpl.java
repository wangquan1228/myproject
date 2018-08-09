package com.wq.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.wq.bos.dao.StaffDao;
import com.wq.bos.dao.base.impl.BaseDaoImpl;
import com.wq.bos.domain.Staff;
import com.wq.bos.utils.PageBean;
/**
 * dao 层进行数据操作
 * @author Administrator
 *
 */

@Repository("staffDao")
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao {
    
    /**
     * 删除用户(将用户的某个标记字段修)
     */
    @Override
    public void executeUpdate( final Serializable id) {
        getHibernateTemplate().execute(new HibernateCallback<List<Staff>>() {
            @Override
            public List<Staff> doInHibernate(Session session) throws HibernateException {
                String hql = "from Staff  where  id=?";
                Query query = session.createQuery(hql);
                query.setParameter(0, id);
                List<Staff> list = query.list();
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setDeltag("1");
                    }
                }
                return list;
            }
        });

            
       
        
    }

    @Override
    public void doRestore(final Serializable id) {
        getHibernateTemplate().execute(new HibernateCallback<List<Staff>>() {

            @Override
            public List<Staff> doInHibernate(Session session) throws HibernateException {
                String hql = "from Staff  where  id=?";
                Query query = session.createQuery(hql);
                query.setParameter(0, id);
                List<Staff> list = query.list();
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setDeltag("0");
                    }
                }
                return list;
            }
        });
       
    }

    
    

}
