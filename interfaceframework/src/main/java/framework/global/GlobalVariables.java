package framework.global;

import lombok.Data;

import java.util.HashMap;

/**
 * @ClassName GlobalVariables
 * @Description 全局变量，应该运行时都要可以获取到的变量
 * @Author sfmewl
 * @Date 2021/8/18 21:18
 **/
@Data
public class GlobalVariables {

    private static HashMap<String, String> globalVariables = new HashMap<>();

    public static HashMap<String, String> getGlobalVariables() {
        return globalVariables;
    }

    public static void setGlobalVariables(HashMap<String, String> globalVariables) {
        GlobalVariables.globalVariables = globalVariables;
    }
}
