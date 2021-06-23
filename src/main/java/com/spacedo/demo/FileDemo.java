package com.spacedo.demo;

import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileDemo {


    @SneakyThrows
    public static void main(String[] args) {
        /*File file = new File("");
        String filePath = null;
        try {
            filePath = file.getCanonicalPath();
            System.out.println(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*File file=new File("D:\\project\\idea\\cfg\\utils/huge.json");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/


        String content = "我是DY";

        File file = new File("D:\\project\\a.txt");

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();

        System.out.println("Done");

    }

}
