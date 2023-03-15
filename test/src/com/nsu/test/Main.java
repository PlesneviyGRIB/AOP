package com.nsu.test;

import com.nsu.aop.annotations.Around;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        new TestClass().someMethod();
        methodForTest();
    }

    public static void methodForTest(){
        System.out.println("methodForTest");
    }
}
