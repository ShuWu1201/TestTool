package com.hogwarts.testTraining.pageobject.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @ClassName BasePage
 * @Description 所有页面公共的、基础的操作封装到基础类中
 * @Author sfmewl
 * @Date 2021/6/24 09:02
 **/
public class BasePage {

    /**
     * 由于webDriver是在@BeforeAll注解修饰的静态方法中初始化的
     * 所有需要定义一个静态变量（类级别的变量）
     *
     * 后面初始化 webDriver 可以使用工厂模式，根据传入的参数来判断初始化chrome、Firefox、还是safari
     */
    WebDriver webDriver;

    public BasePage() {
        this.webDriver = new ChromeDriver();
    }

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // 简单封装
    void click(By by){
        webDriver.findElement(by).click();
    }

    // 简单封装
    void sendKeys(By by, String content){
        webDriver.findElement(by).sendKeys(content);
    }

}