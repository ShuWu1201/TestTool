package com.hogwarts.testTraining.framework.po_pattern_02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TestCase基类
 * @Description 封装TestCase公共的方法等
 * @Author sfmewl
 * @Date 2021/7/2 08:19
 **/
public class TestCase {

    public List<String> data;
    // search.yaml 文件中，HashMap的Value具体类型不确定，使用Object
    public List<HashMap<String, Object>> steps;

    public int index = 0;

    /**
     * 基于外部文件的测试数据生成多个测试用例
     * @return
     */
    public List<POTestCase> testcaseGenerate(){
        List<POTestCase> testCaseList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            POTestCase testCaseNew = new POTestCase();
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
    public Object getValue(Map<String, Object> step, String key){
        Object value = step.get(key);
        if (value instanceof String){
            // 对获取到的值进行替换 TODO：支持复杂类型，复杂类型一般使用递归
            return ((String)value).replace("${data}", data.get(index));
        }else {
            return value;
        }
    }
    public Object getValue(Map<String, Object> step, String key, Object defaultValue){
        return step.getOrDefault(key, defaultValue);
    }

    /**
     * 空方法，需要子类进行覆盖
     */
    public void run() throws InterruptedException {

    }

}