package restassuredtest.demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

/**
 * @ClassName XueQiu
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/16 23:12
 **/
public class XueQiu {

    @Test
    public void testSearch(){
        // 信任https的任何证书
        useRelaxedHTTPSValidation();

        // 全局代理，每个接口都走代理
        RestAssured.proxy("127.0.0.1", 8888);

        // given开头表示输入数据
        given().log().all()
                // qurey请求
                .queryParam("code", "soso")
                // 头信息
                .header("Cookie","xq_a_token=afef3e11704c2f4dcf82884f93d0f0bc1979ec0a;xq_id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOjY3NTY1Mjk2MjgsImlzcyI6InVjIiwiZXhwIjoxNjMxNjAwNjExLCJjdG0iOjE2MjkwMDkwMTEwNjksImNpZCI6IldpQ2lteHBqNUgifQ.O5aTETOnPVbsbwGEQhGdUuNFeQlFv46rDb4UOolOF3lDhsjbr9aZXekDcUmBIxid19eyj5r09na0Nbx3VjqhYNLe8gi6ZHvU89u_6JgNI7EvoINZ7an8t0Us17xT0oFdld0ctZKU9fhVcS7mmN_bCL5N_ODITVs6cJE2hY3fSRosohwYTAgTsL5QB-6WYTJ2-zAc7j06Pz3iCWJ2dzYYccuASP1wSbTJDaskgWqyOICm10bWWkTW5BHLmG2gVaw8_SkNJrH3C3fynzE0Td9NIwlJM2k05btLKR4iAppubCTNypEPv-DB_myC77fG7q7Vk4XaMOsvMcAxiuQ2dcwPRQ;u=6756529628")
                // 表示触发条件
                .when()
                .get("https://xueqiu.com/stock/search.json")
                .then()
                .log().all()
                .statusCode(200)
                .body("stocks.code", hasItems("SOSO"))
                .body("stocks.name", hasItems("SOSO"))
                .body("stocks.find {it.code == 'SOSO'}.name", equalTo("SOSO"))
        ;
    }

    /**
     * 雪球登录接口
     */
    @Test
    public void testLogin(){

        useRelaxedHTTPSValidation();

        /**
         * 下面的请求和服务器的请求一致，如果有加密算法/或者不清楚的参数等需要和开发确认
         */
        Response response = given().proxy("127.0.0.1", 8888)
                .header("user-agent", "Xueqiu iPhone 12.41")
                // query参数里面有随机生成的字段，不需要加入到queryParam里面，否则可能会导致请求失败，具体要和开发确认
                .queryParam( "_t", "10091F31-258D-4C9F-8D7C-179364BF7B48.4339095202.1629133050563.1629133322121")
                .cookie("xq_a_token", "86b0ac61bc43ad878149f9462e6af5cb53240e28", "xq_id_token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOjQzMzkwOTUyMDIsImlzcyI6InVjIiwiZXhwIjoxNjMxNzI1MzIyLCJjdG0iOjE2MjkxMzMzMjIwNDMsImNpZCI6IldpQ2lteHBqNUgifQ.N6vTCIeZNm4-J94yN7U1WsZeRrgHK0TjiqwQo6gOpJ2qVyKt8SrZrWxjBWH5Zl-e_7Db-pIoB19aX1W1pyrk_iVgwSQCbfCscMXSqISUVl1EfFbNnb94k2mfOrvmWKyYqCp9MqGI3BCPS1fRc7FCLKCGp_14_OOypDopAYP7MF5er3J337p2dNRgWXKKjz04QenCF1bwRi45ShgOK2_QY1d2qpuUUKlWWC2OZy583z1TW1dZqIXCDD18v5MadzKG6FPm-25QWI8-e6WC5X_zdLPdz7xw5mJF1FCEcDIB-1uOpV_v5uc0KS6-Gzn8Fw8gH5ONeeIrb5MYwHsosZq6LA", "u", "4339095202")
                .formParam("client_id", "WiCimxpj5H")
                .formParam("client_secret", "TM69Da3uPkFzIdxpTEm6hp")
                .formParam("grant_type", "password")
                .formParam("s_id", "10091F31-258D-4C9F-8D7C-179364BF7B48")
                .formParam("sign", "e166459c2ddcdf47d17b5509ff2c26b7b57d186a")
                .formParam("type", 2)
                .when()
                .post("https://api.xueqiu.com/provider/oauth/token")
                .then()
                .statusCode(200)
                .body("uid", equalTo(-1))
                // extract 可以允许返回某个值给后面的接口使用（类似上下文共享参数）
                .extract().response()
                ;
        System.out.println("-----------------------------");
        System.out.println(response.path("access_token").toString());
    }

    @Test
    public void testPostJson(){
        HashMap<String, Object> hashMap = new HashMap<String,Object>();
        hashMap.put("a", 1);
        hashMap.put("b", 2);

        given()
                .contentType(ContentType.JSON)
                .body(hashMap)
                .when()
                .post("https://www.baidu.com")
                .then()
                .statusCode(200);
    }

}