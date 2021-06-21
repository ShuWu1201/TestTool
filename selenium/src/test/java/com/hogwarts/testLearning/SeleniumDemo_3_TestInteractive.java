package com.hogwarts.testLearning;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;

/**
 * @ClassName SeleniumDemo_3_TestInteractive
 * @Description Selenium Actions类demo
 * @Author sfmewl
 * @Date 2021/6/18 07:33
 **/
public class SeleniumDemo_3_TestInteractive {

    public static WebDriver webDriver;
    public static Actions actions;

    @BeforeAll
    public static void inintData(){
        System.setProperty("webdriver.chrome.driver", "/Users/sfmewl/Documents/process/selenium/chromedriver");

        webDriver = new ChromeDriver();
        actions = new Actions(webDriver);
    }

    /**
     * 鼠标click测试
     * @throws InterruptedException
     */
    @Ignore
    public void testClick() throws InterruptedException {
        webDriver.get("https://www.baidu.com/");

        actions.click(webDriver.findElement(By.xpath("//*[@id=\"s-top-left\"]/a[7]"))).perform();

        Thread.sleep(3000);

        //actions.click(webDriver.findElement(By.xpath("//*[@class=\"clickActive index_bold_font\"]"))).perform();

        Thread.sleep(3000);

    }

    /**
     * 鼠标move测试
     */
    @Ignore
    public void testMove(){
        try {
            webDriver.get("https://www.baidu.com/");

            actions.moveToElement(webDriver.findElement(By.id("s-usersetting-top"))).perform();
            Thread.sleep(30000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 滑动测试
     */
    @Test
    public void testScorll(){
        try {
            /**
             * 打开百度链接，输入selenium
             */
            webDriver.get("https://www.baidu.com/");
            webDriver.findElement(By.id("kw")).sendKeys("selenium");
            Thread.sleep(3000);

            /**
             * 点击"百度一下"
             */
            TouchActions touchActions = new TouchActions(webDriver);
            actions.click(webDriver.findElement(By.id("su"))).perform();
            Thread.sleep(3000);

            /**
             * 滑动窗口到底部
             */
            JavascriptExecutor jsDriver = (JavascriptExecutor)webDriver;
            jsDriver.executeScript("window.scrollBy(0, document.body.scrollHeight)");
            Thread.sleep(3000);

            /**
             * 点击"下一页"
             */
            webDriver.findElement(By.xpath("//*[@id=\"page\"]/div/a[10]")).click();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }


}