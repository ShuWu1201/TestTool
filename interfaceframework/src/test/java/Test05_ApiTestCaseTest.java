import framework.global.ApiLoader;
import framework.steps.AssertModel;
import framework.steps.StepModel;
import framework.testcase.ApiTestCaseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @ClassName Test03_ApiLoader
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/19 13:01
 **/
public class Test05_ApiTestCaseTest {

    public static final Logger logger = LoggerFactory.getLogger(Test05_ApiTestCaseTest.class);

    @BeforeAll
    public static void loadTest(){
        ApiLoader.load("src/main/resources/api");
        logger.info("debugger");
    }

    @Test
    void loadApiTest() throws IOException {
        ApiTestCaseModel apiTestCaseModel = ApiTestCaseModel.load("src/main/resources/testcase/creatdepartment.yaml");
        logger.info("Debugger");
    }

    @Test
    public void runTest() throws IOException {

        ApiTestCaseModel apiTestCaseModel = ApiTestCaseModel.load("src/main/resources/testcase/creatdepartment.yaml");
        apiTestCaseModel.run();

        logger.info("Debugger");

    }

}