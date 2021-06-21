package com.hogwarts.testLearning;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SeleniumDemo_2_TestThreeWaitForDemo
 * @Description Selenium的三种等待方式
 * @Author sfmewl
 * @Date 2021/6/18 07:33
 **/
public class SeleniumDemo_2_TestThreeWaitForDemo {


    public static WebDriver webDriver;

    /**
     * 定义一个显示等待的实例
     */
    public static WebDriverWait webDriverWait;


    @BeforeAll
    public static void inintData(){
        System.setProperty("webdriver.chrome.driver", "/Users/sfmewl/Documents/process/selenium/chromedriver");

        webDriver = new ChromeDriver();

        try {
            /**
             * 1、直接等待，线程sleep
             */
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 2、隐式等待
         */
        //webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        /**
         * 3、实例化显式等待实例时需要给构造函数传入一个 WebDriver 类实例和一个 long 类型的超时时间
         */
        webDriverWait = new WebDriverWait(webDriver, 5);

    }


    @Test
    public void testBaidu() throws InterruptedException {
        // 打开百度链接
        webDriver.get("https://www.baidu.com/");

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // 打开百度学术
        //webDriver.findElement(By.xpath("//*[@id=\"s-top-left\"]/a[7]")).click();

        /**
         * 3、1 显式等待1:WebDriverWait 配合 until( ) 方法，根据条件判断进行等待.通过匿名内部类来判断元素是否定位到
         * 一般如果在未定位到元素时有其他操作时可以使用这种匿名内部类的写法
         */
        WebElement webElement = webDriverWait.until(new ExpectedCondition<WebElement>(){
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("//*[@id=\"s-top-left\"]/a[7]"));
            }
        });
        webElement.click();
        Thread.sleep(3000);

        /**
         * 3、2 显式等待2:WebDriverWait 配合 until( ) 方法，根据条件判断进行等待.
         * 一般直接通过ExpectedConditions类的方法来判断元素是否定位到
         */
//        WebElement webElement01 = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"s-top-left\"]/a[7]")));
//        webElement01.click();
//        Thread.sleep(3000);
    }

    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }


}