package com.spacedo.demo.proxy;

import net.sf.cglib.proxy.Enhancer;
//import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        //testCglib1();
        //testJdk();

        //getJdkProxyClass();
        /*IUserDao userDao = new JdkProxy(new LogJdkDynamicProxy(new UserDao()));
        userDao.save();*/
        new LogCglibDynamicProxyTarget<UserDao>(new UserDao()).getProxy(UserDao.class).save();
    }

    /*private static void getJdkProxyClass() {
        try {
            byte[] proxyClass = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{UserDao.class});
            FileOutputStream outputStream = new FileOutputStream(new File("d:\\$Proxy0.class"));
            outputStream.write(proxyClass);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){

        }
    }*/

    private static void testCglib1() {
        UserDao userDao = new LogCglibDynamicProxy().getProxy(UserDao.class);
        userDao.save();
    }

    private static void testCglib() {
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(UserDao.class);
        enhancer.setCallback(new LogCglibDynamicProxy());
        //生成代理类
        UserDao ud=(UserDao) enhancer.create();
        ud.save();
    }

    private static void testJdk1() {
        IUserDao userDao1 = new LogJdkDynamicProxy<UserDao, IUserDao>(new UserDao()).getProxy();
        userDao1.save();
    }

    private static void testJdk() {
        IUserDao userDao = (IUserDao) Proxy.newProxyInstance(Test.class.getClassLoader(), UserDao.class.getInterfaces(), new LogJdkDynamicProxy(new UserDao()));
        userDao.save();
    }
}
