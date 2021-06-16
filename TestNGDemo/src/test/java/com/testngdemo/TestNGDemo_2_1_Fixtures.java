package com.testngdemo;

/**
 * 执行顺序：@beforeAll
 */

import org.testng.annotations.*;

/**
 * @ClassName Fixtures
 * @Description 执行时机
 * @Author sfmewl
 * @Date 2021/6/14 09:42
 **/
public class TestNGDemo_2_1_Fixtures {
    @BeforeSuite
    public static void beforeSuite(){
        System.out.println("BeforeSuite 执行！");
    }
    @AfterSuite
    public static void afterSuite(){
        System.out.println("AfterSuite 执行！");
    }

    @BeforeTest
    public static void beforeTest(){
        System.out.println("BeforeTest 执行！");
    }
    @AfterTest
    public static void afterTest(){
        System.out.println("AfterTest 执行！");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("BeforeClass 执行！");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("AfterClass 执行！");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeMethod 执行！");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod 执行！");
    }


    @Test
    public void testMethod01(){
        System.out.println("testMethod01 执行！");
    }
    @Test
    public void testMethod02(){
        System.out.println("testMethod02 执行！");
    }

}