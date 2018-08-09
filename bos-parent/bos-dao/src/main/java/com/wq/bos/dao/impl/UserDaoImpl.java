package com.wq.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wq.bos.dao.UserDao;
import com.wq.bos.dao.base.impl.BaseDaoImpl;
import com.wq.bos.domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    /**
     * 根据用户名和密码查询对象
     */
    public User login(String userName, String password) {
        String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, userName, password);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
   /*
    * (non-Javadoc)
    * @see com.wq.bos.dao.UserDao#findUserByUserName(java.lang.String)
    */
    @Override
    public User findUserByUserName(String userName) {
        String hql = "FROM User u WHERE u.username = ? ";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, userName);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;

    }

}
