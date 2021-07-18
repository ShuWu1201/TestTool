package com.hogwarts.kptest;

import java.util.ArrayList;

/**
 * @ClassName Test
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/28 09:06
 **/
public class Test {

    @org.junit.jupiter.api.Test
    public void testStudent(){
        Student student = new Student();
        System.out.println("--------------");
        Student student1 = new Student(52353452345L);
    }


    @org.junit.jupiter.api.Test
    public void testTryFinally(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("apple");
        arrayList.add("banana");
        arrayList.add("orange");

        try {
            arrayList.forEach(data -> {
                System.out.println(data);
            });
        } finally {
            System.out.println("finally!");
        }
    }


    /**
     * 使用 . 切割字符串的问题
     */
    @org.junit.jupiter.api.Test
    public void testStringSplit(){
        String string = "apple.banana.arange";
        // split的参数是一个regex，是一个正则表达式，而.代表所有，所以使用.切割字符串时需要使用转义字符
        String[] strings = string.split("\\.");

        for (String s: strings) {
            System.out.println(s);
        }

    }

    /**
     * 父类无法强制转换成子类
     */
    @org.junit.jupiter.api.Test
    public void testFatherCastChild(){
//        People people = new People();
//        Student student = (Student) people;
//        System.out.println(student);
    }

}