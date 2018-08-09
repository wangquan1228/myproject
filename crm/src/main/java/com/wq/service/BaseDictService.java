package com.wq.service;

import java.util.List;

import com.wq.bean.BaseDict;

public interface BaseDictService {
    
    //根据数据字典类型字段获得数据字典对象

    List<BaseDict> getListByTpyeCode(String dict_type_code);
}
