package com.hogwarts.testLearning;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SeleniumDemo_1_TestBaidu
 * @Description 测试打开百度输入"selenium"再点击百度一下
 * @Author sfmewl
 * @Date 2021/6/18 07:33
 **/
public class SeleniumDemo_1_TestBaidu {

    /**
     * 定义一个webDriver
     */
    public static WebDriver webDriver;

    /**
     * 第一步：环境配置+实例化webDriver
     */
    @BeforeAll
    public static void inintData(){
//        System.setProperty("webdriver.chrome.driver", "/Users/sfmewl/Documents/process/selenium/chromedriver");
//        webDriver = new ChromeDriver();

        System.setProperty("webdriver.gecko.driver", "/Users/sfmewl/Documents/process/selenium/geckodriver");
        webDriver = new FirefoxDriver();

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    /**
     * 第二步：用例编写,打开百度输入"selenium"再点击百度一下
     * @throws InterruptedException
     */
    @Test
    public void testBaidu() throws InterruptedException {
        // 打开百度首页
        webDriver.get("https://www.baidu.com/");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // 输入框输入selenium
        webDriver.findElement(By.xpath("//*[@id=\"kw\"]")).sendKeys("selenium");

        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        // 点击"百度一下"
        webDriver.findElement(By.id("su")).click();
        Thread.sleep(3000);
    }

    /**
     * 第三步：退出资源（关闭浏览器）
     */
    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }


}