package restassuredtest.apiobject;

import io.restassured.response.Response;
import restassuredtest.utils.FakeUtils;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @ClassName DepartmentObject
 * @Description 封装，封装的是逻辑，应该和数据无关，因此数据要作为参数传进来。后续可以在用例层对参数的驱动。
 * tips：因此该逻辑里面不应该有任何写死的参数
 * @Author sfmewl
 * @Date 2021/8/17 23:45
 **/
public class DepartmentObject {

    /**
     * 创建部门
     * @param accessToken
     * @param createName
     * @param createEnName
     * @param parentid
     * @return
     */
    public static Response createDepartment(String accessToken, String createName, String createEnName, Integer parentid){

        String createBody = "{\n" +
                "   \"name\": \"" + createName + "\",\n" +
                "   \"name_en\": \"" + createEnName + "\",\n" +
                "   \"parentid\": " + parentid + ",\n" +
                //"   \"order\": 1,\n" +
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
        // 返回一个Response完整的数据方便后面做断言
        return createResponse;

    }

    /**
     * 通过时间戳创建部门
     * @param accessToken
     * @param parentid
     * @return
     */
    public static Response createDepartmentByTimeStamp(String accessToken, Integer parentid){

        String createName = "创建name"+ FakeUtils.getTimeStamp();
        String createEnName = "创建en_name" + FakeUtils.getTimeStamp();

        return createDepartment(accessToken, createName, createEnName, parentid);
    }

    /**
     * 通过随机数创建部门（随机数不如时间戳好，因为随机数也可能重复）
     * @param accessToken
     * @param parentid
     * @return
     */
    public static Response createDepartmentByRandomInt(String accessToken, Integer parentid){

        String createName = "创建name"+ FakeUtils.getRandomInt(1000);
        String createEnName = "创建en_name" + FakeUtils.getRandomInt(1000);

        return createDepartment(accessToken, createName, createEnName, parentid);
    }


    /**
     * 更新部门
     * @param accessToken
     * @param updateName
     * @param updateEnName
     * @param parentid
     * @param departmentId
     * @return
     */
    public static Response updateDepartment(String accessToken, String updateName, String updateEnName, Integer parentid, Integer departmentId){

        String updateBody = "{\n" +
                "   \"name\": \"" + updateName +"\",\n" +
                "   \"name_en\": \"" + updateEnName + "\",\n" +
                "   \"parentid\": " + parentid + ",\n" +
                //"   \"order\": 1,\n" +
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

        return updateResponse;
    }

    /**
     * 删除部门
     * @param accessToken
     * @param departmentId
     * @return
     */
    public static Response deleteDepartment(String accessToken, Integer departmentId){
        Response deleteResponse = given().log().all()
                .contentType("application/json")
                .param("access_token", accessToken)
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().body()
                .extract()
                .response();

        return deleteResponse;
    }


    public static Response listDepartment(String accessToken, Integer departmentId){

        Response listResponse = given().log().all()
                .when()
                .param("id", departmentId)
                .param("access_token", accessToken)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then()
                .log().body()
                .extract()
                .response();

        return listResponse;

    }

    public static void clearDepartment(String accessToken, Integer departmentRootId){

        Response listResponse = listDepartment(accessToken, departmentRootId);

        /**
         * 从某个根部门id获取到所有子部门id，并加入到一个list里面
         */
        ArrayList<Integer> departmentIdList = listResponse.path("department.id");


        for (Integer departmentId: departmentIdList) {
            /**
             * 删除除跟部门外所有子部门
             */
            if (departmentId == 1){
                continue;
            }else {
                deleteDepartment(accessToken, departmentId);
            }
        }

    }

}