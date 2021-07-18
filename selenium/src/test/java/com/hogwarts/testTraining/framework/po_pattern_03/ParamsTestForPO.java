package com.hogwarts.testTraining.framework.po_pattern_03;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.hogwarts.testTraining.framework.po_pattern_01.ParamsTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName ParamsTestForPO
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/7/2 08:07
 **/
public class ParamsTestForPO {

    @ParameterizedTest
    @MethodSource
    void search(POTestCase testCase) {

        System.setProperty("webdriver.chrome.driver", "/Users/sfmewl/Documents/process/selenium/chromedriver");
        //WebDriver webDriver = new ChromeDriver();

//        webDriver.manage().timeouts().implicitlyWait(seleniumTestCase.steps.)

        System.out.println(testCase);
        try {
            // runner 引擎
            testCase.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //testCase.getWebDriver().quit();
        }

        //testCase.getWebDriver().quit();

    }


    static List<POTestCase> search() throws IOException {
        //return Stream.of("apple", "banana");

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        //TypeReference typeReference = new TypeReference<List<String>>(){};


        TestCase testCase = mapper.readValue(
                //Thread.currentThread().getContextClassLoader().getClass().getResourceAsStream("/framework/search.yaml"),
                ParamsTest.class.getResourceAsStream("/framework/framework_search_po_test.yaml"),
                POTestCase.class
        );
        System.out.println("-------" + testCase.testcaseGenerate());
        return testCase.testcaseGenerate();

//        System.out.println(seleniumTestCase);
//        return Stream.of(seleniumTestCase);
    }

}