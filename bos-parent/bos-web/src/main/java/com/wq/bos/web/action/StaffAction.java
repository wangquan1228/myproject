package com.wq.bos.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wq.bos.domain.Staff;
import com.wq.bos.service.StaffService;
import com.wq.bos.utils.PageBean;
import com.wq.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 取派员控制页面
 * 
 * @author Administrator
 *
 */
@Controller("staffAction")
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private StaffService staffService;

    private String ids;// 前台传过来的ids(删除时用的)

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    /**
     * 添加取派员
     * 
     * @return
     */
    public String add() {
       staffService.add(model);
       return "list";
    }

    /**
     * 分页查询
     */
    public String query() throws Exception {
        // 调用service层的方法
        staffService.pageQuery(pageBean);
        this.javaToJson(pageBean, new String[] { "currentPage", "detachedCriteria", "pageSize","decidedzones" });
        return NONE;
    }

    /**
     * 删除用户(修改用户的某个字段作为标记)
     */
    @RequiresPermissions("staff-delete")//要想执行该功能,必须拥有权限
    public String deleteBatch() throws Exception {
        staffService.deleteBatch(ids);
        return "list";
    }

    /**
     * 还原用户信息
     */
    public String doRestore() throws Exception {
        staffService.doRestore(ids);
        return "list";
    }

    /**
     * 获取选中的人的信息
     * 
     */
    public String editStaff() throws Exception {
        Staff staff = staffService.findById(model.getId());
        // 使用页面提交的数据进行覆盖
        staff.setName(model.getName());
        staff.setTelephone(model.getTelephone());
        staff.setHaspda(model.getHaspda());
        staff.setStandard(model.getStandard());
        staff.setStation(model.getStation());
        staffService.update(staff);
        return "list";
    }
    
    /*
     * 获取没有被删除的取派员
     */
    public String listAjax() {
        List<Staff> list=staffService.findNotDelete();
        System.out.println(list);
        //将结果转换成json对象,并将decidedzones过滤掉
        this.javaToJson(list, new String [] {"decidedzones"});
        return NONE;
    }
}
