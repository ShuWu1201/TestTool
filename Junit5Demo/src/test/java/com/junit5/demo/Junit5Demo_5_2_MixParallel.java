package com.junit5.demo;

import com.util.Calculator;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * 由于 Calculator 类中使用了同一个静态全局变量result，当并发执行加和减操作时会因为result值出现出现问题，
 * 单独执行加或者减不会出现问题；
 * 此时只需要将 Calculator 类的全局变量result定义为方法内变量即可解决并发带来的问题
 */


/**
 * @ClassName Junit5Demo_5_1_Parallel
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/14 15:28
 **/
public class Junit5Demo_5_2_MixParallel {

    @RepeatedTest(10)
    public void addTest(){
        int result = Calculator.add(4, 2);
        System.out.println(result);
        assertEquals(6, result);
    }

    @RepeatedTest(10)
    public void subTractTest(){
        int result = Calculator.subtract(4, 2);
        System.out.println(result);
        assertEquals(2, result);
    }
}