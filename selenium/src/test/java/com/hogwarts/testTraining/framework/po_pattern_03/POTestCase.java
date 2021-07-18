package com.hogwarts.testTraining.framework.po_pattern_03;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName POTestCase，用于做PO
 * @Description 使用类和yaml文件进行交互,将定义的yaml文件转换成这个类
 * @Author sfmewl
 * @Date 2021/6/26 17:05
 **/
public class POTestCase extends TestCase {

//    private static WebDriver webDriver = new ChromeDriver();
    private WebElement currentElement;
    MainPage mainPage;
    private POTestCase poTestCase;

    public MainPage getMainPage() {
        return mainPage;
    }

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

//    public static WebDriver getWebDriver() {
//        webDriver = new ChromeDriver();
//        return webDriver;
//    }

//    public void setWebDriver(WebDriver webDriver) {
//        this.webDriver = webDriver;
//    }

    public WebElement getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(WebElement currentElement) {
        this.currentElement = currentElement;
    }


    @Override
    public void run() throws InterruptedException {
        try {
            steps.forEach(step->{
                String key = step.keySet().iterator().next();
//                String[] objectMethod = key.split(".");
//                String object = objectMethod[0];
//                String method = objectMethod[1];

                if (step.keySet().contains("init")){
                    ArrayList<String> value = (ArrayList<String>) getValue(step, "init");

                    BasePage basePage = BasePage.getInstance().poInit(value.get(0), value.get(1));
                    System.out.println(basePage);

                    mainPage = new MainPage();

                    BeanUtils.copyProperties(basePage, mainPage);



//                    Field[] fields = basePage.getClass().getDeclaredFields();
//                    for (Field field: fields) {
//                    }
//                    this.mainPage = ((MainPage) basePage);

//                    mainPage = (MainPage) (BasePage.getInstance().poInit(value.get(0), value.get(1)));
//                    mainPage.webDriver.get("https://baidu.com");
                    System.out.println("反射生成的类：" + mainPage);

                    //webDriver = new ChromeDriver();
                }
//                if (step.keySet().contains("implicitly_wait")){
//                    System.out.println(step);
//
//                    this.webDriver.manage().timeouts().implicitlyWait(
//                            (Integer) getValue(step, "implicitly_wait", 5), TimeUnit.SECONDS);
//                }

                /**
                 * 解决mainPage.search的调用，但是不推荐，后期维护还是需要修改
                 */
//                if (step.keySet().contains(".")){
//                    String key = step.keySet().iterator().next();
//                    String method = key.split(".")[1];
//
//                    if (method.equals("search")){
//                        mainPage.search();
//                    }
//                }

                /**
                 * 使用反射的方式解决上述mainPage.search的调用
                 */
                if (step.keySet().toString().contains(".")){
                    // 公共的方法放在最前面
//                    String key = step.keySet().iterator().next();
                    String[] objectMethod = key.toString().split("\\.");
                    String object = objectMethod[0];
                    String method = objectMethod[1];

                    // 解决了找方法的问题
                    BasePage.getInstance().getPO(object).stepRun(method);
                }


            });
        } finally {

            Thread.sleep(5000);
            mainPage.webDriver.quit();
        }
    }

    @Override
    public void run01(){
        try {
            mainPage.webDriver = new ChromeDriver();
            steps.forEach(step->{
                System.out.println(step);

                if (step.keySet().contains("chrome")){
                    System.out.println(step);

                    mainPage.webDriver = new ChromeDriver();
                }
                if (step.keySet().contains("implicitly_wait")){
                    System.out.println(step);

//                    webDriver.manage().timeouts().implicitlyWait(
//                            (Integer) step.get("implicitly_wait"), TimeUnit.SECONDS);
                    //(Long)step.getOrDefault("implicitly_wait", 5), TimeUnit.SECONDS);
                    mainPage.webDriver.manage().timeouts().implicitlyWait(
                            (Integer) getValue(step, "implicitly_wait", 5), TimeUnit.SECONDS);
                }
                if (step.keySet().contains("get")){
                    System.out.println(step);

                    mainPage.webDriver.get((String) step.get("get"));
                    //webDriver.get(getValue(step, "get").toString());
                }
                if (step.keySet().contains("find")){
                    System.out.println(step);

                    // 为了安全，lambda 表达式里不能赋值，因此要使用安全的原子引用
                    //AtomicReference<By> by = null;
                    ArrayList<By> bys = new ArrayList<>();

                    // entrySet 是设置类型为Map的变量
                    //((HashMap)getValue(step, "find")).entrySet().forEach(stringEntry -> {...});
                    ((HashMap<String, String>)step.get("find")).entrySet().forEach(stringEntry -> {
                        if (stringEntry.getKey().contains("id")){
                            bys.add(By.id(stringEntry.getValue()));
                        }

                        if (stringEntry.getKey().contains("xpath")){
                            bys.add(By.xpath(stringEntry.getValue()));
                        }

                    });

                    currentElement = mainPage.webDriver.findElement(bys.get(0));
                }


                if (step.keySet().contains("send_keys")){

                    // HaspMap 中 remove 可以将删除的数据返回
                    //System.out.println(((HashMap<String, String>)step.get("send_keys")).remove("data"));

                    // currentElement.sendKeys(((HashMap<String, String>)step.get("send_keys")).get("data"));
                    // 封装getValue后的写法，从step中的map中取值
                    currentElement.sendKeys(getValue(step, "send_keys").toString());

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                if (step.entrySet().contains("click")){
                    System.out.println(step);
                    mainPage.webDriver.findElement(By.id((String) getValue((HashMap)step.get("click"), "id"))).click();

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (step.keySet().contains("sleep")){
                    try {
                        Thread.sleep(((Long.valueOf((Integer)getValue(step, "sleep"))) * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            });
        } finally {
            mainPage.webDriver.quit();
        }
    }

}