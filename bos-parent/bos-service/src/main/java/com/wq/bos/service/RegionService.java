package com.wq.bos.service;

import java.util.List;

import com.wq.bos.domain.Region;
import com.wq.bos.utils.PageBean;

/**
 * region接口设计
 * @author wangquan
 * @date 2018年7月24日
 *
 */
public interface RegionService {
    /**
     * 分页插叙缓存
     * @param pageBean
     */
    void pageQuery(PageBean pageBean);
    /**
     * 导入接口
     * @param regions
     */
    void save(List<Region> regions);
    /**
     * 查询所有的对象
     * @return
     */
    List<Region> findAll();
    //添加region
    void save(Region model);
    
     
    List<Region> findAll(String term);

}
