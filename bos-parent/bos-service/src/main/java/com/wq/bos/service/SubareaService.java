package com.wq.bos.service;

import java.util.List;

import com.wq.bos.domain.Subarea;
import com.wq.bos.utils.PageBean;

public interface SubareaService {
    // 分页查询
    void pageQuery(PageBean pageBean);

    // 添加
    void save(Subarea model);

    // 导出,查询所有
    List<Subarea> findAll();

    // 查询没有没关联的分区
    List<Subarea> findListNotAssociation();
}
