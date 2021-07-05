package com.hogwarts.testTraining.framework.po_pattern_02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @ClassName SearchPage
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/27 21:00
 **/
public class SearchPage extends BasePage{

//    public SearchPage() {
//    }

    public SearchPage(WebDriver webDriver) {

        //super();
        this.webDriver = webDriver;
        System.out.println("SearchPage 初始化 driver");
    }

    public void search(String keyword){
        sendKeys(By.id("kw"), keyword);
        click(By.id("su"));
    }
}