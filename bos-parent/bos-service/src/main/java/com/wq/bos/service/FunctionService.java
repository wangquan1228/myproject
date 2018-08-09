package com.wq.bos.service;

import java.util.List;

import com.wq.bos.domain.Function;
import com.wq.bos.utils.PageBean;

/** 
*
* @author : wangquan
* @date ：2018年8月6日 上午10:37:42
* 
*/
public interface FunctionService {
    //查询权限
    List<Function> findAll();
    //添加权限
    void add(Function model);
    //分页查询
    void pageQuery(PageBean pageBean);

}
