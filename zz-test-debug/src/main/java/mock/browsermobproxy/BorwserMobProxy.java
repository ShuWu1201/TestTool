package mock.browsermobproxy;

import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName BorwserMobProxy
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/16 08:57
 **/
public class BorwserMobProxy {

    @Test
    public void bmp() throws IOException {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(8080);
        int port = proxy.getPort(); // get the JVM-assigned port
        // Selenium or HTTP client configuration goes here

        proxy.addResponseFilter(new ResponseFilter() {
            @Override
            public void filterResponse(HttpResponse response, HttpMessageContents contents, HttpMessageInfo messageInfo) {
                if (messageInfo.getOriginalUrl().contains(".json")) {
                    // todo: json -> hashmap -> rescue -> 新的hashmap -> json

                    /**
                     * "\"[^\"]*\""：下面语句会将所有json的值的key和value替换成null
                     * curl -k https://ceshiren.com/categories.json -x http://localhost:8080
                     */
                    String contentsNew = contents.getTextContents().replaceAll("\"[^\"]*\"", "null");// 把所有值改为null
                    contents.setTextContents(contentsNew);
                    //contents.setTextContents("This message body will appear in all responses!");
                }
            }
        });

        proxy.addRequestFilter(((httpRequest, httpMessageContents, httpMessageInfo) -> {
            /**
             * curl -k https://ceshiren.com/categories.json -x http://localhost:8080
             * 将uri设置为 / 后，请求将返回首页的内容
             */
            httpRequest.setUri("/");
            return null;
        }));

        System.in.read();
    }

}