package com.nsu.test;

import com.nsu.aop.annotations.Around;

public class Main {
    public static void main(String[] args) {
        new TestClass();
        new MyPointcuts();
    }

    @Around("as")
    public static void methodForTest(){
        System.out.println("methodForTest");
    }
    
}
