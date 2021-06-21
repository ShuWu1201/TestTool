package com.hogwarts.testLearning;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

/**
 * @ClassName SeleniumDemo_7_TestJs
 * @Description 测试selenium执行js脚本
 * @Author sfmewl
 * @Date 2021/6/20 17:49
 **/
public class SeleniumDemo_7_TestJs extends SeleniumDemo_6_1_MultipleBrowsersBaseClass{

    @Test
    public void testJs() throws InterruptedException {
        // 打开12306完整
        webDriver.get("https://www.12306.cn/index/");

        // 实例化一个JavascriptExecutor，并将webDriver强制转换为一个JavascriptExecutor实例
        JavascriptExecutor jsDriver = (JavascriptExecutor) webDriver;

        Thread.sleep(5000);

        // 执行js脚本
        jsDriver.executeScript("document.getElementById(\"train_date\").value='2022-12-21'");

        Thread.sleep(5000);

        //通过js脚本获取属性值
        System.out.println(jsDriver.executeScript("return document.getElementById(\"train_date\").value"));
    }

}