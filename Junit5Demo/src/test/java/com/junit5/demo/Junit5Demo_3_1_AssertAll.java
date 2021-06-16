package com.junit5.demo;
import com.util.Calculator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * 基础脚本：分别执行加减乘除、计算器操作并打印结果
 * 1、进行优化：添加自动断言机制，解决人工检查结果的问题
 * 2、使用 Junit5 提供的 assertAll 的断言方法，增加脚本容错性
 */


/**
 * @ClassName Junit5Demo_2_1_Assert
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/14 07:52
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Junit5Demo_3_1_AssertAll {

    @Test
    public void addTestAssertFail(){
        int result01 = Calculator.add(4, 2);
        assertEquals(6, result01);
        System.out.println("result01: " + result01);

        /**
         * 该断言失败后会导致直接跳出该测试方法无法执行后面的断言
         */
        int result02 = Calculator.add(5, 2);
        assertEquals(6, result02);
        System.out.println("result02: " + result02);

        int result03 = Calculator.add(6, 2);
        assertEquals(8, result03);
        System.out.println("result03: " + result03);
    }

    @Test
    public void addTestAll(){
        int result01 = Calculator.add(4, 2);
        System.out.println("result01: " + result01);

        int result02 = Calculator.add(5, 2);
        System.out.println("result02: " + result02);

        int result03 = Calculator.add(6, 2);
        System.out.println("result03: " + result03);

        /**
         * 使用 Junit5 提供的 java8 lambda 的断言方法，当一个断言失败，剩下的断言依然会执行
         */
        assertAll("",
                ()->assertEquals(6, result01),
                ()->assertEquals(6, result02),
                ()->assertEquals(8, result03)
        );
    }

    @Test
    @Order(2)
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

    @BeforeEach
    public void celar(){
        Calculator.clear();
    }

}