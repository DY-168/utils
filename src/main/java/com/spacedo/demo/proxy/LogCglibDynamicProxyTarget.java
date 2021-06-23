package com.spacedo.demo.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class LogCglibDynamicProxyTarget<T> implements MethodInterceptor {
    private T t;

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib动态代理，记录日志:"+method.getName()+":start");
        //Object object = methodProxy.invokeSuper(o, args);
        Object object = method.invoke(t, args);
        System.out.println("Cglib动态代理，记录日志:"+method.getName()+":emd");
        return object;
    }

    public LogCglibDynamicProxyTarget(T t){
        this.t=t;
    }

    public <T> T getProxy(Class<T> tClass){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(tClass);
        enhancer.setCallback(this);
        //生成代理类
        return (T) enhancer.create();
    }

}
