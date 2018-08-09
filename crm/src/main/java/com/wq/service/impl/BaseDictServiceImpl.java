package com.wq.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wq.bean.BaseDict;
import com.wq.dao.BaseDictDao;
import com.wq.service.BaseDictService;

@Service("baseDictService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class BaseDictServiceImpl implements BaseDictService{
    @Resource(name="baseDictDao")
    private BaseDictDao baseDictDao;
    
    public void setBaseDictDao(BaseDictDao baseDictDao) {
        this.baseDictDao = baseDictDao;
    }

    public List<BaseDict> getListByTpyeCode(String dict_type_code) {
         
        return baseDictDao.getListByTypeCode(dict_type_code);
    }

}
