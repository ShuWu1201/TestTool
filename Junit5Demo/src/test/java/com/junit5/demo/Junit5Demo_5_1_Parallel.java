package com.junit5.demo;

import com.util.Calculator;
import org.junit.jupiter.api.RepeatedTest;

/**
 * @ClassName Junit5Demo_5_1_Parallel
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/14 15:28
 **/
public class Junit5Demo_5_1_Parallel {
    /**
     * 累加功能测试
     * @throws InterruptedException
     */
    @RepeatedTest(10)
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        System.out.println(result);
    }

    /**
     * 累加功能测试（方法加锁）
     * @throws InterruptedException
     */
    @RepeatedTest(10)
    public void countSynTest() throws InterruptedException {
        int result = Calculator.countSyn(1);
        System.out.println(result);
    }
}