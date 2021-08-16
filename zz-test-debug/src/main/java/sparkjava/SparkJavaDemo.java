package sparkjava;

import static spark.Spark.get;

/**
 * @ClassName SparkJavaDemo
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/16 15:28
 **/
public class SparkJavaDemo {
    /**
     * 最简单的restful，可以使用 http://localhost:4567/hello 进行访问
     * @param args
     */
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");

    }
}