package com.github.binarylei.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void toIndex(HttpServletResponse res) {
        try {
            res.sendRedirect("/");
        } catch (IOException e) {
            ;
        }
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user() {
        return "user";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String unauthorized() {
        return "403";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String check(String username, String password) {
        Subject currentUser = SecurityUtils.getSubject();

        //1. 判断身份信息认证是否通过
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                currentUser.login(token);
                System.out.println(username + "登陆成功！！");
            } catch (UnknownAccountException e) {
                logger.info(username + "用户名不存在");
                return "login";
            } catch (IncorrectCredentialsException ex) {
                logger.info(username + "用户密码错误");
                return "login";
            } catch (LockedAccountException e) {
                logger.info(username + "用户被锁定");
                return "login";
            } catch (AuthenticationException e) {
                logger.info(username + "认证失败");
                return "login";
            }
        }
        return "redirect:/";
    }
}
