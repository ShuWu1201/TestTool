package com.testngdemo.examples.packageC;

import jdk.jfr.Description;
import org.testng.annotations.Test;

/**
 * @ClassName ClassATest
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/15 09:13
 **/
public class ClassCTest {
    @Test(groups = {"group01"})
    @Description("testCaseC01 inside ClassCTest inside packageC")
    public void testCaseC01(){
        System.out.println("testCaseC01 inside ClassCTest inside packageC");
    }

    @Test(groups = {"group02"})
    @Description("testCaseC02 inside ClassCTest inside packageC")
    public void testCaseC02(){
        System.out.println("testCaseC02 inside ClassCTest inside packageC");
    }

    @Test(groups = {"group03"})
    @Description("testCaseC03 inside ClassCTest inside packageC")
    public void testCaseC03(){
        System.out.println("testCaseC03 inside ClassCTest inside packageC");
    }
}