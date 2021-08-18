package restassuredtest.task;

import restassuredtest.apiobject.DepartmentObject;

/**
 * @ClassName EnvClearHandler
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/18 10:01
 **/
public class EnvClearHandler {
    public static void clearHistoryCreateDepartmentByDepartmentRootId(String accessToken, Integer departmentRootId){
        DepartmentObject.clearDepartment(accessToken, departmentRootId);
    }
}