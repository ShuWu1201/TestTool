package com.testngdemo.examples.packageB;

import jdk.jfr.Description;
import org.testng.annotations.Test;

/**
 * @ClassName ClassATest
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/15 09:13
 **/
public class ClassBTest {
    @Test(groups = {"group01"})
    @Description("testCaseB01 inside ClassBTest inside packageB")
    public void testCaseB01(){
        System.out.println("testCaseB01 inside ClassBTest inside packageB");
    }

    @Test(groups = {"group02"})
    @Description("testCaseB02 inside ClassBTest inside packageB")
    public void testCaseB02(){
        System.out.println("testCaseB02 inside ClassBTest inside packageB");
    }

    @Test(groups = {"group03"})
    @Description("testCaseB03 inside ClassBTest inside packageB")
    public void testCaseB03(){
        System.out.println("testCaseB03 inside ClassBTest inside packageB");
    }
}