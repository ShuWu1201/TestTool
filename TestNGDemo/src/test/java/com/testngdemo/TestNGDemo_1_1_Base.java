package com.testngdemo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * @ClassName TestNGDemo_1_1_Base
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/15 07:39
 **/
public class TestNGDemo_1_1_Base {
    @Test(priority = 1)
    public void addTest(){
        int result = Calculator.add(4, 2);
        System.out.println("addTest: " + result);
        Assert.assertEquals(result, 6);
    }

    /**
     * 测试 TestNG 的软断言SoftAssert
     */
    @Test
    public void addTestSoftAssert(){
        SoftAssert assertion = new SoftAssert();

        int result01 = Calculator.add(4, 2);
        System.out.println("result01: " + result01);
        assertion.assertEquals(result01, 6);

        int result02 = Calculator.add(4, 2);
        System.out.println("result02: " + result02);
        assertion.assertEquals(result02, 6);

        int result03 = Calculator.add(4, 2);
        System.out.println("result03: " + result03);
        assertion.assertEquals(result03, 6);

        /**
         * 将前面的软断言全部输出
         */
        assertion.assertAll();
    }

    /**
     * 问题：Junit5 不是按照方法定义顺序来执行的。但是根据一个固定的顺序来执行的（？？？什么逻辑），但是可以人为控制顺序。
     * 控制执行顺序使用@TestMethodOrder\@Order注解人工干预
     * @throws InterruptedException
     */
    @Test(priority = 2)
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        System.out.println("countTest: " + result);
        Assert.assertEquals(result, 4);
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
        Assert.assertEquals(result, 8);
    }

    @Test
    public void divideTest(){
        int result = Calculator.divide(4, 2);
        System.out.println("divideTest: " + result);
        Assert.assertEquals(result, 2);
    }

    @BeforeMethod
    public void celar(){
        Calculator.clear();
    }
}