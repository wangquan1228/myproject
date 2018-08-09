package com.wq.bos.service;

import java.util.List;

import com.wq.bos.domain.Role;
import com.wq.bos.utils.PageBean;

/** 
*
* @author : wangquan
* @date ：2018年8月6日 下午3:34:27
* 
*/
public interface RoleService {
    //添加权限
    void add(Role model, String functionIds);
    //分页查询
    void pageQuery(PageBean pageBean);
    //获取所有的
    List<Role> findAll();

}
