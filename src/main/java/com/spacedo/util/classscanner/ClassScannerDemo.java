package com.spacedo.util.classscanner;

import java.util.List;

public class ClassScannerDemo {
    public static void main(String[] args) {
        List<Class<?>> classList = new DefaultClassScanner().getClassList("org.apache.commons.lang3");
        for (Class<?> aClass : classList) {
            System.out.println(aClass.getName());
        }
    }
}
