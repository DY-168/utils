package com.quantumfluctuation.demo;

import java.io.File;
import java.io.IOException;

public class FileDemo {


    public static void main(String[] args) {
        /*File file = new File("");
        String filePath = null;
        try {
            filePath = file.getCanonicalPath();
            System.out.println(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        File file=new File("D:\\project\\idea\\cfg\\utils/huge.json");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
