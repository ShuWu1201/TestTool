package com.hogwarts.testTraining.logintest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestWeChatLogin
 * @Description 自动化测试企业微信扫码登录；先手动扫码将cookie保存下来后登录的时候直接addCookie的方式注入
 * @Author sfmewl
 * @Date 2021/6/20 22:52
 **/
public class TestWeChat {

    /**
     * 由于webDriver是在@BeforeAll注解修饰的静态方法中初始化的
     * 所有需要定义一个静态变量（类级别的变量）
     */
    public static WebDriver webDriver;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/sfmewl/Documents/process/selenium/chromedriver");
        webDriver = new ChromeDriver();
    }

    /**
     * 自动化登录中的方法：复用cookies。手动扫码将浏览器登录的cookies序列化之后保存到cookies.yaml文件中
     * 这一步实在没有cookie的时候保存cookie使用的，当有cookie之后就不需要了，可以设置为类中方法，需要的时候调用即可
     * @throws InterruptedException
     * @throws IOException
     */
    public static void needLogin() throws InterruptedException, IOException {

        webDriver.get("https://work.weixin.qq.com/wework_admin/frame");

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        Thread.sleep(20000);

        // 获取到所有的cookie
        Set<Cookie> cookies = webDriver.manage().getCookies();

        // 通过 new YAMLFactory() 初始化一个 ObjectMapper 对象
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        // 将 cookies 读取到 mapper 中，并将 mapper 写到 cookies.yaml 文件中
        mapper.writeValue(new File("cookies.yaml"), cookies);

        System.exit(0);
    }

    /**
     * 将上述保存下来的cookies反向序列化之后使用
     * 但是cookies很多情况是不支持反向序列化去读的，需要我们自己去解析。
     *
     * 解析：从企业微信的cookie来看，List，List里面存的是HashMap
     *
     * 通常cookies在服务端和客户端都是有过期时间的，客户端我们可以设置为永久过期，但是服务端一般是会定时过期的（比如一周或者一个月），此时的解决方法：
     * 1、通过配置白名单方式让xx用户cookie永不过期
     * 2、测试环境中不校验等方法
     * @throws InterruptedException
     * @throws IOException
     */
    @BeforeAll
    public static void testLoginedBeforeAll() throws IOException, InterruptedException {

        File file = new File("cookies.yaml");

        /**
         * 判断cookie文件是否存在，存在即复用cookie，如果不存在就扫码获取cookie
         */
        if (file.exists()) {
            webDriver.get("https://work.weixin.qq.com/wework_admin/frame");

            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            /**
             * 企业微信的cookies不支持反向序列化去读，需要我们自己去解析
             */
//        // 通过 new YAMLFactory() 初始化一个 ObjectMapper 对象
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        // 需要将 cookies.yaml 文件转换为 Set<Cookie> 类型的对象
//        TypeReference typeReference = new TypeReference<Set<Cookie>>() {};
//        // 将 cookies 读取到 mapper 中，并将 mapper 写到 cookies.yaml 文件中
//        Set<Cookie> cookies = (Set<Cookie>) mapper.readValue(new File("cookies.yaml"), typeReference);

            /**
             * 解析：从企业微信的cookie来看，List，List里面存的是HashMap
             */
            // 通过 new YAMLFactory() 初始化一个 ObjectMapper 对象
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            // 需要将 cookies.yaml 文件转换为 Set<Cookie> 类型的对象
            TypeReference typeReference = new TypeReference<List<HashMap<String, Object>>>() {};
            // 将 cookies 读取到 mapper 中，并将 mapper 写到 cookies.yaml 文件中
            List<HashMap<String, Object>> cookies = (List<HashMap<String, Object>>) mapper.readValue(new File("cookies.yaml"), typeReference);

            cookies.forEach(cookieMap ->{
                // addCookie 支持单个增加 cookie
                webDriver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString()));
            });

            // 刷新一下网页
            webDriver.navigate().refresh();

            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            //Thread.sleep(10000);
        } else {
            needLogin();
        }
    }

    @Test
    public void testAddContact(){
        //webDriver.findElement(By.cssSelector("[node-type=addmember]")).click();
        webDriver.findElement(By.linkText("添加成员")).click();

        /**
         * 一般表单的 name 是含有业务属性的，而且是和服务端实体类的字段是有一定关联的（前端不会随便改表单的name属性），因此可以使用name属性。
         */
        webDriver.findElement(By.name("username")).sendKeys("张三");
        webDriver.findElement(By.name("acctid")).sendKeys("123456");
        webDriver.findElement(By.name("mobile")).sendKeys("15013145200");

        // linkTest 会被转成 xpath，而且 linkTest 一般也不会变
        webDriver.findElement(By.linkText("保存并继续添加")).click();

        /**
         * 用例执行结束后校验：如上场景
         * 1、比如保存成功后有一个"保存成功"的toast"可以进行校验，但是toast提示可能因为页面渲染慢导致无法定位，不推荐
         * 2、可以校验保存成功后页面上展示的信息等
         * 3、如果后面有用例有校验这个点，可以暂时忽略
         */
    }

    @Ignore
    public void search(){

    }


    // 简单封装
    void click(By by){
        webDriver.findElement(by).click();
    }

    // 简单封装
    void sendKeys(By by, String content){
        webDriver.findElement(by).sendKeys(content);
    }


    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }
}