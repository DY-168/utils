package com.spacedo.demo.proxy;

public class UserDaoStaticProxy implements IUserDao{
    private UserDao ud = new UserDao();

    @Override
    public void save() {
        System.out.println("静态代理操作，记录日志:save() start");
        ud.save();
        System.out.println("静态代理操作，记录日志:save() end");
    }

    @Override
    public void test() {
        System.out.println("静态代理操作，记录日志:test() start");
        ud.test();
        System.out.println("静态代理操作，记录日志:test() end");
    }

}
