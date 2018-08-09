package com.wq.bos.service;

import java.util.List;

import com.wq.bos.domain.Decidedzone;
import com.wq.bos.utils.PageBean;

public interface DecidedzoneService {

    void add(Decidedzone model, String[] subareaId);

    // 分页查询
    void pageQuery(PageBean pageBean);

}
