package com.wq.bos.web.action.base;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wq.bos.utils.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    public static final String HOME = "home";

    protected T model;
    
    protected PageBean pageBean=new PageBean();
    
    //因为BaseAction 所以不能具体化,只能在我们的构造函数中去获取我们的action类型
    DetachedCriteria detachedCriteria = null;

    @Override
    public T getModel() {
        // TODO Auto-generated method stub
        return model;
    }
    //分页的封装
   //当前的页面
    public void setPage(int page) {
        pageBean.setCurrentPage(page);
    }
    //每页显示的数据条数
    public void setRows(int rows) {
        pageBean.setPageSize(rows);
    }

    // 在构造中动态的去获取实体类,通过反射创建model对象
    public BaseAction() {
        // 获取父类的class属性
        ParameterizedType genericsSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        // 获取泛型数组
        Type[] actualTypeArguments = genericsSuperclass.getActualTypeArguments();
        Class<T> entityClass = (Class<T>) actualTypeArguments[0];
        //具体化,通过反射创建对象
        detachedCriteria=DetachedCriteria.forClass(entityClass);
        pageBean.setDetachedCriteria(detachedCriteria);
        // 通过反射创建对象
        try {
            model= entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    //将java对象转换成json对象,并且响应到客户端
    public  void javaToJson(Object obj,String[] exclueds) {
        JsonConfig jsonConfig = new JsonConfig();
        //去掉我们不需要的json参数
        jsonConfig.setExcludes(exclueds);
        String json = JSONObject.fromObject(obj,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json ;charset=utf-8");
        try {
            //响应到前端的页面
            ServletActionContext.getResponse().getWriter().print(json);
            System.out.println(json);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //重载javaToJson方法
    public void javaToJson(List  list,String[] exclueds) {
        JsonConfig jsonConfig = new JsonConfig();
        //去掉我们不需要的json参数
        jsonConfig.setExcludes(exclueds);
        String json = JSONArray.fromObject(list,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json ;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
