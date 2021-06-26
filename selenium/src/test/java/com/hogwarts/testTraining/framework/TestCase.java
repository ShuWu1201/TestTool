package com.hogwarts.testTraining.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName TestCase
 * @Description 使用类和yaml文件进行交互,将定义的yaml文件转换成这个类
 * @Author sfmewl
 * @Date 2021/6/26 17:05
 **/
public class TestCase {

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebElement getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(WebElement currentElement) {
        this.currentElement = currentElement;
    }

    private WebDriver webDriver;

    public List<String> data;

    // search.yaml 文件中，HashMap的Value具体类型不确定，使用Object
    public List<HashMap<String, Object>> steps;

    private WebElement currentElement;

    public void run(){
        try {
            steps.forEach(step->{
                System.out.println(step);

                if (step.keySet().contains("chrome")){
                    System.out.println(step);

                    webDriver = new ChromeDriver();
                }
                if (step.keySet().contains("implicitly_wait")){
                    System.out.println(step);

                    webDriver.manage().timeouts().implicitlyWait(
                            (Integer) step.get("implicitly_wait"), TimeUnit.SECONDS);
                            //(Long)step.getOrDefault("implicitly_wait", 5), TimeUnit.SECONDS);
                }
                if (step.keySet().contains("get")){
                    System.out.println(step);

                    webDriver.get((String) step.get("get"));
                }
                if (step.keySet().contains("find")){
                    System.out.println(step);

                    // 为了安全，lambda 表达式里不能赋值，因此要使用安全的原子引用
                    //AtomicReference<By> by = null;
                    ArrayList<By> bys = new ArrayList<>();

                    // entrySet 是设置类型为Map的变量
                    ((HashMap<String, String>)step.get("find")).entrySet().forEach(stringEntry -> {
                        if (stringEntry.getKey().contains("id")){
                            bys.add(By.id(stringEntry.getValue()));
                        }

                        if (stringEntry.getKey().contains("xpath")){
                            bys.add(By.xpath(stringEntry.getValue()));
                        }

                    });

                    currentElement = webDriver.findElement(bys.get(0));
                }


                if (step.keySet().contains("send_keys")){

                    System.out.println(step);

                    // (String)step.get("send_keys")
                    currentElement.sendKeys("MMP");

                }

                if (step.keySet().contains("click")){
                    System.out.println(step);
                    currentElement.click();
                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        } finally {
            webDriver.quit();
        }
    }

}