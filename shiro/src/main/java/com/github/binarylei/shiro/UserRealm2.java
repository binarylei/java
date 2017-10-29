package com.github.binarylei.shiro;

import com.github.binarylei.bean.UserBean;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

@Component
public class UserRealm2 extends AuthorizingRealm {
    @Override
    //用户权限信息
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1. 根据principals获取用户名
        String username = (String) principals.getPrimaryPrincipal();

        //2. 封装权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //3. 添加权限
        String role = "admin".equals(username) ? "admin" : "user".equals(username) ? "user" : null;
        System.out.println("---------->" + role);
        info.addRole(role);

        //4. 返回权限信息
        return info;
    }

    @Override
    //用户身份信息
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1. 根据token获取用户名
        String username = (String) token.getPrincipal();

        //2. 查询数据库
        UserBean user = new UserBean("admin", "adminadmin", null, false);

        //3. 用户名不存在，密码比对交给shiro完成
        if(user ==  null) {
            throw new UnknownAccountException();
        }

        //4. 根据token获取用户名
        if(Boolean.TRUE.equals(user.isLocked())) {
            throw new LockedAccountException();
        }

        //5. 封装用户身份信息SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名 principal
                user.getPassword(), //密码 credentials
                getName()   //realmName
        );

        //6. 返回用户身份信息
        return info;
    }

}
