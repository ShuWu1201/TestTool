package restassuredtest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

/**
 * @ClassName GetTest
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/16 16:59
 **/
public class GetTest {

    public static String access_token;

    /**
     * 注意使用params时URL后面不能有 ？结尾，否则会报错：Request URI cannot end with ?
     */
    @BeforeAll
    public static void getMethod(){
        access_token = given()
                .params("corpid", "wwc13e1f3c6a12d7aa", "corpsecret", "QEXUPIltwraEpXo1lM0Q6S42Xw-i3Kogp5ue5VajiSg")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .log()
                .all()
                .extract()
                .response()
                .path("access_token");

        System.out.println(access_token);
    }

    @Test
    void postMethod(){
        given()
                .contentType("application/json;charset=utf-8")
                .body("{\n" +
                        "   \"touser\" : \"@all\",\n" +
                        "   \"msgtype\" : \"text\",\n" +
                        "   \"agentid\" : 1000002,\n" +
                        "   \"text\" : {\n" +
                        "       \"content\" : \"不懂的问题。\\n请点击<a href=\\\"http://www.baidu.com\\\">链接</a>搜索。\"\n" +
                        "   },\n" +
                        "}\n")
                .queryParam("access_token", access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/message/send")
                .then()
                .log()
                .all();
    }
}