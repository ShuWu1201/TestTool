package framework.utils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 替换工具类：配置文件或模板中的占位符的替换
 * Date: 15-5-8
 * Time: 下午4:12
 */
public class PlaceholderUtils {
    public static final Logger logger = LoggerFactory.getLogger(PlaceholderUtils.class);


    /**
     * Prefix for system property placeholders: "${"
     * 占位符的前缀
     */
    public static final String PLACEHOLDER_PREFIX = "${";
    /**
     * Suffix for system property placeholders: "}"
     * 占位符的后缀
     */
    public static final String PLACEHOLDER_SUFFIX = "}";

    /**
     * @param text API里面的形参数
     * @param parameter testcase里面的实参
     * @return
     */
    public static String resolveString(String text, Map<String, String> parameter) {
        if (parameter == null || parameter.isEmpty()||text == null || text.isEmpty()) {
            return text;
        }
        StringBuffer buf = new StringBuffer(text);
        /**
         * 计算变量名开始位置
         **/
        int startIndex = buf.indexOf(PLACEHOLDER_PREFIX);
        while (startIndex != -1) {
            /**
            * 计算变量名结束的位置
            **/
            int endIndex = buf.indexOf(PLACEHOLDER_SUFFIX, startIndex + PLACEHOLDER_PREFIX.length());
            if (endIndex != -1) {
                /**
                 * 取出要替换的变量名
                 **/
                String placeholder = buf.substring(startIndex + PLACEHOLDER_PREFIX.length(), endIndex);
                int nextIndex = endIndex + PLACEHOLDER_SUFFIX.length();
                try {
                    /**
                     * 取出变量map中的真实值
                     **/
                    String propVal = parameter.get(placeholder);
                    if (propVal != null) {
                        /**
                         * 替换变量
                         **/
                        buf.replace(startIndex, endIndex + PLACEHOLDER_SUFFIX.length(), propVal);
                        nextIndex = startIndex + propVal.length();
                    } else {
                        logger.info("Could not resolve placeholder '" + placeholder + "' in [" + text + "] ");
                    }
                } catch (Exception ex) {
                    logger.info("Could not resolve placeholder '" + placeholder + "' in [" + text + "]: " + ex);
                }
                startIndex = buf.indexOf(PLACEHOLDER_PREFIX, nextIndex);
            } else {
                startIndex = -1;
            }
        }
        return buf.toString();
    }

    public static ArrayList<String> resolveList(ArrayList<String> list, Map<String, String> parameter) {
        if (parameter == null || parameter.isEmpty() || list == null || list.isEmpty()) {
            return list;
        }
        ArrayList<String> retureList = new ArrayList<String>();
            list.forEach(str -> {
                if (str.contains(PLACEHOLDER_PREFIX)) {
                    retureList.add(resolveString(str, parameter));
                } else {
                    retureList.add(str);
                }
            });
        return retureList;
    }

    public static HashMap<String, String> resolveMap(HashMap<String, String> map, Map<String, String> parameter) {
        if (parameter == null || parameter.isEmpty() || map == null || map.isEmpty()) {
            return map;
        }
        HashMap<String, String> retureMap = new HashMap<String, String>();
            map.forEach((key, value) -> {
                if (value.contains(PLACEHOLDER_PREFIX)) {
                    retureMap.put(key, resolveString(value, parameter));

                }
            });
        return retureMap;
    }

    /**
     * 测试上述类的工具
     */
    @Test
    public void test(){
        String originalText = "corpid: ${corpid}\n" +
                "corpsecret: ${corpsecret}";

        ArrayList<String> originalList = new ArrayList<String>();
        originalList.add("corpid: ${corpid}");
        originalList.add("corpsecret: ${corpsecret}");


        HashMap<String, String> originalMap = new HashMap<String, String>();
        originalMap.put("corpid", "${corpid}");
        originalMap.put("corpsecret", "${corpsecret}");


        Map<String, String> replace = new HashMap<String, String>();
        replace.put("corpid", "666");
        replace.put("corpsecret", "777");

        System.out.println("resolveString:\n" + resolveString(originalText, replace));
        System.out.println("resolveList:" + resolveList(originalList, replace));
        System.out.println("resolveMap:" + resolveMap(originalMap, replace));

    }
}