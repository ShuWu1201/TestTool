package com.hogwarts.testTraining;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestDebug
 * @Description 编写脚本过程中的测试类
 * @Author sfmewl
 * @Date 2021/6/20 22:52
 **/
public class TestDebug {

    public static WebDriver webDriver;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/sfmewl/Documents/process/selenium/chromedriver");
        webDriver = new ChromeDriver();
    }

    @Test
    public void testDebug(){
        webDriver.get("https://www.baidu.com/");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        // 显式等待，可以具体看下源代码
        WebDriverWait webDriverWait = (WebDriverWait) new WebDriverWait(webDriver, 30, 1).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"s-top-left\"]/a[7]")));
    }


    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }
}