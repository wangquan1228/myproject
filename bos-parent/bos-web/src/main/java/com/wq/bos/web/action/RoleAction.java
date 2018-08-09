package com.wq.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wq.bos.domain.Role;
import com.wq.bos.service.RoleService;
import com.wq.bos.web.action.base.BaseAction;

/**
 *
 * @author : wangquan
 * @date ：2018年8月6日 下午3:30:48
 * 
 */
@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
    // 注入service
    @Autowired
    private RoleService roleService;

    // 属性驱动
    public String functionIds;

    public String getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    // 添加角色
    public String add() {
        roleService.add(model,functionIds);
        return "list";
    }
    //分页查询
    public String query() {
        roleService.pageQuery(pageBean);
        javaToJson(pageBean, new String[] {"functions","users"});
        return NONE;
    }
    //返回所有的json数据
    public String listAjax() {
       List<Role>list= roleService.findAll();
       javaToJson(list, new String[] {"functions","users"});
        return NONE;
    }
}
