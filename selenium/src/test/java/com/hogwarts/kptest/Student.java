package com.hogwarts.kptest;

/**
 * @ClassName Student
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/28 09:06
 **/
public class Student extends People{

    public Student() {
        //super();
        System.out.println("Student Constructor");
    }

    public Student(Long cardId) {
        super(cardId);
        System.out.println("Student Constructor 参数：cardId");
    }
}