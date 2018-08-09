package com.wq.bos.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wq.bos.dao.StaffDao;
import com.wq.bos.domain.Staff;
import com.wq.bos.service.StaffService;
import com.wq.bos.utils.PageBean;

@Service("staffService")
@Transactional
public class StaffServiceImpl implements StaffService {

        @Autowired
        private StaffDao staffDao;
        /**
         * 存储取派员信息
         */
        @Override
        public void add(Staff model) {
         staffDao.save(model);
        }
        
        /**
         * 查询所有的取派员的信息
         */
        @Override
        public void getAllList() {
           staffDao.findAll();
        }
        /*
         * (non-Javadoc) 分页查询
         * @see com.wq.bos.service.StaffService#pageQuery(com.wq.bos.utils.PageBean)
         */
        public void pageQuery(PageBean pageBean) {
             staffDao.pageQuery(pageBean);
            
        }
        /**
         * 删除用户(修改某个字段)
         */
        @Override
        public void deleteBatch(String ids) {
             //判断ids是否为空字符串
            if(StringUtils.isNotBlank(ids)) {
                //将字符串分割为数组
                String[]  staffIds=ids.split(",");
                for (String id : staffIds) {
                    //执行修改语句
                    staffDao.executeUpdate(id);
                }
            }
            
        }
        /**
         * 获取该取派员信息
         */
        @Override
        public Staff getStaff(String id) {
             
            return staffDao.getById(id);
        }
        /**
         * 修改取派员的信息
         */
        @Override
        public void update(Staff model) {
             staffDao.update(model);
        }

        @Override
        public void doRestore(String ids) {
            //判断ids是否为空字符串
            if(StringUtils.isNotBlank(ids)) {
                //将字符串分割为数组
                String[]  staffIds=ids.split(",");
                for (String id : staffIds) {
                    //执行修改语句
                    staffDao.doRestore(id);
                }
            }
            
            
        }
        /**
         * 查询某个人信息
         */
        @Override
        public Staff findById(Serializable id) {
             return staffDao.getById(id);
        }

        @Override
        public List<Staff> findNotDelete() {
             
            DetachedCriteria detachedCriteria  = DetachedCriteria.forClass(Staff.class);
            //添加过滤条件,当为0的时候查询
            detachedCriteria.add(Restrictions.ne("deltag","1"));
            return staffDao.findByCriteria(detachedCriteria);
        }
}
