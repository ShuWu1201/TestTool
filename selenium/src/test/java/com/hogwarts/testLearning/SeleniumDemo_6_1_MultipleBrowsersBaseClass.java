package com.hogwarts.testLearning;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @ClassName SeleniumDemo_6_1_MultipleBrowsersBaseClass
 * @Description 多浏览器测试
 * @Author sfmewl
 * @Date 2021/6/20 14:11
 **/
public class SeleniumDemo_6_1_MultipleBrowsersBaseClass {

    public static WebDriver webDriver;

    @BeforeAll
    public static void initData(){

        String browserName = System.getenv("browser");

        System.setProperty("webdriver.chrome.driver", "/Users/sfmewl/Documents/process/selenium/chromedriver");
        webDriver = new ChromeDriver();

        /**
         * 多浏览器测试使用
         */
//        if ("chrome".equals(browserName)){
//            System.setProperty("webdriver.chrome.driver", "/Users/sfmewl/Documents/process/selenium/chromedriver");
//            webDriver = new ChromeDriver();
//        }else if ("firefox".equals(browserName)){
//            System.setProperty("webdriver.gecko.driver", "/Users/sfmewl/Documents/process/selenium/geckodriver");
//            webDriver = new FirefoxDriver();
//        }else if ("safari".equals(browserName)){
//            webDriver = new SafariDriver();
//        }
    }


    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }
}