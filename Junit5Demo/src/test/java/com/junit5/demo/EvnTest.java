package com.junit5.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvnTest {
    @Test
    public void envTest(){
        assertEquals(3, 3, "实际值与期望结果不符！");
    }
}
