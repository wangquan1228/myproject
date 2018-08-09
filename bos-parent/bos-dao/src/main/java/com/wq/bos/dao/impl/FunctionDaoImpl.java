package com.wq.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wq.bos.dao.FunctionDao;
import com.wq.bos.dao.base.impl.BaseDaoImpl;
import com.wq.bos.domain.Function;

/** 
*
* @author : wangquan
* @date ：2018年8月6日 上午10:41:06
* 
*/
@Repository("functionDao")
public class FunctionDaoImpl  extends BaseDaoImpl<Function> implements FunctionDao{

  //重写父类查找所有方法  查询顶级权限
    public List<Function> findAll(){
        String hql = "FROM Function f WHERE f.parentFunction IS NULL";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
        return list;
    }


    //查询所有权限
    @Override
    public List<Function> findFunctionListByUserId(String id) {
        System.out.println("测试");
        String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles"
                + " r LEFT OUTER JOIN r.users u WHERE u.id = ?";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql,id);
        return list;
    }

}
