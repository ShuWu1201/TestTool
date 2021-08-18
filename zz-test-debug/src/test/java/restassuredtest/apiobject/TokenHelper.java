package restassuredtest.apiobject;

import static io.restassured.RestAssured.given;

/**
 * @ClassName TokenHelper
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/17 23:42
 **/
public class TokenHelper {

    public static String getAccessToken(){
        String accessToken = given().log().all()
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

        return accessToken;
    }

}