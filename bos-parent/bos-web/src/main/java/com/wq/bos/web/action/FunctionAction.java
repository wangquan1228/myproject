package com.wq.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wq.bos.domain.Function;
import com.wq.bos.service.FunctionService;
import com.wq.bos.web.action.base.BaseAction;

/** 
*
* @author : wangquan
* @date ：2018年8月6日 上午10:34:17
* 
*/
@Controller("functionAction")
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
    //注册dao
    @Autowired
    private FunctionService functionService;
    
    //查询所有的权限
    public String  listAjax() {
        List<Function> list = functionService.findAll();
        javaToJson(list, new String[] {"parentFunction","roles"});
        return NONE;
    }
    //添加权限
    public String add() {
        functionService.add(model);
         return "list";
    }
    //分页查询
    public String query() {
        String page = model.getPage();
        pageBean.setCurrentPage(Integer.parseInt(page));
        functionService.pageQuery(pageBean);
        javaToJson (pageBean, new String [] {"parentFunction","roles","children"});
         
        return NONE;
    }

}
