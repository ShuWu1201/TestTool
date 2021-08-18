package restassuredtest.department;

import io.restassured.response.Response;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @ClassName Demo_02_Separate
 * @Description
 * 1、基础脚本：执行创建、查询、修改、删除脚本
 * 2、方法间进行解藕、可以独立允许（如修改脚本可以独立运行）
 * 3、优化：通过清理历史数据的方式，保证每次运行环境一致
 *
 * @Author sfmewl
 * @Date 2021/8/17 16:54
 **/
public class Demo_03_02_repeat_envclear {
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

        clearDepartment();
    }

    @AfterAll
    public static void tearDown(){
        clearDepartment();
    }


    void createDepartment(){

        logger.info(accessToken);

        String createBody = "{\n" +
                "   \"name\": \"创建后部门neme\",\n" +
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


        System.out.println("createResponse.path(\"errcode\"):" + createResponse.path("errcode").toString());

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

        /**
         * 单个方法解藕，即在单个方法内部执行上游创建部门方法
         */
        createDepartment();

        System.out.println("departmentId:" + departmentId);

        String updateBody = "{\n" +
                "   \"name\": \"修改后部门neme\",\n" +
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

    @Test
    public static void clearDepartment(){

        Response listResponse = given().log().all()
                .when()
                .param("id", 0)
                .param("access_token", accessToken)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then()
                .log().body()
                .extract()
                .response();

        /**
         * 从根部门id获取到所有子部门id，并加入到一个list里面
         */
        ArrayList<Integer> departmentIdList = listResponse.path("department.id");

        System.out.println("--------------------------------");

        departmentIdList.forEach(x -> System.out.println(x));

        System.out.println("--------------------------------");

        for (int departmentId: departmentIdList) {
            /**
             * 删除除跟部门外所有子部门
             */
            if (departmentId == 1){
                continue;
            }else {
                deleteDepartment(departmentId);
            }
        }

    }

    /**
     * 删除部门
     * @param departmentId
     */
    public static void deleteDepartment(int departmentId){
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

    /**
     * 获取部门成员信息，但是由于没有关联企业无法拼凑出department_id参数导致无法查询部门下是否有成员
     * @param departmentId
     */
    //@Test
    void getDepartmentMembers(int departmentId){

        Response getDepartmentMembersRespose = given().log().all()
                .queryParam("access_token", accessToken)
                .body("{\n" +
                        "   \"department_id\": \"1\",\n" +
                        "   \"fetch_child\": 0\n" +
                        "}")
                .post("https://qyapi.weixin.qq.com/cgi-bin/linkedcorp/user/list")
                .then().log().all()
                .extract().response();

    }

    @Ignore
    void testMetho(){
         getDepartmentMembers(60005);
    }

}