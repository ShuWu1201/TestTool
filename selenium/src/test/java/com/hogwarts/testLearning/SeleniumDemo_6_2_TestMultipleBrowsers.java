package com.hogwarts.testLearning;

import org.junit.jupiter.api.Test;

/**
 * @ClassName SeleniumDemo_6_2_TestMultipleBrowsers
 * @Description 测试多浏览器
 * @Author sfmewl
 * @Date 2021/6/20 15:06
 **/
public class SeleniumDemo_6_2_TestMultipleBrowsers extends SeleniumDemo_6_1_MultipleBrowsersBaseClass{

    /**
     * 1、使用命令行mvn命令执行，执行时指定browser="chrome"
     * $ browser="chrome" mvn -Dtest=SeleniumDemo_6_2_TestMultipleBrowsers test
     *
     * 2、也可以在 Run/Debug Configurations 中指定 Environment variables 为 browser=chorme
     */
    @Test
    public void testMultipleBrowsers(){
        webDriver.get("https://www.baidu.com/");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}