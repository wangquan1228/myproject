package com.wq.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wq.bos.dao.RegionDao;
import com.wq.bos.domain.Region;
import com.wq.bos.service.RegionService;
import com.wq.bos.utils.PageBean;

/**
 * 
 * @author wangquan
 * @date 2018年7月25日
 *
 */
@Service("regionService")
@Transactional
public class RegionServiceImpl implements RegionService {
    // 注入dao层
    @Autowired
    private RegionDao regionDao;

    /*
     * 分页查询 (non-Javadoc)
     * 
     * @see com.wq.bos.service.RegionService#pageQuery(com.wq.bos.utils.PageBean)
     */
    @Override
    public void pageQuery(PageBean pageBean) {
        regionDao.pageQuery(pageBean);

    }

    /*
     * 事物得开启,实在service层开启的,这里是一次开启,多次执行save方法 导入实现
     * 
     * @see com.wq.bos.service.RegionService#save(java.util.List)
     */
    @Override
    public void save(List<Region> regions) {
        for (Region region : regions) {
            regionDao.save(region);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.wq.bos.service.RegionService#findAll()
     */
    @Override
    public List<Region> findAll() {

        return regionDao.findAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.wq.bos.service.RegionService#save(com.wq.bos.domain.Region)
     */
    @Override
    public void save(Region model) {
        regionDao.save(model);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.wq.bos.service.RegionService#findAll(java.lang.String)
     */
    @Override
    public List<Region> findAll(String term) {
        return regionDao.findAll(term);
    }

}
