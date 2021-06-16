package com.junit5.demo;

import org.junit.jupiter.api.*;

/**
 * @ClassName Fixtures
 * @Description 执行时机
 * @Author sfmewl
 * @Date 2021/6/14 09:42
 **/
public class Junit5Demo_4_2_FixturesExtend extends Junit5Demo_4_1_Fixtures{
    @BeforeAll
    public static void beforeAllChildClass(){
        System.out.println("ChildClass beforeAll 执行！");
    }
    @AfterAll
    public static void afterAllChildClass(){
        System.out.println("Child afterAll 执行！");
    }
    @BeforeEach
    public void beforeEachChildClass(){
        System.out.println("ChildClass beforeEach 执行！");
    }
    @AfterEach
    public void afterEachChildClass(){
        System.out.println("ChildClass afterEach 执行！");
    }
    @Test
    public void testMethod01ChildClass(){
        System.out.println("ChildClass testMethod01 执行！");
    }
//    @Test
//    public void testMethod02ChildClass(){
//        System.out.println("ChildClass testMethod02 执行！");
//    }
//    @Test
//    public void testMethod03ChildClass(){
//        System.out.println("ChildClass testMethod03 执行！");
//    }
}