package com.wq.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wq.bos.dao.RoleDao;
import com.wq.bos.domain.Function;
import com.wq.bos.domain.Role;
import com.wq.bos.service.RoleService;
import com.wq.bos.utils.PageBean;

/**
 *
 * @author : wangquan
 * @date ：2018年8月6日 下午3:35:16
 * 
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
    // 注入dao
    @Autowired
    private RoleDao roleDao;

    // 添加角色
    public void add(Role model, String functionIds) {
        roleDao.save(model);
        if (StringUtils.isNotBlank(functionIds)) {
            // functionIds "1231321,121212.121212"
            String[] fIds = functionIds.split(",");
            for (String functionId : fIds) {
                // 手动构建一个权限对象,设置id,对象状态应该是托管状态
                Function function = new Function(functionId);
                // 角色 权限 关联
                model.getFunctions().add(function);
            }

        }
    }

    // 分页查询
    @Override
    public void pageQuery(PageBean pageBean) {
        roleDao.pageQuery(pageBean);
    }
    
    //查询所有
    @Override
    public List<Role> findAll() {
       
        return roleDao.findAll();
    }

}
