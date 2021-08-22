package framework.steps;

import framework.BaseResult;
import lombok.Data;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @ClassName StepResult
 * @Description step执行结果对象
 * @Author sfmewl
 * @Date 2021/8/19 14:45
 **/
@Data
public class StepResult extends BaseResult {

    /**
     * 将软断言assertAll的中间状态存储为一个assertList，然后执行完逻辑代码再统一断言
     */
    private ArrayList<Executable> assertList;
    private HashMap<String, String> stepVariable = new HashMap<>();

}