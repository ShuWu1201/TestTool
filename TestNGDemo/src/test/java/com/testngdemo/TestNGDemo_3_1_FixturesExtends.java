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
public class TestNGDemo_3_1_FixturesExtends extends TestNGDemo_2_1_Fixtures{
    @BeforeSuite
    public static void beforeSuiteChildClass(){
        System.out.println("BeforeSuite ChildClass 执行！");
    }
    @AfterSuite
    public static void afterSuiteChildClass(){
        System.out.println("AfterSuite ChildClass 执行！");
    }

    @BeforeTest
    public static void beforeTestChildClass(){
        System.out.println("BeforeTest ChildClass 执行！");
    }
    @AfterTest
    public static void afterTestChildClass(){
        System.out.println("AfterTest ChildClass 执行！");
    }

    @BeforeClass
    public void beforeClassChildClass(){
        System.out.println("BeforeClass ChildClass 执行！");
    }
    @AfterClass
    public void afterClassChildClass(){
        System.out.println("AfterClass ChildClass 执行！");
    }

    @BeforeMethod
    public void beforeMethodChildClass(){
        System.out.println("BeforeMethod ChildClass 执行！");
    }
    @AfterMethod
    public void afterMethodChildClass(){
        System.out.println("AfterMethod ChildClass 执行！");
    }


    @Test
    public void testMethod01ChildClass(){
        System.out.println("testMethod01 ChildClass 执行！");
    }
    @Test
    public void testMethod02ChildClass(){
        System.out.println("testMethod02 ChildClass 执行！");
    }

}