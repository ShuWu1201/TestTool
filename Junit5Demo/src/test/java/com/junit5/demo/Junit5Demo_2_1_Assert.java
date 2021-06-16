package com.junit5.demo;
import com.util.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * 基础脚本：分别执行加减乘除、计算器操作并打印结果
 * 1、进行优化：添加自动断言机制，解决人工检查结果的问题
 */


/**
 * @ClassName Junit5Demo_2_1_Assert
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/14 07:52
 **/
public class Junit5Demo_2_1_Assert {

    @Test
    public void addTest(){
        int result = Calculator.add(4, 2);
        System.out.println("addTest: " + result);
        assertEquals(6, result);
    }

    @Test
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        System.out.println("countTest: " + result);
        assertEquals(12, result);
    }

    @Test
    public void subTractTest(){
        int result = Calculator.subtract(4, 2);
        System.out.println("subTractTest: " + result);
        assertEquals(2, result);
    }

    @Test
    public void multiplyTest(){
        int result = Calculator.multiply(4, 2);
        System.out.println("multiplyTest: " + result);
        assertEquals(8, result);
    }

    @Test
    public void divideTest(){
        int result = Calculator.divide(4, 2);
        System.out.println("divideTest: " + result);
        assertEquals(2, result);
    }

}