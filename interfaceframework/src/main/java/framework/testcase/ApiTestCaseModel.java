package framework.testcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.util.concurrent.FakeTimeLimiter;
import framework.api.ApiObjectModel;
import framework.steps.StepModel;
import framework.steps.StepResult;
import framework.utils.FakeUtils;
import lombok.Data;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @ClassName ApiTestCaseModel
 * @Description 用例yaml对应的数据对象
 * @Author sfmewl
 * @Date 2021/8/19 19:38
 **/
@Data
public class ApiTestCaseModel {

    public static final Logger logger = LoggerFactory.getLogger(ApiTestCaseModel.class);

    private String name;
    private String description;
    private ArrayList<StepModel> steps;
    private ArrayList<Executable> assertList =  new ArrayList<>();
    private HashMap<String,String> testCaseVariables = new HashMap<>();

    public static ApiTestCaseModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path),ApiTestCaseModel.class);
    }

    public void run(){
        /**
         * 1、加载用例层相关键字变量
         */
        this.testCaseVariables.put("getTimeStamp", FakeUtils.getTimeStamp());
        logger.info("用例变量更新：" + testCaseVariables);

        /**
         * 2、遍历step进行执行
         */
        steps.forEach(step -> {
            StepResult stepResult = step.run(testCaseVariables);

            /**
             * 3、处理step返回的sava变量
             */
            if (stepResult.getStepVariable().size() > 0){
                testCaseVariables.putAll(stepResult.getStepVariable());
                logger.info("testcase变量更新");
            }

            /**
             * 4、处理assertList，并进行统一断言
             */
            if (stepResult.getStepVariable().size() > 0){
                assertList.addAll(stepResult.getAssertList());
            }
        });

        /**
         * 5、进行统一断言
         */
        assertAll("", assertList.stream());

    }

}