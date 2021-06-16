package com.testngdemo;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClassName FakerUtils
 * @Description 处理CSV文件的工具
 * @Author sfmewl
 * @Date 2021/6/16 07:31
 **/
public class FakerUtils {

    public static Object[][] getTestData(String fileName){
        String projectRoot = new File("").getAbsolutePath();
        String charset = "utf-8";
        ArrayList<String[]> list = new ArrayList<>();
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new InputStreamReader(new FileInputStream(new File(projectRoot+fileName)), charset)))){
            /**
             * 第一行一般为标题，跳过
             */
            csvReader.skip(1);

            /**
             * 遍历每一行，将数据添加到list中
             */
            Iterator<String[]> iterator = csvReader.iterator();
            while (iterator.hasNext()){
                list.add(iterator.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 再将数据放到一个二维数组中
         */
        Object data[][] = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            data[i] = list.get(i);
        }
        return data;
    }

}