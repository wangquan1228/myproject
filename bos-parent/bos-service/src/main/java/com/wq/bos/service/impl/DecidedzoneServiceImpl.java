package com.wq.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wq.bos.dao.DecidedzoneDao;
import com.wq.bos.dao.SubareaDao;
import com.wq.bos.domain.Decidedzone;
import com.wq.bos.domain.Subarea;
import com.wq.bos.service.DecidedzoneService;
import com.wq.bos.utils.PageBean;

@Service("decidedzoneService")
@Transactional
public  class DecidedzoneServiceImpl implements DecidedzoneService {
    //注入dao层
    @Autowired
    private DecidedzoneDao decidedzoneDao;
    @Autowired
    private SubareaDao subareaDao;
   
   //添加定区管理
    public void add(Decidedzone model, String[] subareaId) {
      //存储decidedzone 表 跟其他表没有关系,可以直接存
        decidedzoneDao.save(model);
        //循环遍历id 将关联的分区表分别注入定区id 以表示产生一个关联关系
        for (String id : subareaId) {
            //通过id从数据库返回subarea
            Subarea subarea = subareaDao.getById(id);
            //分区关联定区 ,分区表(一的一方)维护定区表外键(多的一方)  
            subarea.setDecidedzone(model);
        }
    }

   //分页查询
    public void pageQuery(PageBean pageBean) {
       
         decidedzoneDao.pageQuery(pageBean);
    }

}
