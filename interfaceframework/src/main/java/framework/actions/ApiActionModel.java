package framework.actions;

import framework.global.GlobalVariables;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import framework.utils.PlaceholderUtils;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * @ClassName ApiActionModel
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/18 17:53
 **/
@Data
public class ApiActionModel {
    /**
     * 默认是get方法，也可以根据实际执行的方法进行赋值
     */
    private String method = "get";
    private String url;
    private String body;
    private String contentType;
    /**
     * 请求api模版的URL（包括链接和参数）
     */
    private HashMap<String, String> query;
    private HashMap<String, String> headers;
    private String post;
    private String get;
    private Response response;
    /**
     * formalParam代表yaml文件中的形参
     */
    private ArrayList<String> formalParam;

    /**
     * 实际执行时的参数
     */
    private HashMap<String, String> actionVariables = new HashMap<>();



    public Response run(ArrayList<String> actualParameter) {
        /**
         * case里面的参数替换模版中参数后，最终发起请求的参数
         */
        HashMap<String, String> finalQuery = new HashMap<>();
        /**
         * 使用runBody替代原有body(因为原有bady里面可能不止一次请求body，因此不能改变原有body的结构)
         */
        String runBody = this.body;
        /**
         * 使用runRrl替换原有url
         */
        String runUrl = this.url;


        /**
         * 1.确定请求方法和URL
         */
        if (post != null) {
            runUrl = post;
            method = "post";
        } else if (get != null) {
            runUrl = get;
            method = "get";
        }

        /**
         * 2、请求参数、URL中全局变量替换
         * PS:这里需要编写占位符工具类PlaceholderUtils
         */
        if (query != null) {
            finalQuery.putAll(PlaceholderUtils.resolveMap(query, GlobalVariables.getGlobalVariables()));
        }

//        //debug调试
//        HashMap<String, String> globalVariables = GlobalVariables.getGlobalVariables();

        //body全局变量替换(body是一个String)
        runBody = PlaceholderUtils.resolveString(runBody, GlobalVariables.getGlobalVariables());
        //url全局变量替换
        runUrl = PlaceholderUtils.resolveString(runUrl, GlobalVariables.getGlobalVariables());

        if (formalParam != null && actualParameter != null && formalParam.size() > 0 && actualParameter.size() > 0) {

            /**
             * 3、根据形参和实参来构建内部变量替换的Map
             */
            for (int index = 0; index < formalParam.size(); index++) {
                actionVariables.put(formalParam.get(index), actualParameter.get(index));
            }
            /**
             * 4、请求、URL中的内部变量进行一个替换
             */
            if(query !=null){
                finalQuery.putAll(PlaceholderUtils.resolveMap(query,actionVariables));
            }
            runBody =  PlaceholderUtils.resolveString(body,actionVariables);
            runUrl = PlaceholderUtils.resolveString(runUrl,actionVariables);
        }
        /**
         * 5、拿到了上面完成了变量替换的请求数据，我们接下来要进行请求并返回结果
         */
        RequestSpecification requestSpecification = given().log().all();
        if(contentType != null){
            requestSpecification.contentType(contentType);
        }
        if(headers !=null){
            requestSpecification.headers(headers);
        }
        if(finalQuery != null && finalQuery.size()>0){
            /**
             * 设置请求参数
             */
            requestSpecification.formParams(finalQuery);
        }else if (runBody !=null){
            requestSpecification.body(runBody);
        }

        Response response = requestSpecification.request(method,runUrl).then().log().all().extract().response();

        this.response = response;
        return response;
    }

}