package com.hogwarts.testTraining.framework.po_pattern_02;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;

/**
 * @ClassName POTestCase，用于做PO
 * @Description 使用类和yaml文件进行交互,将定义的yaml文件转换成这个类
 * @Author sfmewl
 * @Date 2021/6/26 17:05
 **/
public class POTestCase extends TestCase{

//    private static WebDriver webDriver = new ChromeDriver();
    private WebElement currentElement;
    private MainPage mainPage;


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


    public void run() throws InterruptedException {
        try {
            steps.forEach(step->{
                String key = step.keySet().iterator().next();
//                String[] objectMethod = key.split(".");
//                String object = objectMethod[0];
//                String method = objectMethod[1];

                if (step.keySet().contains("init")){
                    ArrayList<String> value = (ArrayList<String>) getValue(step, "init");
                    mainPage = (MainPage) BasePage.getInstance().poInit(value.get(0), value.get(1));
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


}