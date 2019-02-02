package com.neo.shiroTest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmTest {

    //DruidDataSource dataSource = new DruidDataSource();

    @Test
    public void testAuthentication() {

        JdbcRealm jdbcRealm = new JdbcRealm();



        //1.构建SecurityManagement环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        //2.设置realm

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


    }
}
