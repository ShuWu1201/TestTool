package restassuredtest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * @ClassName GetAndPostTest
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/7/20 20:05
 **/
public class GetAndPostTest {

    /**
     * 获取accessTaken作为全局变量，以便post请求使用
     */
    public static String accessTaken = given()
            .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wwc13e1f3c6a12d7aa&corpsecret=QEXUPIltwraEpXo1lM0Q6S42Xw-i3Kogp5ue5VajiSg")
            .then()
            .extract().response().path("access_token");

    /**
     * Get 请求
     * 企业微信 获取access_token
     */
    @Test
    public void getMethodForAccessToken(){
        given()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wwc13e1f3c6a12d7aa&corpsecret=QEXUPIltwraEpXo1lM0Q6S42Xw-i3Kogp5ue5VajiSg")
                .then()
                .log()
                .all();
    }

    /**
     * Get 请求
     * 通过params()方法并将请求的参数存放在params()里面
     */
    @Test
    public void getMethodForAccessTokenAndSetParams(){
        given()
                .params("accessTaken", "wwc13e1f3c6a12d7aa", "corpsecret", "QEXUPIltwraEpXo1lM0Q6S42Xw-i3Kogp5ue5VajiSg")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken?")
                .then()
                .extract().response().path("access_token");
    }


    /**
     * Post 请求
     */
    @Test
    void postMethod(){

        given()
                .contentType("application/json;charset=utf-8")
                .body("{\n" +
                        "   \"touser\" : \"@all\",\n" +
                        "   \"msgtype\" : \"text\",\n" +
                        "   \"agentid\" : 1000002,\n" +
                        "   \"text\" : {\n" +
                        "       \"content\" : \"服务端自动化测试发送百度链接。\\n百度链接为：<a href=\\\"http://www.baidu.com\\\">百度一下</a>，点击前请将设备联网。\"\n" +
                        "   },\n" +
                        "}\n")
                .post("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + accessTaken)
                .then()
                .log()
                .all();
    }

    /**
     * 通过queryParam()方法并将参数存放在方法里面
     * 注意URL后面不能有 ？结尾，否则会报错：Request URI cannot end with ?
     */
    @Test
    void postMethodAndSetQueryParam(){

        given()
                .contentType("application/json;charset=utf-8")
                .body("{\n" +
                        "   \"touser\" : \"@all\",\n" +
                        "   \"msgtype\" : \"text\",\n" +
                        "   \"agentid\" : 1000002,\n" +
                        "   \"text\" : {\n" +
                        "       \"content\" : \"服务端自动化测试发送百度链接。\\n百度链接为：<a href=\\\"http://www.baidu.com\\\">百度一下</a>，点击前请将设备联网。\"\n" +
                        "   },\n" +
                        "}\n")
                .queryParam("access_token", accessTaken)
                .post("https://qyapi.weixin.qq.com/cgi-bin/message/send")
                .then()
                .log()
                .all();
    }

    /**
     * 下面两个方法对比将body里面的内容参数化
     */
    @Test
    public void testPostMethodForBody(){
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\"a\": 1,\n" +
                        "\"b\": \"testerhome\",\n" +
                        "\"array\": [\"111\", \"222\"]\n" +
                        "}")
                .when()
                .post("https://www.baidu.com")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void testPostMethodForJsonParams(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("a", "1");
        map.put("b", "testerhome");
        map.put("array", new String[] {"111", "222"});

        given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("https://www.baidu.com")
                .then()
                .log()
                .all()
                //.statusCode(200).extract().response().path("", "");
                .statusCode(200).body(hasXPath("//*[@hert='xxx']"));
    }

}