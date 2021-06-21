package com.hogwarts.testLearning;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SeleniumDemo_4_TestWindowSwitch
 * @Description 多frame切换测试demo
 * @Author sfmewl
 * @Date 2021/6/18 07:33
 **/
public class SeleniumDemo_5_TestIframeSwitch {

    public static WebDriver webDriver;

    @BeforeAll
    public static void inintData(){
        System.setProperty("webdriver.chrome.driver", "/Users/sfmewl/Documents/process/selenium/chromedriver");

        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void testIframeSwitch(){
        webDriver.get("https://www.runoob.com/try/try.php?filename=jqueryui-api-droppable");

        /**
         * webDriver先切换到子frame，并打印子frame中元素的文本
         */
        webDriver.switchTo().frame("iframeResult");
        System.out.println(webDriver.findElement(By.id("draggable")).getText());

        /**
         * webDriver先切换到父frame，并打印父frame中元素的文本
         * 编写脚本的时候要考虑parentFrame是否唯一
         */
        webDriver.switchTo().parentFrame();
        System.out.println(webDriver.findElement(By.id("submitBTN")).getText());
    }


    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }


}