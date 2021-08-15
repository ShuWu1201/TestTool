package debug;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

/**
 * @ClassName debug.TestDemo
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/7/20 09:19
 **/
public class TestDemo {

    HttpClient httpClient;
    @Test
    void getMethodTest() throws IOException {
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        System.out.println(EntityUtils.toString(entity1, "utf-8"));
    }

    @Test
    void restassuredGetMethod(){
        given().get("").then().log().all();
    }

    @Test
    void test(){
        System.out.println("hhhhhh");
    }


    /**
     * - Collection体系的结合可以使用默认方法stream()生成流
     *   default Stream<E> stream()
     * - Map体系的接口间接生成流
     * - 数组可以通过Stream接口的静态方法of(T... valuse)生成流
     */
    @Test
    void streamGenerate(){
        // Collection体系的结合可以使用默认方法stream()生成流
        List<String> list = new ArrayList<String>();
        Stream<String> listStream = list.stream();

        Set<String> set = new HashSet<String>();
        Stream<String> setStream = set.stream();

        // Map体系的接口间接生成流
        Map<String, Integer> map = new HashMap<String, Integer>();
        Stream<String> keyStream = map.keySet().stream();
        Stream<Integer> valueStream = map.values().stream();
        Stream<Map.Entry<String, Integer>> entryStream = map.entrySet().stream();

        // 数组可以通过Stream接口的静态方法of(T... valuse)生成流
        String[] strArray = {"hello", "world", "java"};
        Stream<String> strArray1 = Stream.of(strArray);

        Stream<String> stringStream = Stream.of("hello", "world", "java");
        Stream<Integer> integerStream = Stream.of(10, 20, 30);

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("林青霞");
        arrayList.add("张曼玉");
        arrayList.add("王祖贤");
        arrayList.add("柳岩");
        arrayList.add("张敏");
        arrayList.add("张无忌");

        // 需求1：把arrayList集合中以"张"开头的元素在控制台输出
//        arrayList.stream().filter((String s) -> {
//            return s.startsWith("张");
//        }).forEach(System.out::println);

        arrayList.stream().filter(s -> s.startsWith("张")).forEach(System.out::println);
        System.out.println("----------------------");
        // 需求2：把arrayList集合中长度为3的元素在控制台输出
        arrayList.stream().filter(s -> s.length() == 3).forEach(System.out::println);
        System.out.println("----------------------");

        // 需求3：把arrayList集合中以"张"开头 且 长度为3的元素在控制台输出
        arrayList.stream().filter(s -> s.startsWith("张")).filter(s -> s.length() == 3).forEach(System.out::println);
        System.out.println("----------------------");

        // 需求4：把arrayList集合中前3个元素在控制台输出
        arrayList.stream().limit(3).forEach(System.out::println);
        System.out.println("----------------------");

        // 需求5：把arrayList集合中的元素跳过前3个后的元素在控制台输出
        arrayList.stream().skip(3).forEach(System.out::println);
        System.out.println("----------------------");

        // 需求6：把arrayList集合中的元素先跳过2个元素后，再输出余下的前3个元素
        arrayList.stream().skip(2).limit(3).forEach(System.out::println);
        System.out.println("----------------------");

        // 需求7：把arrayList集合中的元素取前4个组成一个流
        Stream<String> s1 = arrayList.stream().limit(4);
        System.out.println("----------------------");

        // 需求8：把arrayList集合中的元素跳过前2个后组成一个流
        Stream<String> s2 = arrayList.stream().skip(2);
        System.out.println("----------------------");

        // 需求9：合并7、8得到的流并输出
        Stream.concat(s1, s2).forEach(System.out::println);
        System.out.println("----------------------");

        // 需求10：合并7、8得到的流并输出且要求元素不能重复
        Stream.concat(s1, s2).distinct().forEach(System.out::println);
        System.out.println("----------------------");

        // 需求11：将元素按字母顺序将流数据输出
        arrayList.stream().sorted().forEach(System.out::println);

        // 需求12：将元素按字符串长度将流数据输出
        arrayList.stream().sorted((s3, s4) ->{
            int num = s3.length() - s4.length();
            int num2 = num == 0? s3.compareTo(s4): num;
            return num2;
        }).forEach(System.out::println);

    }

}