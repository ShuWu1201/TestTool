package com.testngdemo;


/**
 * 基础脚本：分别执行加减乘除、计算器操作并打印结果
 * 1、进行优化：添加自动断言机制，解决人工检查结果的问题
 * 2、使用 Junit5 提供的 assertAll 的断言方法，增加脚本容错性
 */


import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * @ClassName Junit5Demo_2_1_Assert
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/14 07:52
 **/
@Epic("Epic 计算器项目")
@Feature("Feature 冒烟测试用例")
public class TestNGDemo_7_1_Allure {

    @Test
    @Description("Description")
    @Story("Story 加法测试")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("www.bugsystemLink.com bug系统链接")
    @Link(name = "Link 社区贴", type = "mylink", url = "www.link.com")
    public void addTestAssertFail(){
        int result01 = Calculator.add(4, 2);
        Assert.assertEquals(6, result01);
        System.out.println("result01: " + result01);

        /**
         * 该断言失败后会导致直接跳出该测试方法无法执行后面的断言
         */
        int result02 = Calculator.add(5, 2);
        Assert.assertEquals(7, result02);
        System.out.println("result02: " + result02);

        int result03 = Calculator.add(6, 2);
        Assert.assertEquals(8, result03);
        System.out.println("result03: " + result03);
    }

    @Test
    public void addTestAll(){
        SoftAssert softAssert = new SoftAssert();

        int result01 = Calculator.add(4, 2);
        System.out.println("result01: " + result01);
        softAssert.assertEquals(result01, 6);

        int result02 = Calculator.add(5, 2);
        System.out.println("result02: " + result02);
        softAssert.assertEquals(result02, 7);

        int result03 = Calculator.add(6, 2);
        System.out.println("result03: " + result03);
        softAssert.assertEquals(result03, 8);

        softAssert.assertAll();

    }

    @Test
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        System.out.println("countTest: " + result);
        Assert.assertEquals(4, result);
    }

    @Test
    public void subTractTest(){
        int result = Calculator.subtract(4, 2);
        System.out.println("subTractTest: " + result);
        Assert.assertEquals(result, 2);
    }

    @Test
    public void multiplyTest(){
        int result = Calculator.multiply(4, 2);
        System.out.println("multiplyTest: " + result);
        Assert.assertEquals(8, result);
    }

    @Test
    public void divideTest(){
        int result = Calculator.divide(4, 2);
        System.out.println("divideTest: " + result);
        Assert.assertEquals(2, result);
    }

    @BeforeMethod
    public void celar(){
        Calculator.clear();
    }

}