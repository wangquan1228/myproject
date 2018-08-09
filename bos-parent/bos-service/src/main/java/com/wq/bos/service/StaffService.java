package com.wq.bos.service;

import java.io.Serializable;
import java.util.List;

import com.wq.bos.domain.Staff;
import com.wq.bos.utils.PageBean;

public interface StaffService {

    public void add(Staff model);

    public void getAllList();

    public void pageQuery(PageBean pageBean);

    public void deleteBatch(String ids);

    public Staff getStaff(String id);

    // 修改用户信息
    public void update(Staff model);

    // 还原用户信息
    public void doRestore(String ids);

    // 根据id查询某个人
    public Staff findById(Serializable id);
    //寻找没有删除的取派员
    public List<Staff> findNotDelete();

}
