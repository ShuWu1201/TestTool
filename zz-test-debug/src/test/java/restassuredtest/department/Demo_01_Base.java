package restassuredtest.department;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @ClassName Demo_01_Base
 * @Description 1、基础脚本：执行创建、查询、修改、删除脚本
 * @Author sfmewl
 * @Date 2021/8/17 14:41
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_01_Base {

    private static final Logger logger = LoggerFactory.getLogger(Demo_01_Base.class);
    static String accessToken;
    static String departmentId;


    /**
     * @BeforeAll 作用
     * 1、获取登录态的信息如accessToken
     * 2、清除之前的测试数据防止后续脚本执行失败
     */
    @BeforeAll
    public static void getAccessToken(){
        accessToken = given().log().all()
                .when()
                .param("corpid", "wwc13e1f3c6a12d7aa")
                .param("corpsecret", "AEr-bKcNeHwL0TocDbloAuIEGeyVvioxLQymHelhVjI")
                // 普通的secret
                //.param("corpsecret", "QEXUPIltwraEpXo1lM0Q6S42Xw-i3Kogp5ue5VajiSg")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .log()
                .all()
                .extract()
                .response()
                .path("access_token")
                ;
    }

    @DisplayName("创建部门")
    @Test
    @Order(1)
    void createDepartment(){

        logger.info(accessToken);

        String createBody = "{\n" +
                "   \"name\": \"create部门\",\n" +
                "   \"name_en\": \"RDGZ\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1,\n" +
                //"   \"id\": 9999\n" +
                "}\n";

        Response createResponse = given()
                .log()
                .all()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken)
                .then()
                .log()
                .all()
                .extract()
                .response();


        if (createResponse.path("errcode").toString().equals("0")) {
            departmentId = createResponse.path("id").toString();
        }else {
            logger.info(createResponse.path("errcode").toString());
        }

    }

    @DisplayName("修改部门")
    @Test
    @Order(2)
    void updateDepartment(){

        String updateBody = "{\n" +
                "   \"name\": \"update部门\",\n" +
                "   \"name_en\": \"RDGZ\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1,\n" +
                "   \"id\": " + departmentId + "\n" +
                "}\n";

        Response updateResponse = given().log().all()
                .contentType("application/json")
                .body(updateBody)
                .queryParam("access_token", accessToken)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().log().all()
                .extract()
                .response();

        assertEquals("0", updateResponse.path("errcode").toString());

    }

    @DisplayName("查询部门")
    @Test
    @Order(3)
    void listDepartment(){

        Response listResponse = given().log().all()
                .when()
                .param("id", 0)
                .param("access_token", accessToken)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then()
                .log().body()
                .extract()
                .response();

        assertEquals("0", listResponse.path("errcode").toString());

    }

    @DisplayName("删除部门")
    @Test
    @Order(4)
    void deleteDepartment(){
        Response deleteResponse = given().log().all()
                .contentType("application/json")
                .param("access_token", accessToken)
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().body()
                .extract()
                .response();

        assertEquals("0", deleteResponse.path("errcode").toString());
    }

}