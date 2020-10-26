package com.quantumfluctuation.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONWriter;
import com.quantumfluctuation.util.FastJsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FastJsonDemo {


    public static void main(String[] args) throws IOException {
        //FileUtils.getFile("D:\\project\\idea\\cfg\\utils/huge.json");
        /*FileWriter jsonFileWrite = new FileWriter("D:\\project\\idea\\cfg\\utils/huge.json");
        JSONWriter writer = new JSONWriter(jsonFileWrite); // 把json对象写入到哪里
        writer.startObject();  //先调用
        writer.writeObject();
        for (int i = 0; i < 10; ++i) {
            writer.writeKey("x" + i);         // 挨个写入key
            writer.writeValue(VO.getInstance()); // 挨个写入value
        }
        writer.writeKey("arr");         // 挨个写入key
        writer.startArray();  //先调用
        for (int i = 0; i < 10; ++i) {
            writer.writeValue(new VO(i)); // 挨个写入value
        }
        writer.endArray();  //先调用
        writer.endObject();  // 序列化完毕后调用
        writer.close();*/

        testWrite();


        //new File("\\temp\\1").mkdirs();

        List<VO> s = new ArrayList<>();
        for (long i = 0; i < 10000L; i++) {
            s.add(VO.getInstance());
        }
        List<List<VO>> ss = new ArrayList<>();
        for (long i = 0; i < 500L; i++) {
            ss.add(new ArrayList<>(s));
        }
/*        List<List<List<VO>>> sss = new ArrayList<>();
        for (long i = 0; i < 10L; i++) {
            sss.add(new ArrayList<>(ss));
        }*/
        FileWriter jsonFileWrite = new FileWriter("D:\\temp.json");
        JSONWriter writer = new JSONWriter(jsonFileWrite);
        FastJsonUtils.writeArray(writer, ss);
        writer.close();
//        System.out.println(JSON.toJSONString(ss));
    }

    /**
     * String字符串最大长度为，
     *  编译阶段：常量池，65534字节
     *  运行阶段：char[] 数组长度为int，最大值为2147483647，char为16位，最大字符串大约4GB内存
     * @throws IOException
     */
    private static void testWrite() throws IOException {
        FileWriter jsonFileWrite = new FileWriter("D:\\project\\idea\\cfg\\utils/huge.json");
        JSONWriter writer = new JSONWriter(jsonFileWrite); // 把json对象写入到哪里
        writer.startObject();
        FastJsonUtils.writeObjectAllKeyAndValue(writer, VO.getInstance());
        writer.writeKey("arr");
        FastJsonUtils.writeArray(writer, VO.getInstance(), VO.getInstance(), VO.getInstance());
        writer.endObject();
        writer.close();
        JSON.toJSONString(VO.getInstance());
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class VO {
    private Integer id;
    private Integer id1;

    static VO getInstance() {
        return new VO(new Random().nextInt(100), null);
    }
}
