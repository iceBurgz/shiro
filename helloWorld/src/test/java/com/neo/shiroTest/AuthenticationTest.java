package com.neo.shiroTest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
shiro认证、授权过程
 */
public class AuthenticationTest {

    //构建连接数据库的realm
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    //添加账号信息模拟数据库
    @Before
    public void addUser() {
        simpleAccountRealm.addAccount("zhubin","123456","admin","user");
    }

    @Test
    public void testAuthentication() {

        //1.构建SecurityManagement环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //2.设置realm
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //3.主体工具类装配环境
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //4.新建一个认证主体
        Subject subject = SecurityUtils.getSubject();

        //5.new一个token
        UsernamePasswordToken token = new UsernamePasswordToken("zhubin" ,"123456");
        //6.登录
        subject.login(token);

        //7.查看认证是否通过
        System.out.println("isAuthenticated:"+subject.isAuthenticated());

        //8.检查用户的角色
        subject.checkRoles("admin","user");

        //9.登出
        subject.logout();
    }
}
