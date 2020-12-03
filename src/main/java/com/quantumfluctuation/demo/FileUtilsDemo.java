package com.quantumfluctuation.demo;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileUtilsDemo {

    @SneakyThrows
    public static void main(String[] args) {
        //原地址 https://blog.csdn.net/qq_20610631/article/details/81100685
        //官方文档：http://commons.apache.org/proper/commons-io/javadocs/api-2.4/index.html
        //官方指引：http://commons.apache.org/proper/commons-io/description.html
        //1.写 文件/文件夹 如果目标文件不存在，FileUtils会自动创建
        FileUtils.write(new File("D:/a/b/cxyapi.txt"), "程序换api","UTF-8",true);

        List<String> lines=new ArrayList<>();
        lines.add("欢迎访问:");lines.add("www.cxyapi.com");
        FileUtils.writeLines(new File("D:/a/b/cxyapi.txt"),lines,true);

        FileUtils.writeStringToFile(new File("D:/a/b/cxyapi.txt"), "作者：cxy", "UTF-8",true);

        //2.读 文件/文件夹
        //读文件
        System.out.println(FileUtils.readFileToString(new File("D:/a/b/cxyapi.txt"), "UTF-8"));

        System.out.println(FileUtils.readLines(new File("D:/a/b/cxyapi.txt"), "UTF-8")); //返回一个list

        //3.删除 文件/文件夹
        //删除目录
        FileUtils.deleteDirectory(new File("D:/not/cxyapi"));
        //文件夹不是空任然可以被删除，永远不会抛出异常
        FileUtils.deleteQuietly(new File("D:/not/cxyapi"));

        //4.移动 文件/文件夹
        // 文件夹cxyapi1的内容 移动到 文件夹cxyapi2，注意这里 第二个参数文件不存在会引发异常
        FileUtils.moveDirectory(new File("D:/cxyapi1"), new File("D:/cxyapi2"));
        // 文件夹cxyapi2本身及内容 移动到 文件夹cxyapi2
        FileUtils.moveDirectoryToDirectory(new File("D:/cxyapi2"), new File("D:/cxyapi3"), true);
        //其中moveToDirectory和其他的区别是 它能自动识别操作文件还是文件夹
        //static void:moveFileToDirectory(srcFile, destDir, createDestDir)
        //static void:moveFile(File srcFile, File destFile)
        //static void:moveToDirectory(File src, File destDir, boolean createDestDir)

        //5.copy 文件/文件夹
        //结果是cxyapi和cxyapi1在同一目录
        FileUtils.copyDirectory(new File("D:/cxyapi"), new File("D:/cxyapi1"));
        //结果是将cxyapi拷贝到cxyapi2下
        FileUtils.copyDirectoryToDirectory(new File("D:/cxyapi"), new File("D:/cxyapi2"));
        //拷贝文件
        FileUtils.copyFile(new File("d:/cxyapi.xml"), new File("d:/cxyapi.xml.bak"));
        //拷贝文件到目录中
        FileUtils.copyFileToDirectory(new File("d:/cxyapi.xml"), new File("d:/cxyapi"));
        //拷贝url到文件
        FileUtils.copyURLToFile(new URL("http://www.cxyapi.com/rss/cxyapi.xml"), new File("d:/cxyapi.xml"));

        //6. 其他
        //判断是否包含文件或者文件夹
        boolean b=FileUtils.directoryContains(new File("D:/cxyapi"), new File("D:/cxyapi/cxyapi.txt"));
        System.out.println(b);

        //获得临时目录 和 用户目录
        System.out.println(FileUtils.getTempDirectoryPath());
        System.out.println(FileUtils.getUserDirectoryPath());

        //打开流，如果不存在创建文件及其目录结构
        //第二个参数表示 文件流是否是追加方式
        FileOutputStream fos=FileUtils.openOutputStream(new File("D:/cxyapi/cxyapi.txt"),true);
        fos.write(new String("欢迎访问：www.cxyapi.com\r\n").getBytes());
        fos.close();

        //文件 或 文件夹大小
        System.out.println(FileUtils.sizeOf(new File("D:/cxyapi")));
        System.out.println(FileUtils.sizeOfDirectory(new File("D:/cxyapi")));

        // 比较两个字节输入流中的内容是否相同
        // boolean flag = IOUtils.contentEquals(input1, input2);
        // System.out.println(flag);
    }
}
