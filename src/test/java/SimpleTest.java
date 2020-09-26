import com.quantumfluctuation.util.ClassUtil;
import com.quantumfluctuation.util.classscanner.DefaultClassScanner;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class SimpleTest {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = ClassUtil.getClassLoader().getResourceAsStream("logback.xml");
                InputStreamReader isr = new InputStreamReader(resourceAsStream);//读取

        char []cha = new char[1024];
        int len = isr.read(cha);
        System.out.println(new String(cha,0,len));
        isr.close();

        List<Class<?>> classList = new DefaultClassScanner().getClassList("org.apache.commons.lang3");
        for (Class<?> aClass : classList) {
            System.out.println(aClass.getName());
        }
    }
}
