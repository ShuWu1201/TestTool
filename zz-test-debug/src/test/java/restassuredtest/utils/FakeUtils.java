package restassuredtest.utils;

import org.junit.jupiter.api.Test;

/**
 * @ClassName FakeUtils
 * @Description 数据伪造工具类
 * @Author sfmewl
 * @Date 2021/8/17 17:16
 **/
public class FakeUtils {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getTel());
        }
    }

    /**
     * 获取固定长度的随机数，区间位[start, end)
     * @param length
     * @return
     */
    @Test
    public static int getRandomInt(int length){
        length = length - 1;
        int randomInt = (int) ((Math.random() * 9 + 1) * Math.pow(10, (double) length));
        return randomInt;
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static String getTimeStamp(){
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 获取一个随机数[start]
     * @param start
     * @param end
     * @return
     */
    public static int getNum(int start, int end){
        return (int) (Math.random() * (end - start) + start);
    }

    /**
     * 获取一个14位的随机数
     * @return
     */
    public static String orderNo(){
        String cardNo = "123456";
        for (int i = 0; i < 8; i++){
            cardNo += getNum(0, 9);
        }
        return cardNo;
    }

    /**
     * 电话号码生成器
     * @return
     */
    private static String[] telFirst = {
            "133", "149", "153", "173", "177",
            "180", "181", "189", "199", "130", "131", "132",
            "145", "155", "156", "166", "171", "175", "176", "185", "186", "166", "134", "135",
            "136", "137", "138", "139", "147", "150", "151", "152", "157", "158", "159", "172",
            "178", "182", "183", "184", "187", "188", "198", "170", "171"
    };

    /**
     * 获取11位电话号码
     * @return
     */
    public static String getTel(){
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }

}