package test.jasper;

import org.junit.Test;
import org.springframework.util.ClassUtils;

public class TestSpringUtils {
    @Test
    public void classUtils() throws Exception {
        ClassLoader classLoader = null;
        System.out.println(ClassUtils.forName("int", classLoader));
        System.out.println(ClassUtils.forName("java.lang.String[]", classLoader));
        System.out.println(ClassUtils.forName("[Ljava.lang.String;", classLoader));
        System.out.println(ClassUtils.forName("java.util.Map$Entry", classLoader));
    }
}
