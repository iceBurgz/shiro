package com.neo.shiroTest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 使用ini文件放置用户信息，使用IniRealm作为数据源
 */
public class IniRealmTest {


    @Test
    public void testAuthentication() {

        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        //1.构建SecurityManagement环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        //2.设置realm
        defaultSecurityManager.setRealm(iniRealm);

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
        subject.checkRole("admin");

        //9.检查用户权限
        subject.checkPermission("user.delete");
        subject.checkPermission("user.update");

        //10.登出
        subject.logout();
    }
}
