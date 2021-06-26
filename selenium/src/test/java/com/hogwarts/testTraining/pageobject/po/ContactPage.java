package com.hogwarts.testTraining.pageobject.po;

import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.security.Identity;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @ClassName ContactPage
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/23 22:31
 **/
public class ContactPage extends BasePage{

    // PO原则2：不要暴露页面内部实现细节（使用private属性）
    private By parterInfo = By.cssSelector(".js_party_info");;

    // 构造函数调用父类的构造函数
    public ContactPage(WebDriver webDriver) {
        super(webDriver);
    }

    // PO原则6：同一个业务返回状态时需要返回不同的PO类
    // 添加成功 与 添加识别 返回的页面页面是不同的，需要封装为不同的方法
    public ContactPage addMember(String username, String acctid, String mobile){
        return this;
    }

    public ContactPage addDepart(String departName){
        // TODO:添加部门
        //click(By.cssSelector(".member_colLeft_top_addBtn"));
        click(By.linkText("添加"));
        click(By.linkText("添加部门"));

        sendKeys(By.name("name"), departName);
        click(By.linkText("选择所属部门"));

        // 使用这个定位部门选择时报错(报错原因是定位到不止一个元素，弹框后面也会有相同的元素被定位到)：org.openqa.selenium.ElementClickInterceptedException: element click intercepted: Element <a class="jstree-anchor jstree-clicked" href="#" tabindex="-1" id="1688850382079544_anchor">...</a> is not clickable at point (204, 163). Other element would receive the click: <div class="qui_mask ww_mask js_dialog_mask" style="height: 100%; width: 100%;"></div>
        //  (Session info: chrome=91.0.4472.114)
        //click(By.linkText("企业微信自动化测试"));

        // 下拉框元素如果是折叠的情况导致定位不到，需要先点击一下然后在进行定位

        webDriver.findElements(By.linkText("企业微信自动化测试")).get(1).click();
        //click(By.linkText("//a[text()='企业微信自动化测试'])[2]"));

        click(By.linkText("确定"));

        return this;
    }

    // PO原则5：不要实现所有的方法（细节），按需封装
    public ContactPage searchDepart(String departName) throws InterruptedException {

        // PO原则1：用公共方法代表页面所提供的功能
        // PO原则3：通常不要再PO方法内添加断言
        sendKeys(By.id("memberSearchInput"), departName);

        String content = webDriver.findElement(parterInfo).getText();
        System.out.println(content);

        /**
         * 上述页面元素 ".js_party_info" 是有了，但是具体的 Text 还没有刷出来，这时候用隐式等待是无法解决的，可以通过显示等待解决
         * 此外也可以通过某些操作解决，比如说click一下
         */
        // 隐式等待
        //webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // 显示等待
        //WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);
        //webDriverWait.until(ExpectedConditions.textMatches(By.cssSelector(".js_party_info"), Pattern.compile("无任何成员")));

        // click一下解决上述问题
        click(By.cssSelector(".ww_icon_AddMember"));



        // 断言不建议放在PO内部
        //assertTrue(content.contains("无任何成员"));


        return this;
    }

    // 获取部门信息
    public String getPartyInfo(){
        String content = webDriver.findElement(parterInfo).getText();
        System.out.println("------------------" + content);
        return content;
    }

    /**
     * 清除部门（清除所有的成员）
     * @param departName
     * @throws InterruptedException
     */
    public void clearAllDeparts(String departName) throws InterruptedException {
        searchDepart(departName);
    }


}