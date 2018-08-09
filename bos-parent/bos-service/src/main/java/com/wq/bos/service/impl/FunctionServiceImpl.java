package com.wq.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wq.bos.dao.FunctionDao;
import com.wq.bos.domain.Function;
import com.wq.bos.service.FunctionService;
import com.wq.bos.utils.PageBean;

/** 
*
* @author : wangquan
* @date ：2018年8月6日 上午10:38:25
* 
*/
@Service("functionService")
@Transactional
public class FunctionServiceImpl implements FunctionService {
    //注入dao
    @Autowired
    private FunctionDao functionDao;

    //查询所有
    public List<Function> findAll() {
        return functionDao.findAll();
    }

    //添加权限
    public void add(Function model) {
        Function parentFunction = model.getParentFunction();
        if (parentFunction != null && parentFunction.getId().equals("")) {
            model.setParentFunction(null);
        }
        functionDao.save(model);
        
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        functionDao.pageQuery(pageBean);
        
    }

}
