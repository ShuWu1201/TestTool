package com.testngdemo.examples.packageA;

import jdk.jfr.Description;
import org.testng.annotations.Test;

/**
 * @ClassName ClassATest
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/15 09:13
 **/
public class ClassATest {
    /**
     * @Test(groups = {"smoke", "release"})
     * 将用例可以定义为冒烟或者发布的时候执行的case
     */
    @Test(groups = {"group01"})
    @Description("testCaseA01 inside ClassATest inside packageA")
    public void testCaseA01(){
        System.out.println("testCaseA01 inside ClassATest inside packageA");
    }

    @Test(groups = {"group02", "group01"})
    @Description("testCaseA02 inside ClassATest inside packageA")
    public void testCaseA02(){
        System.out.println("testCaseA02 inside ClassATest inside packageA");
    }

    @Test(groups = {"group03", "group01"})
    @Description("testCaseA03 inside ClassATest inside packageA")
    public void testCaseA03(){
        System.out.println("testCaseA03 inside ClassATest inside packageA");
    }
}