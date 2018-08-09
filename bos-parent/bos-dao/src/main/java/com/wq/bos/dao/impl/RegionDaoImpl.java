package com.wq.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wq.bos.dao.RegionDao;
import com.wq.bos.dao.base.impl.BaseDaoImpl;
import com.wq.bos.domain.Region;

/**
 * 区域设置:dao层的实现
 * 
 * @author wangquan
 * @date 2018年7月24日
 *
 */
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao {

    @Override
    public List<Region> findAll(String term) {
        String hql = "FROM Region r WHERE r.shortcode LIKE ? " + " OR r.citycode LIKE ? OR r.province LIKE ? "
                + "OR r.city LIKE ? OR r.district LIKE ?";
        List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql, "%" + term + "%", "%" + term + "%", "%" + term + "%",
                "%" + term + "%", "%" + term + "%");
        return list;
    }

}
