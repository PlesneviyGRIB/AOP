package com.nsu.test;

import com.nsu.aop.annotations.Around;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        TestClass.loggingAdvice0();
        TestClass.someMethod("STRING ARG");
        TestClass.loggingAdvice1();
    }
}
