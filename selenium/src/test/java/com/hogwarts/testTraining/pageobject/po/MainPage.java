package com.hogwarts.testTraining.pageobject.po;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
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
 * @ClassName MainPage
 * @Description 首页（每一个公共方法代表一个行为）
 * @Author sfmewl
 * @Date 2021/6/23 22:30
 **/
public class MainPage extends BasePage{

    void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/sfmewl/Documents/process/selenium/chromedriver");
        //webDriver = new ChromeDriver();
    }

    /**
     * 自动化登录中的方法：复用cookies。手动扫码将浏览器登录的cookies序列化之后保存到cookies.yaml文件中
     * 这一步实在没有cookie的时候保存cookie使用的，当有cookie之后就不需要了，可以设置为类中方法，需要的时候调用即可
     * @throws InterruptedException
     * @throws IOException
     */
    void needLogin() throws InterruptedException, IOException {

        setUp();

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
    void testLoginedBeforeAll() throws IOException, InterruptedException {

        // cookies 过期后需要先删除文件，获取新的cookie后再执行
        File file = new File("cookies.yaml");

        /**
         * 判断cookie文件是否存在，存在即复用cookie，如果不存在就扫码获取cookie
         */
        if (file.exists()) {
            this.setUp();

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


    public MainPage() throws IOException, InterruptedException {
        this.testLoginedBeforeAll();
    }


    // 点击"添加成员"后返回的是另一个contact页面，所有返回的是一个类
    public ContactPage contract(){
        click(By.id("menu_contacts"));
        // PO原则4：跳转或者进入新页时返回新的PO类来模拟
        return new ContactPage(this.webDriver);
    }



}