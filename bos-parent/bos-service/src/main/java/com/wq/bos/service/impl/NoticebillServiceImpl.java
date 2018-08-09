package com.wq.bos.service.impl;

 

import java.sql.Timestamp;

import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wq.bos.dao.DecidedzoneDao;
import com.wq.bos.dao.NoticebillDao;
import com.wq.bos.domain.Decidedzone;
import com.wq.bos.domain.Noticebill;
import com.wq.bos.domain.Staff;
import com.wq.bos.domain.User;
import com.wq.bos.domain.Workbill;
import com.wq.bos.service.NoticebillService;
import com.wq.bos.utils.BosUtils;
import com.wq.crm.utils.CustomerService;

/** 
*
* @author : wangquan
* @date ：2018年8月4日 下午3:26:47
* 
*/
@Service
@Transactional
public class NoticebillServiceImpl implements NoticebillService {
    //注入dao
    @Autowired
    private NoticebillDao noticebillDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DecidedzoneDao decidedzoneDao;

    @Override
    public void save(Noticebill model) {
      //获取当前登录的用户
        User user = BosUtils.getUser();
        //设置当前登录用户
        model.setUser(user);
        //model存入数据库
        noticebillDao.save(model);
        
        //获取客户地址
        String pickaddress = model.getPickaddress();
        //远程调用cem,根据地址获取定区id
    
        String decidedzoneId = customerService.findDecidedzoneByAddress(pickaddress);
        System.out.println(decidedzoneId);
        //判断客户是否有定区id
        if (decidedzoneId != null) {
            //如果有
            Decidedzone decidedzone = decidedzoneDao.getById(decidedzoneId);
            //获取取派员
            Staff staff = decidedzone.getStaff();
            //业务通知单关联取派员
            model.setStaff(staff);
            //设置分单类型
            model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
            //为取派员生成一个工单
            Workbill workbill = new Workbill();
            workbill.setAttachbilltimes(0);//追单次数
            //获取当前系统时间
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
            //工单关联通知单 
            workbill.setNoticebill(model);
            //设置取件状态
            workbill.setPickstate(Workbill.PICKSTATE_NO);
            //获取备注信息 
            workbill.setRemark(model.getRemark());
            //关联取派员
            workbill.setStaff(staff);
            //工单类型
            workbill.setType(Workbill.TYPE_1);
            //发送短信++++++
        }else {
            //没有查询到定区id,不能完成自动分单 ,需要人工受理
            model.setOrdertype(Noticebill.ORDERTYPE_MAN);
        }
        
    }

}
