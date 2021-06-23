package com.spacedo.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogJdkDynamicProxy<T, I> implements InvocationHandler {
    private T t;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result=null;
        System.out.println("JDK动态代理，记录日志:"+method.getName()+":start");
        method.invoke(t, args);
        System.out.println("JDK动态代理，记录日志:"+method.getName()+":emd");
        return result;
    }

    public LogJdkDynamicProxy(T t){
        this.t = t;
    }

    public I getProxy(){
        return(I) Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), this);
    }

}
