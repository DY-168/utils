package com.spacedo.demo;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class FileUtilsDemo {
    //原地址 https://blog.csdn.net/qq_20610631/article/details/81100685
    //官方文档：http://commons.apache.org/proper/commons-io/javadocs/api-2.4/index.html
    //官方指引：http://commons.apache.org/proper/commons-io/description.html
    @SneakyThrows
    public static void main(String[] args) {
        getProductClass();
        //productPackge();
        //testOneClass();

    }

    private static void testOneClass() throws IOException {
        String javaFileString = FileUtils.readFileToString(new File("D:\\UniversalSettlementReportServiceImpl.java"), "UTF-8");
        String[] words = javaFileString.split("\\s|;");
        Set<String> importNames = new HashSet<>();
        Set<Field> fields = new HashSet<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i].trim();
            word = word.trim();
            if (word.equals("import")) {
                String importName = words[i + 1].trim();
                if(startsWith(importName, "com.ebao.ls.product", "com.ebao.ls.prd", "com.ebao.ls.pd")
                        && !importName.matches("(.*)model(.*)")
                ) {
                    importNames.add(importName);
                }
            }
        }

        for (String importName : importNames) {
            String className = importName.substring(importName.lastIndexOf(".")+1);
            Field field = new Field();
            field.classFullName=importName;
            field.className=className;
            fields.add(field);
        }

        //有属性关键字
        for (int i = 0; i < words.length; i++) {
            String word = words[i].trim();
            word = word.trim();
            if (word.equals("private") || word.equals("public") || word.equals("protected")){
                String hxClassName = words[i + 1].trim();

                for (Field field : fields) {
                    boolean isField = field.className.equals(hxClassName);
                    if (isField) {
                        String fieldName = words[i + 2].trim();
                        field.name=fieldName;
                        break;
                    }
                }
            }
        }

        for (String word : words) {
            String fullMethodName = matchFullMethodName(fields, word);
            if (fullMethodName!=null) {
                System.out.println(fullMethodName);
            }
        }
    }

    private static void productPackge() {
        Collection<File> javaFiles = getJavaFiles(
                "D:/project/idea/cfg",
                "D:/project/idea/product"
        );

        Set<String> packageNames = new HashSet<>();
        for (File javaFile : javaFiles) {
            String packageName = getPackageName(javaFile);
            packageNames.add(packageName);
        }

        for (String packageName : packageNames) {

        }
    }

    @SneakyThrows
    private static String getPackageName(File javaFile) {
        String javaFileString = FileUtils.readFileToString(javaFile, "UTF-8");
        String[] words = javaFileString.split("\\s|;");
        String packageName = null;
        for (int i = 0; i < words.length; i++) {
            String word = words[i].trim();
            if (word.equals("package")) {
                packageName = words[i+1].trim();
                break;
            }
        }
        if (!startsWith(packageName, "com.ebao.ls.product", "com.ebao.ls.prd", "com.ebao.ls.pd")) {
            System.out.println(javaFile.getAbsolutePath());
        }
        return packageName;
    }

    @SneakyThrows
    private static String getClassFullName(File javaFile) {
        String javaFileString = FileUtils.readFileToString(javaFile, "UTF-8");
        String[] words = javaFileString.split("\\s|;");
        String packageName = null;
        String classFullName = null;
        for (int i = 0; i < words.length; i++) {
            String word = words[i].trim();
            if (word.equals("package")) {
                packageName = words[i+1].trim();
            } else if(word.equals("class")){
                classFullName = packageName + "." + words[i+1].trim();
                break;
            } else if(word.equals("enum")){
                classFullName = packageName + "." + words[i+1].trim();
                break;
            } else if(word.equals("interface")){
                classFullName = packageName + "." + words[i+1].trim();
                break;
            }
        }
        if (classFullName == null) {
            System.out.println(javaFile.getAbsolutePath());
        }
        return classFullName;
    }

    private static void getProductClass() throws IOException {
        Collection<File> javaFiles = getJavaFiles(
                "D:/project/idea/pa_baseline",
                "D:/project/idea/pa",
                "D:/project/idea/sofa-lite-config/pa-slite-facade",
                "D:/project/idea/sofa-lite-config/pa-slite-service-proxy"
        );

        Set<String> allImportWords = new TreeSet<>();
        Set<String> allFullMethodNames = new TreeSet<>();

        for (File javaFile : javaFiles) {
            String s = FileUtils.readFileToString(javaFile, "UTF-8");
            String[] words = s.split("\\s|;");
            Set<String> importNames = new HashSet<>();
            Set<Field> fields = new HashSet<>();

            for (int i = 0; i < words.length; i++) {
                String word = words[i].trim();
                word = word.trim();
                if (word.equals("import")) {
                    String importName = words[i + 1].trim();
                    if(startsWith(importName, "com.ebao.ls.product", "com.ebao.ls.prd", "com.ebao.ls.pd")
                            && importName.matches("(.*)model(.*)")
                    ) {
                        importNames.add(importName);
                    }
                }
            }

            for (String importName : importNames) {
                String className = importName.substring(importName.lastIndexOf(".")+1);
                Field field = new Field();
                field.classFullName=importName;
                field.className=className;
                fields.add(field);
            }

            for (int i = 0; i < words.length; i++) {
                String word = words[i].trim();
                word = word.trim();
                if (word.equals("private") || word.equals("public") || word.equals("protected")){
                    String hxClassName = words[i + 1].trim();

                    for (Field field : fields) {
                        boolean isField = field.className.equals(hxClassName);
                        if (isField) {
                            String fieldName = words[i + 2].trim();
                            field.name=fieldName;
                            break;
                        }
                    }
                }
            }

            for (String word : words) {
                String fullMethodName = matchFullMethodName(fields, word);
                if (fullMethodName!=null) {
                    allFullMethodNames.add(fullMethodName);
                }
            }

            allImportWords.addAll(importNames);
        }

        for (String i : allImportWords) {
            System.out.println(i);
        }

        for (String i : allFullMethodNames) {
            System.out.println(i);
        }

        for (String i : allImportWords) {
            boolean has = false;
            for (String m : allFullMethodNames) {
                if (m.substring(0, m.lastIndexOf(".")).equals(i)) {
                    has = true;
                    break;
                }
            }
            if (!has) {
                System.out.println(i);
            }
        }
    }

    private static String matchFullMethodName(Set<Field> fields, String word){
        for (Field field : fields) {
            String methodName = matchMethodName(field.className, word);
            if (methodName != null) return field.classFullName + "." + methodName + "()";
            methodName = matchMethodName(field.name, word);
            if (methodName != null) return field.classFullName + "." + methodName + "()";
        }
        return null;
    }

    private static String matchMethodName(String key, String word) {
        if (key==null) {
            return null;
        }
        int index = word.indexOf(key+".");
        if (index!=-1) {
            int beginIndex = index + key.length() + 1;
            if (word.length()>beginIndex) {
                String s = word.substring(beginIndex);
                int i = s.indexOf("(");
                if (i!=-1) {
                    String methodName = s.substring(0, i);
                    //System.out.println(methodName);
                    return methodName;
                }
            }
        }
        return null;
    }

    private static boolean startsWith(String word, String... prefixes) {
        for (String prefix : prefixes) {
            if (word.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    private static Collection<File> getJavaFiles(String... paths) {
        Collection<File> files = new ArrayList<>();
        for (String path : paths) {
            files.addAll(FileUtils.listFiles(new File(path), new String[]{"java"}, true));
        }
        return files;
    }


    private static void demo() throws IOException {
        //1.写 文件/文件夹 如果目标文件不存在，FileUtils会自动创建
        FileUtils.write(new File("D:/a/b/cxyapi.txt"), "程序换api","UTF-8",true);

        List<String> lines=new ArrayList<>();
        lines.add("欢迎访问:");
        lines.add("www.cxyapi.com");
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

class Field{
    String name;
    String className;
    String classFullName;
}
