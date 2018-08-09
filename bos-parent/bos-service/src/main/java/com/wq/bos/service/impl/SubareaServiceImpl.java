package com.wq.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wq.bos.dao.SubareaDao;
import com.wq.bos.domain.Subarea;
import com.wq.bos.service.SubareaService;
import com.wq.bos.utils.PageBean;

/**
 * 
 * @author wangquan
 * @date 2018年7月26日
 *
 */
@Service("subareaService")
@Transactional
public class SubareaServiceImpl implements SubareaService {
    // 注入dao层
    @Autowired
    private SubareaDao subareaDao;

    @Override
    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    @Override
    public void save(Subarea model) {
        subareaDao.save(model);
    }

    @Override
    public List<Subarea> findAll() {

        return subareaDao.findAll();
    }

    @Override
    public List<Subarea> findListNotAssociation() {
        return subareaDao.findByCriteria(DetachedCriteria.forClass(Subarea.class).add(Restrictions.isNull("decidedzone")));
    }

}
