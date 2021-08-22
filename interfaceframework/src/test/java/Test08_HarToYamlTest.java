import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarRequest;
import framework.actions.ApiActionModel;
import framework.api.ApiObjectModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @ClassName Test08_HarToYamlTest
 * @Description
 *
 * 将bar文件转换为一个yaml文件，优点是基本的东西可以自动生成，减少人工编写成本，有利于团队推广使用
 * 缺点是yaml文件不一定完成，比如是否要保存参数为全局变量、断言策略、行参等
 *
 *
 * @Author sfmewl
 * @Date 2021/8/20 14:47
 **/
public class Test08_HarToYamlTest {

    private static final Logger logger = LoggerFactory.getLogger(Test08_HarToYamlTest.class);

    /**
     * 将bar文件转换为一个yaml文件
     * @throws HarReaderException
     * @throws IOException
     */
    @Test
    void harTest() throws HarReaderException, IOException {
        HarReader harReader = new HarReader();
        Har har = harReader.readFromFile(new File("src/main/resources/har/qyapi.weixin.qq.com.har"));
        logger.info("Debug!");

        ApiObjectModel apiObjectModel = new ApiObjectModel();
        ApiActionModel apiActionModel = new ApiActionModel();

        HashMap<String, ApiActionModel> actions = new HashMap<>();
        HashMap<String, String> queryMap = new HashMap<>();

        har.getLog().getEntries().forEach(entrie -> {
            HarRequest harRequest = entrie.getRequest();
            harRequest.getQueryString().forEach(query -> {
                queryMap.put(query.getName(), query.getValue());
            });

            String method = harRequest.getMethod().toString();
            String url = harRequest.getUrl();
            apiActionModel.setQuery(queryMap);

            if (method.equals("get")){
                apiActionModel.setGet(url);
            }else {
                apiActionModel.setPost(url);
            }
            actions.put(getRequestName(url), apiActionModel);
        });
        apiObjectModel.setName("tokenhelper");
        apiObjectModel.setActions(actions);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        /**
         * mapper.writeValue()其中的pathname应该是文件的路径
         * 如果是文件夹的路径会报错：java.io.FileNotFoundException: src/main/resources/har (Is a directory)
         */
        mapper.writeValue(new File("src/main/resources/har/tokenhelper.yaml"), apiObjectModel);
    }


    /**
     * 截取字符串
     * @param url
     * @return
     */
    public String getRequestName(String url) {
        /**
         * 以 ？ 进行分割后取索引为0的字符串后在以 / 分割，然后取最后你一个字符串
         * 例如："https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wwc13e1f3c6a12d7aa&corpsecret=AEr-bKcNeHwL0TocDbloAuIEGeyVvioxLQymHelhVjI"
         */
        String[] suburl = url.split("\\u003F")[0].split("/");
        String name = "";
        if (suburl.length > 1) {
            name = suburl[suburl.length - 1];
        }else if(1==suburl.length){
            name = suburl[0];
        }
        return name;
    }

    /**
     * 执行自动生成的yaml文件
     * @throws IOException
     */
    @Test
    public void runTest() throws IOException {
        ApiObjectModel apiObjectModel = ApiObjectModel.load("src/main/resources/har/tokenhelper.yaml");
        apiObjectModel.getActions().get("gettoken").run(null);
        logger.info("");
    }

}