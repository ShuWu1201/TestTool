import framework.api.ApiObjectModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @ClassName Test02_ApiObjectModelTest
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/19 10:59
 **/
public class Test02_ApiObjectModelTest {

    public static final Logger logger = LoggerFactory.getLogger(Test02_ApiObjectModelTest.class);

    @Test
    void loadTest() throws IOException {
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add("wwc13e1f3c6a12d7aa");
        actualParameter.add("AEr-bKcNeHwL0TocDbloAuIEGeyVvioxLQymHelhVjI");

        ApiObjectModel apiObjectModel= ApiObjectModel.load("src/main/resources/api/tokenhelper.yaml");
        //ApiObjectModel apiObjectModel1= ApiObjectModel.load("src/main/resources/api/department.yaml");

        logger.info("apiObjectModel", apiObjectModel.toString());

        apiObjectModel.getActions().get("getToken").run(actualParameter);
    }

}