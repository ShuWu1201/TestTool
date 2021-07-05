package com.hogwarts.kptest;

/**
 * @ClassName People
 * @Description TODO
 * @Author sfmewl
 * @Date 2021/6/28 09:04
 **/
public class People {
    private Long  cardId;

    public People() {
        System.out.println("People Constructor");
    }

    public People(Long cardId) {
        this.cardId = cardId;
        System.out.println("People Constructor 参数：cardId");
    }
}