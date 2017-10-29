package com.github.binarylei.helloworld;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * shiro快速入门 - Hellowold
 */
public class Hellowold {

    private static final transient Logger logger = LoggerFactory.getLogger(Hellowold.class);

    public static void main(String[] args) {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        //IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3. 获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("name", "join");

        //4. 用户认证
        if(!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("guest", "guest");
            //记住我
            token.setRememberMe(true);

            try {
                currentUser.login(token);
            } catch (UnknownAccountException e) {
                logger.info("用户名不存在 " + token.getPrincipal());
                return;
            } catch (IncorrectCredentialsException ex) {
                logger.info("用户密码错误 ", token.getPrincipal());
                return;
            } catch (LockedAccountException e) {
                logger.info("用户被锁定 " + token.getPrincipal());
                return;
            } catch (AuthenticationException e) {
                logger.info("认证失败" + token.getPrincipal());
                return;
            }
            /**
             * DisabledAccountException（禁用的帐号）
             * LockedAccountException（锁定的帐号）
             * UnknownAccountException（错误的帐号）
             * ExcessiveAttemptsException（登录失败次数过多）
             * IncorrectCredentialsException （错误的凭证）
             * ExpiredCredentialsException（过期的凭证）
             */

            logger.info("用户名登陆成功 " + token.getPrincipal());

            //5. 权限认证
            if (currentUser.hasRole("admin")) {
                logger.info(token.getPrincipal() + "有admin的权限");
            } else {
                logger.info(token.getPrincipal() + "没有admin的权限");
            }

            if (currentUser.isPermitted("delete:xxxxx")) {
                logger.info(token.getPrincipal() + "有执行delete:xxxxx的权限");
            } else {
                logger.info(token.getPrincipal() + "没有执行delete:xxxxx的权限");
            }

            //6. 登出
            currentUser.logout();
        }

    }
}
