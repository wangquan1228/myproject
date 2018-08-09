package com.wq.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.wq.bean.BaseDict;
import com.wq.service.BaseDictService;

import net.sf.json.JSONArray;

@Controller("baseDictAction")
public class BaseDictAction extends ActionSupport {
    
    @Resource(name="baseDictService")
    private BaseDictService baseDictService;

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }
    
    //属性驱动
    
    private String dict_type_code;

    public String getDict_type_code() {
        return dict_type_code;
    }

    public void setDict_type_code(String dict_type_code) {
        this.dict_type_code = dict_type_code;
    }
        
    @Override
    public String execute() throws Exception {
        //1、调用Service根据typecode获得数据字典对象list
        List<BaseDict> list = baseDictService.getListByTpyeCode(dict_type_code);
        //2、将list转换为json格式
        String json = JSONArray.fromObject(list).toString();
        //3、将json发送给浏览器
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(json);
        return null;//告诉struts2不需要进行结果处理
    }
}
