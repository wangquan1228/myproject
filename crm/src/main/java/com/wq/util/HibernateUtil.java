package com.wq.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    //spring获取bean为sessionFactory
    private static SessionFactory  sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //
    public static Session openSession() {
        Session session = sessionFactory.openSession();
        return session;
    }

    // 获取与现场绑定的Session
    public static Session getCurrentSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    // 关闭资源
     
     

     
}