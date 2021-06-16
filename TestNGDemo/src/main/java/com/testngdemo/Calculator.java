package com.testngdemo;

/**
 * @ClassName Calculator
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/15 07:37
 **/
public class Calculator {
    public static int result = 0;

    /**
     * 加法功能
     * @param x
     * @param y
     * @return
     */
    public static int add(int x, int y){
//        int result = x + y;
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return result;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return x + y;
    }

    /**
     * 累加功能
     * @param x
     * @return
     * @throws InterruptedException
     */
    public static int count(int x) throws InterruptedException {
        int i = result;
        Thread.sleep(1000);
        result = i + x;
        return result;
    }

    /**
     * 累加功能，方法加锁保证线程安全
     * @param x
     * @return
     * @throws InterruptedException
     */
    public synchronized static int countSyn(int x) throws InterruptedException {
        int i = result;
        Thread.sleep(2000);
        result = i + x;
        return result;
    }

    /**
     * 减法功能
     * @param x
     * @param y
     * @return
     */
    public static int subtract(int x, int y){
//        int result = x - y;
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return result;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return x - y;
    }

    /**
     * 乘法功能
     * @param x
     * @param y
     * @return
     */
    public static int multiply(int x, int y){
        result = x * y;
        return result;
    }

    /**
     * 除法功能
     * @param x
     * @param y
     * @return
     */
    public static int divide(int x, int y){
        result = x / y;
        return result;
    }

    /**
     * 清零功能
     */
    public static void clear(){
        result = 0;
        System.out.println("当前结果已清零！");
    }

}