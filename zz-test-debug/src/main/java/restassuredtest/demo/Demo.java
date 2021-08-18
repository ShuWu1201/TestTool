package restassuredtest.demo;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * @ClassName Demo
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/16 21:14
 **/
public class Demo {

    @Test
    public void testJson(){
        /**
         * 访问链接，并判断返回值中某个字段的值是否与预期一致
         */
        given()
                .when()
                .get("http://127.0.0.1:8000/testerhome.json")
                .then()
                .body("store.book.category", hasItems("reference"))
                .body("store.book[0].author", equalTo("Nigel Rees"))
                .body("store.book.findAll { book -> book.price == 8.95f }.price", notNullValue()) //数字如果时小数一定要在后面加f
                .body("store.book.find { book -> book.price == 8.95f }.title", equalTo("Sayings of the Century"))
                ;
    }

    @Test
    public void testXML(){
        given()
                .when()
                .get("http://127.0.0.1:8000/testerhome.xml")
                .then()
                .body("shopping.category[0].item[0].name", equalTo("Chocolate"))
                .body("shopping.category[0].item.size()", equalTo(2))
                .body("shopping.category.item.size()", equalTo(5))
                // <category type="groceries"> 里面的属性要使用@
                .body("shopping.category.findAll { it.@type == 'groceries' }.size()", equalTo(1))
                // 标签<price>不使用@
                .body("shopping.category.item.findAll { it.price == 20 }.name", equalTo("Coffee"))
                // xmlpath里面支持 ** 深度搜索，JsonPaht不支持 **
                .body("**.findAll { it.price == 20 }.name", equalTo("Coffee"))
                ;
    }

}