package com.hogwarts.testLearning;

import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * @ClassName SeleniumDemo_0_TestDebug
 * @Description 测试类
 * @Author sfmewl
 * @Date 2021/6/20 09:22
 **/
public class SeleniumDemo_0_TestDebug {

    public static WebDriver webDriver;
    public static Actions actions;

    @BeforeAll
    public static void inintData(){
        System.setProperty("webdriver.chrome.driver", "/Users/sfmewl/Documents/process/selenium/chromedriver");

        webDriver = new ChromeDriver();
        actions = new Actions(webDriver);

        webDriver.findElement(By.xpath("")).sendKeys("路径");

        webDriver.switchTo().alert();


    }


}