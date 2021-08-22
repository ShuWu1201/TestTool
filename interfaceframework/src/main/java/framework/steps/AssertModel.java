package framework.steps;

import io.restassured.response.Response;
import lombok.Data;

/**
 * @ClassName AssertModel
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/19 14:34
 **/
@Data
public class AssertModel {

    private String actual;
    private String matcher;
    private String expect;
    private String reason;

    public void run(Response response){

    }

}