package com.junit5.demo;

import com.util.Calculator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
/**
 * 基础脚本：分别执行加减乘除、计算器操作并打印结果
 */


/**
 * @ClassName Junit5Demo_1_1_Base
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/14 07:50
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Junit5Demo_1_1_Base {

    @Test
    @Order(1)
    public void addTest(){
        int result = Calculator.add(4, 2);
        System.out.println("addTest: " + result);
    }

    /**
     * 问题：Junit5 不是按照方法定义顺序来执行的。但是根据一个固定的顺序来执行的（？？？什么逻辑），但是可以人为控制顺序。
     * 控制执行顺序使用@TestMethodOrder\@Order注解人工干预
     * @throws InterruptedException
     */
    @Test
    @Order(6)
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        System.out.println("countTest: " + result);
    }

    @Test
    @Order(2)
    public void subTractTest(){
        int result = Calculator.subtract(4, 2);
        System.out.println("subTractTest: " + result);
    }

    @Test
    @Order(3)
    public void multiplyTest(){
        int result = Calculator.multiply(4, 2);
        System.out.println("multiplyTest: " + result);
    }

    @Test
    @Order(4)
    public void divideTest(){
        int result = Calculator.divide(4, 2);
        System.out.println("divideTest: " + result);
    }

    @Test
    @Order(6)
    public void celar(){
        Calculator.clear();
    }

}