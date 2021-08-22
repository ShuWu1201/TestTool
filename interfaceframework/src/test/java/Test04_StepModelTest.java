import framework.actions.ApiActionModel;
import framework.global.ApiLoader;
import framework.steps.AssertModel;
import framework.steps.StepModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @ClassName Test03_ApiLoader
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/19 13:01
 **/
public class Test04_StepModelTest {

    public static final Logger logger = LoggerFactory.getLogger(Test04_StepModelTest.class);

    @BeforeAll
    public static void loadTest(){
        ApiLoader.load("src/main/resources/api");
        logger.info("debugger");
    }

    @Test
    public void runTest(){
        /**
         * 实参
         */
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add("wwc13e1f3c6a12d7aa");
        actualParameter.add("AEr-bKcNeHwL0TocDbloAuIEGeyVvioxLQymHelhVjI");


        /**
         * 断言
         */
        ArrayList<AssertModel> asserts = new ArrayList<>();
        AssertModel assertModel = new AssertModel();
        assertModel.setActual("errcode");
        assertModel.setExpect("2");
        assertModel.setMatcher("equalTo");
        assertModel.setReason("getToken错误码校验01！");
        asserts.add(assertModel);
        /**
         * save
         */
        HashMap<String, String> save = new HashMap<>();
        save.put("accesstoken", "access_token");

        /**
         * saveGolbal
         */
        HashMap<String, String> globalsave = new HashMap<>();
        globalsave.put("accesstoken", "access_token");

        StepModel stepModel = new StepModel();
        stepModel.setApi("tokenhelper");
        stepModel.setAction("getToken");
        stepModel.setActualParameter(actualParameter);
        stepModel.setAsserts(asserts);
        stepModel.setSave(save);
        stepModel.setSaveGlobal(globalsave);


        stepModel.run(null);
        logger.info("Debugger");

    }

}