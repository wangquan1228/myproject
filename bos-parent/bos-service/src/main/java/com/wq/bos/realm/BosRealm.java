package com.wq.bos.realm;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.wq.bos.dao.FunctionDao;
import com.wq.bos.dao.UserDao;
import com.wq.bos.domain.Function;
import com.wq.bos.domain.User;

import freemarker.ext.beans.BeansWrapper;

/**
 *
 * @author : wangquan
 * @date ：2018年8月5日 下午8:50:12
 * 
 */
public class BosRealm extends AuthorizingRealm {
    // 注入dao
    @Autowired
    private UserDao userDao;
    @Autowired
    private FunctionDao functionDao;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection paramPrincipalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
      //动态获取当前用户对象
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Function> list = null;
        if (user.getUsername().equals("admin")) {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);      
            list = functionDao.findByCriteria(detachedCriteria);
        }
        //当不是admin 时候
        else {
               
            list = functionDao.findFunctionListByUserId(user.getId());
        }
        for (Function function : list) {
            info.addStringPermission(function.getCode());
        }
        return info;
    }

    // 验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken paramAuthenticationToken)
            throws AuthenticationException {
        // 获取页面传来的用户名和密码
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) paramAuthenticationToken;
        String userName = passwordToken.getUsername();
        // 根据用户名获得数据库信息
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            // 页面输入的用户名不存在
            return null;

        }
        // 认证信息对象
        AuthenticationInfo aInfo = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        return aInfo;
    }

}
