package mock.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * @ClassName WireMockDemoTest
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/15 15:47
 **/
public class WireMockDemoTest {

    static WireMockServer wireMockServer;

    @BeforeAll
    public static void initData(){
        wireMockServer = new WireMockServer(wireMockConfig().port(8089));
        wireMockServer.start();
        configureFor("localhost", 8089);
    }


    /**
     * 1、桩：即给一个固定路由返回一个固定内容就是桩的意思
     * 可以使用curl进行访问下面的url
     * curl命令：curl -H "Accept: text/xml"  localhost:8089/my/resource
     */
    @Test
    public void stubMock() {
        stubFor(get(urlEqualTo("/my/resource"))
                .withHeader("Accept", containing("text/xml"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>SUCCESS--验证成功</response>")));
        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同一个url请求返回不同的body部分
     */
    @Test
    public void easy_mock() {
        try {
            stubFor(get(urlEqualTo("/my/resource"))
                    .withHeader("Accept", containing("text/xml"))
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "text/xml")
                            .withBody("<response>SUCCESS--验证成功</response>")));
            Thread.sleep(10000);

            /**
             *
             */
            reset();

            stubFor(get(urlEqualTo("/my/resource"))
                    .withHeader("Accept", containing("text/xml"))
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "text/xml")
                            .withBody("<response>SUCCESS--验证reset方法</response>")));
            Thread.sleep(50000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2、反向代理：获得任意地址时从第三方网站来进行提取
     * 通过改变优先级改变将本地resources里面的mock.json文件的内容当作body部分返回给客户端
     */
    @Test
    public void proxyMockTest(){
        // Low priority catch-all proxies to otherhost.com by default
        stubFor(get(urlMatching(".*")).atPriority(10)
                .willReturn(aResponse().proxiedFrom("https://www.baidu.com/")));

        try {
            // High priority stub will send a Service Unavailable response
            // if the specified URL is requested
            /**
             * 当匹配到该地址时给一个高的优先级，然后返回一个指定内容
             */
            stubFor(get(urlEqualTo("/home/pcweb/submit/mancardmenu")).atPriority(10)
                    .willReturn(aResponse().withBody(Files.readAllBytes(Paths.get(WireMockDemoTest.class.getResource("/mock.json").getPath())))));

            Thread.sleep(500000);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}