package com.quantumfluctuation.demo;

import lombok.SneakyThrows;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class DigestUtilsDemo {

    public static void main(String[] args) {
        //testGetStringMd5Digest();
        testGetFileMd5Digest();
        List<Integer> i = new ArrayList();
    }

    public static void testGetStringMd5Digest(){
        String md5Hex = DigestUtils.md5Hex("中国");
        System.out.println(md5Hex);
    }

    @SneakyThrows
    public static void testGetFileMd5Digest(){
        String md5Hex = DigestUtils.md5Hex(new FileInputStream("\\huge.json"));
        System.out.println(md5Hex);
    }


}
