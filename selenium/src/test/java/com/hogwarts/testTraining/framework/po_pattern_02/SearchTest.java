package com.hogwarts.testTraining.framework.po_pattern_02;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;

/**
 * @ClassName SearchTest
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/28 08:35
 **/
public class SearchTest {

    /**
     * 简单po封装
     */
    @Ignore
    void search(){
        MainPage mainPage = new MainPage();

        try {
            mainPage.search().search("apple");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mainPage.webDriver.quit();
        }
    }


    /**
     * 数据驱动，数据来源：src/test/resources/framework/framework_search_po_test.yaml
     */
    @Test
    void searchForDataDriver(){
        MainPage mainPage = new MainPage();

        try {
            SearchPage searchPage = mainPage.search();
            searchPage.search("apple");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mainPage.webDriver.quit();
        }
    }

}