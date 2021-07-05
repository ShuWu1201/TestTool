package com.hogwarts.testTraining.framework.po_pattern_02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @ClassName BasePage
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/27 20:53
 **/
public class BasePage {
    static BasePage instance = null;
    WebDriver webDriver;
    // 将所有po都存储在pages里，需要使用的时候获取即可
    HashMap<String, BasePage> pages = new HashMap<>();

    // 获取到一个单例的方法，这个单例用于存储所有的po
    public static BasePage getInstance() {
        if (instance == null){
            instance = new BasePage();
        }
        return instance;
    }

    BasePage getPO(String name){
        pages.keySet().forEach(page -> {
            System.out.println("key:" + page.toString());
        });
        return pages.get(name);
    }

    public BasePage() {
        // 每次基类初始化时都会首先调用父类的构造方法，因此不能在基类中初始化webDriver
        //this.webDriver = new ChromeDriver();
        System.out.println("BasePage 初始化 driver 无参");
    }

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        System.out.println("BasePage 初始化 driver 有参");
    }

    // 简单封装
    void click(By by){
        webDriver.findElement(by).click();
    }

    // 简单封装
    void sendKeys(By by, String content){
        webDriver.findElement(by).sendKeys(content);
    }


    /**
     * 反射的方式调用方法
     * @param method
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    void stepRun(String method){
        //getMethods只能找到public修饰的方法；getDeclaredMethods可以找到类中声明的所有方法
        Method methodInvoke = Arrays.stream(this.getClass().getMethods()).filter(method1 ->
            method1.getName().equals(method)).findFirst().get();
        try {
            methodInvoke.invoke(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    BasePage poInit(String name, String className){
        BasePage pageClass = null;
        try {
            // 通过反射的方式创建类并实例化为一个对象
            // 反射提供的类名必须是全限定类名，即包括包名
            pageClass = (BasePage) Class.forName(className).getDeclaredConstructor().newInstance();
            pages.put(name, pageClass);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            return pageClass;
        }
    }

}