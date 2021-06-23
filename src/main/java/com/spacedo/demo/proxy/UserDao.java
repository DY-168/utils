package com.spacedo.demo.proxy;

public class UserDao implements IUserDao{

    @Override
    public void save() {
        System.out.println("保存用户");
        test();
    }

    @Override
    public void test(){
        System.out.println("test");
    }

}
