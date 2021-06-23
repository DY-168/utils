package com.spacedo.util;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ClassUtilDemo {

    @SneakyThrows
    public static void main(String[] args) {
        InputStream resourceAsStream = ClassUtil.getClassLoader().getResourceAsStream("logback.xml");
        InputStreamReader isr = new InputStreamReader(resourceAsStream);//读取

        char []cha = new char[1024];
        int len = isr.read(cha);
        System.out.println(new String(cha,0,len));
        isr.close();
    }
}
