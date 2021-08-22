package framework.steps;

import framework.global.ApiLoader;
import framework.global.GlobalVariables;
import framework.utils.PlaceholderUtils;
import io.restassured.response.Response;
import lombok.Data;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @ClassName StepModel
 * @Description 用例中的step对象
 * @Author sfmewl
 * @Date 2021/8/19 14:33
 **/
@Data
public class StepModel {
    public static final Logger logger = LoggerFactory.getLogger(StepModel.class);

    /**
     * 定义testcase里面的模型
     */
    private String api;
    private String action;
    private ArrayList<String> actualParameter;
    private ArrayList<AssertModel> asserts;

    private HashMap<String, String> save;
    private HashMap<String, String> saveGlobal;


    /**
     * 如果actualParameter中不是真实的实参而是模版(如带$占位符的)需要再转换一遍
     */
    private ArrayList<String> finalActualParameter = new ArrayList<>();
    /**
     * 真实参数，用于替换actualParameter里面的模版参数
     */
    private HashMap<String, String> stepVariables = new HashMap<>();

    /**
     * 存储执行后的返回信息
     */
    private StepResult stepResult = new StepResult();

    /**
     * 存储需要断言的中间态
     */
    private ArrayList<Executable> assertList = new ArrayList<>();


    /**
     * @param testCaseVariables step里面save存储下来的变量，run()方法中会替换掉带$符的入参
     * @return
     */
    public StepResult run(HashMap<String, String> testCaseVariables){
        /**
         * 替换参数中带$符的入参
         */
        if (actualParameter != null){
            finalActualParameter.addAll(PlaceholderUtils.resolveList(actualParameter, testCaseVariables));
        }

        /**
         * 根据case中配置的api对象和action信息，取出并执行相应的action
         */
        Response response = ApiLoader.getAction(api, action).run(finalActualParameter);


        /**
         * 存储save
         */
        if (save != null){
            save.forEach((variablesName, path) -> {
                String value = response.path(path).toString();
                stepVariables.put(variablesName, value);
                logger.info("全局变量更新：" + stepVariables);
            });
        }

        /**
         * 存储saveGloabl
         */
        if (null != saveGlobal){
            saveGlobal.forEach((variablesName, path) -> {
                String value = response.path(path).toString();
                GlobalVariables.getGlobalVariables().put(variablesName, value);
                logger.info("全局变量更新：" + GlobalVariables.getGlobalVariables());
            });
        }

        /**
         * 处理软断言需要的中间断言数据
         */
        if (asserts != null){
            asserts.stream().forEach(assertModel -> {
                assertList.add(() -> {
                   assertThat(assertModel.getReason(),
                           response.path(assertModel.getActual()).toString(),
                           equalTo(assertModel.getExpect()));
                });
            });
        }

        /**
         * 将response和断言结果存储到stepResult对象中并返回
         */
        stepResult.setAssertList(assertList);
        stepResult.setStepVariable(stepVariables);
        stepResult.setResponse(response);

        return stepResult;
    }

}