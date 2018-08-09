package com.wq.dao;

import com.wq.bean.LinkMan;

public interface LinkManDao extends BaseDao<LinkMan> {

    void updateLinkMan(Long cust_id);

}
