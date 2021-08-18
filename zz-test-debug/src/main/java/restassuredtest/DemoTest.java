package restassuredtest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

/**
 * @ClassName DemoTest
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/7/20 19:46
 **/
public class DemoTest {

    @Test
    void fun(){
        /**
         * rest-assured 语法糖结构
         */
        given()
                .get("https://www.baidu.com")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

}