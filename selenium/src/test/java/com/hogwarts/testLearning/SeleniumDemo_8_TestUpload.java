package com.hogwarts.testLearning;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SeleniumDemo_8_TestUpload
 * @Description 测试文件上传
 * @Author sfmewl
 * @Date 2021/6/20 18:07
 **/
public class SeleniumDemo_8_TestUpload extends SeleniumDemo_6_1_MultipleBrowsersBaseClass{

    @Test
    public void upload() throws InterruptedException {

        webDriver.get("https://www.baidu.com/");

        //定位图片上传按钮，直接复制过来的xpath无法定位元素
        //webDriver.findElement(By.xpath("//*[@id=\"form\"]/span[1]/span[2]")).click();

        // 定位图片上传按钮（如果copy的xpath无法找到元素，需要自己手动在控制台定位好后再将脚本贴过来）
        webDriver.findElement(By.xpath("//span[@class='soutu-btn']")).click();

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        /**
         * sendKeys("文件路径")中的文件路径是绝对路径
         */
        webDriver.findElement(By.xpath("//input[@class='upload-pic']")).sendKeys("/Users/sfmewl/IdeaProjects/TestTool/selenium/src/main/resources/bugScreenshot.png");

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // Thread.sleep(8000);

        webDriver.findElement(By.xpath("//div[@class='graph-guide-button']")).click();

        Thread.sleep(3000);

    }

}