package com.quantumfluctuation;

import com.alibaba.fastjson.JSON;

public class Test1 {
    public static void main(String[] args) {
        B b = new B();
        A a = new A();
        a.setB(b);
        b.setA(a);
        String aStr = JSON.toJSONString(a);
        System.out.println(aStr);
    }
}

class A{
    private String name="nameA";
    private B b;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}

class B{
    private String name="nameB";
    private A a;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
