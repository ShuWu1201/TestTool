package com.testngdemo;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * @ClassName TestNGDemo_4_1_Parallel
 * @Description 通过加法、减法模拟混合多线程
 * @Author sfmewl
 * @Date 2021/6/15 08:36
 **/
public class TestNGDemo_4_2_MixParallel {

    @Test(threadPoolSize = 2, invocationCount = 5)
    public void addTest(){
        SoftAssert softAssert = new SoftAssert();
        int result = Calculator.add(4, 2);
        System.out.println("完成加法运算，结果为：" + result);
        Assert.assertEquals(result, 6);
    }

    @Test(threadPoolSize = 2, invocationCount = 5)
    public void subtractTest(){
        SoftAssert softAssert = new SoftAssert();
        int result = Calculator.subtract(4, 2);
        System.out.println("完成减法运算，结果为：" + result);
        Assert.assertEquals(result, 2);
    }
}