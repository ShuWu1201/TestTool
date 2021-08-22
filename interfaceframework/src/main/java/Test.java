import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @ClassName Test
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/8/18 21:24
 **/
public class Test {

    @org.junit.jupiter.api.Test
    void assertTest1(){
        assertAll("",
                ()->{assertThat(1,equalTo(2));},
                ()->{assertThat(1,equalTo(3));},
                ()->{assertThat(1,equalTo(4));}
        );
    }

    /**
     * 将上诉想要断言的内容存储起来一起断言，中间类型为Executable
     */
    @org.junit.jupiter.api.Test
    void assertTest2() {
        ArrayList<Executable> assertList = new ArrayList<Executable>();

        assertList.add(()->{assertThat(1,equalTo(2));});
        assertList.add(()->{assertThat(1,equalTo(3));});
        assertList.add(()->{assertThat("",1,equalTo(4));});
        assertAll("", assertList.stream());
    }

}