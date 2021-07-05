package com.spacedo.demo;

public class StringDemo {
    public static void main(String[] args) {
        System.out.println();

        for (String s : "1212[[&]]12121\\[\\[&\\]\\]454352[[&12121".split("\\[\\[&\\]\\]")) {
            System.out.println(s);
        }
    }
}
