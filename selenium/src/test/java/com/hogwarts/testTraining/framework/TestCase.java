package com.hogwarts.testTraining.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestCase
 * @Description 使用类和yaml文件进行交互,将定义的yaml文件转换成这个类
 * @Author sfmewl
 * @Date 2021/6/26 17:05
 **/
public class TestCase {

    public List<String> data;
    // search.yaml 文件中，HashMap的Value具体类型不确定，使用Object
    public List<HashMap<String, Object>> steps;
    private WebDriver webDriver;
    private WebElement currentElement;
    public int index = 0;


    /**
     * 基于外部文件的测试数据生成多个测试用例
     * @return
     */
    public List<TestCase> testcaseGenerate(){
        List<TestCase> testCaseList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            TestCase testCaseNew = new TestCase();
            testCaseNew.index = i;
            testCaseNew.steps = steps;
            testCaseNew.data = data;
            testCaseList.add(testCaseNew);
        }
        return testCaseList;
    }

    /**
     * getValue()方法替换 yaml 文件中的变量
     * 数据为复杂数据结构时需要使用递归
     */
    private Object getValue(Map<String, Object> step, String key){
        Object value = step.get(key);
        if (value instanceof String){
            // 对获取到的值进行替换 TODO：支持复杂类型，复杂类型一般使用递归
            return ((String)value).replace("${data}", data.get(index));
        }else {
            return value;
        }
    }
    private Object getValue(Map<String, Object> step, String key, Object defaultValue){
        return step.getOrDefault(key, defaultValue);
    }


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

//                    webDriver.manage().timeouts().implicitlyWait(
//                            (Integer) step.get("implicitly_wait"), TimeUnit.SECONDS);
                            //(Long)step.getOrDefault("implicitly_wait", 5), TimeUnit.SECONDS);
                    webDriver.manage().timeouts().implicitlyWait(
                            (Integer) getValue(step, "implicitly_wait", 5), TimeUnit.SECONDS);
                }
                if (step.keySet().contains("get")){
                    System.out.println(step);

                    webDriver.get((String) step.get("get"));
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

                    currentElement = webDriver.findElement(bys.get(0));
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
                    webDriver.findElement(By.id((String) getValue((HashMap)step.get("click"), "id"))).click();

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
            webDriver.quit();
        }
    }

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


}