package com.testngdemo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @ClassName TestNGDemo_6_1_DataProvider
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/16 07:28
 **/
public class TestNGDemo_6_1_DataProvider {

    @DataProvider(name = "testData")
    public static Object[][] words() throws IOException {
        return FakerUtils.getTestData("/src/main/resources/data/divideparam.csv");
    }

    @Test(threadPoolSize = 1, invocationCount = 1, dataProvider = "testData")
    public void divTest(String x, String y, String expectedResult){
        int calculatorResult = Calculator.divide(Integer.valueOf(x), Integer.valueOf(y));
        Assert.assertEquals(Integer.valueOf(calculatorResult), Integer.valueOf(expectedResult));
        System.out.println(calculatorResult);
    }
}