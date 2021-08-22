import framework.actions.ApiActionModel;
import framework.global.ApiLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


/**
 * @ClassName Test03_ApiLoader
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/19 13:01
 **/
public class Test03_ApiLoader {

    public static final Logger logger = LoggerFactory.getLogger(Test03_ApiLoader.class);

    @BeforeAll
    public static void loadTest(){
        ApiLoader.load("src/main/resources/api");
        logger.info("debugger");
    }

    @Test
    public void getActionTest(){
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add("wwc13e1f3c6a12d7aa");
        actualParameter.add("AEr-bKcNeHwL0TocDbloAuIEGeyVvioxLQymHelhVjI");

        ApiActionModel apiActionModel = ApiLoader.getAction("tokenhelper", "getToken");
        apiActionModel.run(actualParameter);
    }

}