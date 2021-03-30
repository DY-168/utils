package com.quantumfluctuation.demo;

public class ZipDemo {
    public static void main(String[] args) {
        System.out.println(getMax(null,null));
        System.out.println(getMax(null,1));
        System.out.println(getMax(1,null));
        System.out.println(getMax(1,1));
    }

    public static Integer getMax(Integer a, Integer b) {
        if (a == null) {
            return b;
        }else if(b == null){
            return a;
        }else {
            return Math.max(a, b);
        }
    }
}
