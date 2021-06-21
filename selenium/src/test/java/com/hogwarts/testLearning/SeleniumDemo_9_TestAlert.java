package com.hogwarts.testLearning;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

/**
 * @ClassName SeleniumDemo_9_TestAlert
 * @Description 测试弹框
 * @Author sfmewl
 * @Date 2021/6/20 20:56
 **/
public class SeleniumDemo_9_TestAlert extends SeleniumDemo_6_1_MultipleBrowsersBaseClass{

    @Test
    public void testAlert() throws InterruptedException {
        webDriver.get("https://www.runoob.com/try/try.php?filename=jqueryui-api-droppable");

        // 切换到iframe
        webDriver.switchTo().frame("iframeResult");

        Actions actions = new Actions(webDriver);

        // 拖拽到目标框中
        actions.dragAndDrop(webDriver.findElement(By.id("draggable")), webDriver.findElement(By.id("droppable"))).perform();

        Thread.sleep(5000);

        // 切换到alter然后确认
        webDriver.switchTo().alert().accept();

        Thread.sleep(3000);

        webDriver.switchTo().parentFrame();

        System.out.println(webDriver.findElement(By.id("submitBTN")).getText());



    }
}