package com.hogwarts.testEnv;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName com.hogwarts.helloworld.StartSelenium
 * @Description 测试环境变量是否配置正常，脚本是否可正常启动浏览器
 * @Author sfmewl
 * @Date 2021/6/17 09:18
 **/
public class StartSelenium {

    @Test
    public void startSelenium() throws InterruptedException {
        /**
         * 设置chromedriver的环境变量（理论上环境变量配置后无需脚本配置？？系统已配置但是不知为何没有读取到）
         */
        System.setProperty("webdriver.chrome.driver", "/Users/sfmewl/Documents/process/selenium/chromedriver");

        /**
         * 创建webDriver的实例
         */
        WebDriver webDriver = new ChromeDriver();

        /**
         * webDriver驱动chormedriver打开百度链接
         */
        webDriver.get("https://www.baidu.com/");

        /**
         * 避免因为渲染而导致的元素找不到等原因导致运行报错
         * 1、显式等待：线程sleep 3秒：Thread.sleep(3000);
         * 2、隐式等待：即规定时间去操作元素，若没有出现（或者元素状态不对）则抛出异常。(优点是可以节约时间)
         *    webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
         */
        //Thread.sleep(3000);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        /**
         * 根据xpath路径找到【百度学术】元素并click一下
         */
        webDriver.findElement(By.xpath("//*[@id=\"s-top-left\"]/a[7]")).click();

        /**
         * 打开【百度学术】后线程sleep 5s 观察效果
         */
        Thread.sleep(5000);

        /**
         * 退出浏览器资源
         */
        webDriver.quit();
    }

}