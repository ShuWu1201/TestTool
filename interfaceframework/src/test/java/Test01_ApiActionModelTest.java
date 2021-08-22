import framework.actions.ApiActionModel;
import framework.global.GlobalVariables;
import groovy.json.JsonOutput;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @ClassName Test01_ApiActionModelTest
 * @Description 对ApiActionModel实现的单元测试
 * @Author sfmewl
 * @Date 2021/8/19 09:58
 **/
public class Test01_ApiActionModelTest {

    /**
     * 开发时尽量不要使用System.out.println()，原有是Logger知道是那个类输出的，也会更高效
     */
    public static final Logger logger = LoggerFactory.getLogger(Test01_ApiActionModelTest.class);


    @Test
    void runTest(){
        /**
         * 定义和初始化actualParameter实参
         */
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add("wwc13e1f3c6a12d7aa");
        actualParameter.add("AEr-bKcNeHwL0TocDbloAuIEGeyVvioxLQymHelhVjI");

        /**
         * 设置全局变量
         */
        HashMap<String, String> golbalVaribalbes = new HashMap<>();
        golbalVaribalbes.put("x", "gettoken");
        GlobalVariables.setGlobalVariables(golbalVaribalbes);

        /**
         * 行参
         */
        ArrayList<String> formalParameter = new ArrayList<>();
        formalParameter.add("corpid");
        formalParameter.add("corpsecret");

        /**
         * 构造query
         */
        HashMap<String, String> query = new HashMap<>();
        query.put("corpid", "${corpid}");
        query.put("corpsecret", "${corpsecret}");

        /**
         * 定义和实例化apiActionModel
         */
        ApiActionModel apiActionModel = new ApiActionModel();
        apiActionModel.setUrl("https://qyapi.weixin.qq.com/cgi-bin/${x}");
        apiActionModel.setFormalParam(formalParameter);
        apiActionModel.setQuery(query);

        Response response = apiActionModel.run(actualParameter);

    }

}