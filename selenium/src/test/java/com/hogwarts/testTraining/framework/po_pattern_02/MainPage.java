package com.hogwarts.testTraining.framework.po_pattern_02;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName MainPage
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/27 20:53
 **/
public class MainPage extends BasePage{

    public MainPage() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://baidu.com");
//        System.out.println("MainPage 初始化 driver");
    }

//    public MainPage(WebDriver webDriver) {
//        super(webDriver);
//        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        webDriver.get("https://baidu.com");
//    }

    public SearchPage search(){
        return new SearchPage(this.webDriver);
    }


    /**
     * 使用注册的方式，需要在实现类中实现，不推荐，代码被写死
     * @param method
     */
    void stepRun01(String method){
        if (method.equals("search")){
            this.search();
        }
    }



    /**
     * 使用反射的方式，可以在基类中实现
     * @param method
     */
//    void stepRun02(String method){
//        Method methodJava = Arrays.stream(this.getClass().getMethods())
//                .filter(method1 -> method1.getName().equals(method))
//                .findFirst().get();
//
//        try {
//            methodJava.invoke(this);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }

}