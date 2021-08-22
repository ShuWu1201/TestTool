package framework.global;

import framework.actions.ApiActionModel;
import framework.api.ApiObjectModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ClassName ApiLoader
 * @Description 接口对象加载器（将接口对象列表反序列化为一个个对象）
 * @Author sfmewl
 * @Date 2021/8/19 12:43
 **/
public class ApiLoader {

    public static final Logger logger = LoggerFactory.getLogger(ApiLoader.class);

    /**
     * 加载所有api Object对象，并保存到本列表中
     */
    private static ArrayList<ApiObjectModel> apis = new ArrayList<>();

    public static void load(String dir){
        Arrays.stream(new File(dir).list()).forEach(path -> {
            try {
                apis.add(ApiObjectModel.load(dir + "/" + path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static ApiActionModel getAction(String apiName, String actionName){

        /**
         * lambda方法里面引用的局部变量必须是一个final
         */
        final ApiActionModel[] apiActionModel ={ new ApiActionModel() };

        apis.stream().filter(api -> api.getName().equals(apiName)).forEach(api -> {
            apiActionModel[0] = api.getActions().get(actionName);
        });

        if (apiActionModel[0] != null){
            return apiActionModel[0];
        }else {
            logger.info("没有找到接口对象：" + apiName + "中的action：" + actionName);
        }
        return null;
    }

}