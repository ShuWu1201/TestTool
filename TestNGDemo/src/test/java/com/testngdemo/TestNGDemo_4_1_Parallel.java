package com.testngdemo;

import org.testng.annotations.Test;

/**
 * @ClassName TestNGDemo_4_1_Parallel
 * @Description 模拟多线程
 * @Author sfmewl
 * @Date 2021/6/15 08:36
 **/
public class TestNGDemo_4_1_Parallel {

    /**
     * threadPoolSize 表示线程数
     * invocationCount 表示重复执行次数
     * @throws InterruptedException
     */
    @Test(threadPoolSize = 3, invocationCount = 10, timeOut = 8000)
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        System.out.println("计算加法结果：" + result);
    }

    /**
     * 方法加synchronized关键字后的countSyn方法
     * @throws InterruptedException
     */
    @Test(threadPoolSize = 3, invocationCount = 10, timeOut = 80000)
    public void countSynTest() throws InterruptedException {
        int result = Calculator.countSyn(1);
        System.out.println("计算加法结果：" + result);
    }

}