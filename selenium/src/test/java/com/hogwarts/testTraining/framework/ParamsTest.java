package com.hogwarts.testTraining.framework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.temporal.ChronoUnit;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @ClassName ParamsTest
 * @Description junit-jupiter-params包引入的参数化测试，参数来源包括@MethodSource、@ValueSource、@EnumSource
 * @Author sfmewl
 * @Date 2021/6/26 08:04
 **/
public class ParamsTest {
    // @MethodSource 从方法中返回数据
    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithExplicitLocalMethodSource(String argument) {
        System.out.println(argument);
        // TODO:测试步骤
        // TODO:测试数据
        // TODO:断言
        assertNotNull(argument);
    }

    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana");
    }


    @ParameterizedTest
    //@ValueSource(strings = {"百度搜索关键字1", "百度搜索关键字2"})
    // @MethodSource注解中如果不加参数会默认从同名的静态方法中获取参数
    @MethodSource
    void search(String keyWord) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/sfmewl/Documents/process/selenium/chromedriver");
        WebDriver webDriver = new ChromeDriver();

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://www.baidu.com/");

        webDriver.findElement(By.id("kw")).sendKeys(keyWord);
        webDriver.findElement(By.id("su")).click();

        Thread.sleep(5000);

        webDriver.quit();

    }

    static Stream<String> search(){
        return Stream.of("apple", "banana");
    }







    // @ValueSource 从数组中返回数据（八种基本类型+String数组+Class数组）
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void testWithValueSource(int argument) {
        System.out.println(argument);
        assertTrue(argument > 0 && argument < 4);
    }


    // @EnumSource 从枚举类型中返回数据
    @ParameterizedTest
    @EnumSource(names = { "DAYS", "HOURS" })
    void testWithEnumSourceInclude(ChronoUnit unit) {
        System.out.println(unit);
        assertTrue(EnumSet.of(ChronoUnit.DAYS, ChronoUnit.HOURS).contains(unit));
    }
}