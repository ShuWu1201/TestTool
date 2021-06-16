package com.testngdemo;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * @ClassName TestNGDemo_5_1_Parameters
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/16 07:09
 **/
public class TestNGDemo_5_1_Parameters {

    @Test(threadPoolSize = 1, invocationCount = 1)
    @Parameters({"x", "y", "expectedResult"})
    public void addTest(int x, int y, int expectedResult){
        SoftAssert softAssert = new SoftAssert();

        int CalculatorResult = Calculator.add(x, y);
        System.out.println("完成加法运算，结果为：" + CalculatorResult);
        Assert.assertEquals(CalculatorResult, expectedResult);
    }
}