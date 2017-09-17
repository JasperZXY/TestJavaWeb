package com.jasper.demo.spring.mybatis.generator;

/**
 * Created by zxy on 2017/9/17.
 */
public class MyBatisGenerator {
    public static void main(String[] args) {
        run("generatorConfig-test.xml");
    }

    private static void run(String fileName) {
        String filePath = System.getProperty("user.dir") + "/tjw-spring-mybatis/src/test/resources/generator/" + fileName;
        org.mybatis.generator.api.ShellRunner.main(new String[]{"-configfile", filePath, "-overwrite"});
    }
}
