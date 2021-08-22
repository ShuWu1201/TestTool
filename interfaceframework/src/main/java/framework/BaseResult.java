package framework;

import io.restassured.response.Response;
import lombok.Data;

/**
 * @ClassName BaseResult
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/19 16:19
 **/
@Data
public class BaseResult {

    private Response response;

}