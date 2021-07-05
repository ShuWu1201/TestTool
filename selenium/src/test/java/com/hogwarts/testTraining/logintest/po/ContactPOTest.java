package com.hogwarts.testTraining.logintest.po;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @ClassName ContactPOTest
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/23 22:36
 **/
public class ContactPOTest {

    private static MainPage mainPage;

    @BeforeAll
    public static void beforeAll() throws IOException, InterruptedException {
        mainPage = new MainPage();

        /**
         * 测试数据的清理为什么不放在 @AfterAll 里：
         * 因为用例中途停止、或者执行途中失败就可能会导致脏数据，以至于用例无法执行成功
         */
        // 在测试添加功能之前先要删除相关数据。如果可以通过数据库、或者接口删除的就尽量不要用UI来删除测试数据
        mainPage.contract().searchDepart("测试部门四").clearAllDeparts("测试部门四");
    }


    @Ignore
    void testSearchDepartment() throws IOException, InterruptedException {
        /**
         * 步骤：1、打开页面；2、复用session登录
         */
        MainPage mainPage = new MainPage();

        /**
         * 3、跳转页面
         * 4、部门搜索
         */
        ContactPage contactPage = mainPage.contract();
        //contactPage.addMember("李四", "1111111", "12345877665");
        contactPage.searchDepart("测试部门一");

        String context = contactPage.getPartyInfo();
        assertTrue(context.contains("无任何成员"));

    }

    /**
     * PO 模式分层中，测试case只需要代表业务，而不需要有任何底层的实现（如PO、webDriver、findElement等）。
     * PO 代表业务与具体实现（前端）
     * 后期代码维护过程中只需要修改业务脚本即可，业务才是工作中的核心和灵魂。
     *
     * 后期测试需要参数化等
     * @throws IOException
     * @throws InterruptedException
     */
    @Ignore
    void testSearchDepartmentChain() throws IOException, InterruptedException {
        assertTrue(new MainPage().contract().searchDepart("测试部门一").getPartyInfo().contains("无任何成员"));
    }

    /**
     * 流程：首页——》添加成员——》添加部门——》搜索部门——》获取部门文案——》断言
     * @throws InterruptedException
     */
    @Test
    void testDepartAdd() throws InterruptedException {
        String departName = "测试部门四";
        assertTrue(mainPage.contract().addDepart(departName).searchDepart(departName).getPartyInfo().contains(departName));
    }

}