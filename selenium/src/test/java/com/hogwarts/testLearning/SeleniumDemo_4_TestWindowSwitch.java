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
 * @Description 浏览器多窗口切换测试
 * @Author sfmewl
 * @Date 2021/6/18 07:33
 **/
public class SeleniumDemo_4_TestWindowSwitch {

    public static WebDriver webDriver;

    @BeforeAll
    public static void inintData(){
        System.setProperty("webdriver.chrome.driver", "/Users/sfmewl/Documents/process/selenium/chromedriver");

        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void testWidowSwitch() throws InterruptedException {
        /**
         * 打开百度首页
         */
        webDriver.get("https://www.baidu.com");

        /**
         * 获取百度首页的窗口句柄，并打印
         */
        String baiduHome = webDriver.getWindowHandle();
        System.out.println("baiduHome:" + baiduHome);

        // 元素找不到需要把窗口最大化
        webDriver.manage().window().maximize();

        /**
         * 此时点击"登录"后并未产生新窗口，而是一个页面弹框
         */
        webDriver.findElement(By.xpath("//*[@id=\"s-top-loginbtn\"]")).click();

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        /**
         * 此时点击"立即登录"展示上新打开一个新的窗口，但是webDriver还停留在百度首页的窗口句柄，这是一个坑
         */
        webDriver.findElement(By.xpath("//*[@id=\"passport-login-pop-dialog\"]/div/div/div/div[3]/a")).click();

        for (String windowName : webDriver.getWindowHandles()) {
            if (!windowName.equals(baiduHome)){
                // 切换到非baiduHome窗口，也就是注册窗口
                webDriver.switchTo().window(windowName);

                webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

                // 注册时输入用户名和手机号
                webDriver.findElement(By.xpath("//*[@id=\"TANGRAM__PSP_4__userName\"]")).sendKeys("username");
                webDriver.findElement(By.xpath("//*[@id=\"TANGRAM__PSP_4__phone\"]")).sendKeys("12345678901");

                // 切换到baiduHome
                webDriver.switchTo().window(baiduHome);

                webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

                // 登录时输入用户名和密码
                webDriver.findElement(By.xpath("//*[@id=\"TANGRAM__PSP_11__footerULoginBtn\"]")).click();
                webDriver.findElement(By.id("TANGRAM__PSP_11__userName")).sendKeys("15013142847");
                webDriver.findElement(By.id("TANGRAM__PSP_11__password")).sendKeys("password");

                // 直接等待，调试为了观看效果使用
                Thread.sleep(3000);
            }
        }
    }


    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }


}