package restassuredtest.department;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import restassuredtest.apiobject.DepartmentObject;
import restassuredtest.apiobject.TokenHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @ClassName Demo_02_Separate
 * @Description
 * 1、基础脚本：执行创建、查询、修改、删除脚本
 * 2、方法间进行解藕、可以独立允许（如修改脚本可以独立运行）
 * 3、优化：通过清理历史数据的方式，保证每次运行环境一致
 * 4、优化：对脚本进行了分层，减少了重复代码，提高了代码复用率和可维护性
 * 5、优化：覆盖不同入参组合，以数据驱动的方式将入参从代码里进行剥离
 *
 * @Author sfmewl
 * @Date 2021/8/17 16:54
 **/
public class Demo_05_param {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_Base.class);
    static String accessToken;
    static Integer departmentId;
    private static final Integer departmentRootId = 1;


    /**
     * @BeforeAll 作用
     * 1、获取登录态的信息如accessToken
     * 2、清除之前的测试数据防止后续脚本执行失败
     */
    @BeforeAll
    public static void initTest(){
        accessToken = TokenHelper.getAccessToken();
        DepartmentObject.clearDepartment(accessToken, departmentRootId);
    }

    //@AfterAll
    public static void tearDown(){
        DepartmentObject.clearDepartment(accessToken, departmentRootId);
    }

    @DisplayName("创建部门")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/createDepartment.csv", numLinesToSkip = 1)
    void createDepartment(String createName, String createEnName, String returnCode){

        Response createResponse = DepartmentObject.createDepartment(accessToken, createName, createEnName, departmentRootId);

        System.out.println("--------------------------");
        System.out.println(createResponse.path("errcode").toString());
        System.out.println("--------------------------");

        assertEquals(Integer.valueOf(createResponse.path("errcode").toString()), Integer.valueOf(returnCode));

//        if (createResponse.path("errcode").toString().equals(returnCode)) {
//            departmentId = createResponse.path("id");
//        }else {
//            logger.info(createResponse.path("errcode").toString());
//        }

    }

//    @DisplayName("修改部门")
//    @Test
//    @Order(2)
//    void updateDepartment(){
//
//        /**
//         * 单个方法解藕，即在单个方法内部执行上游创建部门方法
//         */
//        createDepartment();
//
//        System.out.println("departmentId:" + departmentId);
//
//
//        Response updateResponse = DepartmentObject.updateDepartment(accessToken, "修改后部门neme", "修改后部门EnNeme", departmentRootId, departmentId);
//
//        assertEquals("0", updateResponse.path("errcode").toString());
//
//    }
//
//    @DisplayName("查询部门")
//    @Test
//    @Order(3)
//    void listDepartment(){
//        Response listResponse = DepartmentObject.listDepartment(accessToken, departmentRootId);
//        assertEquals("0", listResponse.path("errcode").toString());
//    }
//
//
//    /**
//     * 删除部门
//     */
//    @DisplayName("删除部门")
//    @Test
//    @Order(4)
//    void deleteDepartment(){
//        Response deleteResponse = DepartmentObject.deleteDepartment(accessToken, departmentId);
//
//        assertEquals("0", deleteResponse.path("errcode").toString());
//    }

}