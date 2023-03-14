package com.nsu.test;

import com.nsu.aop.annotations.Around;

public class Main {
    public static void main(String[] args) {
        new TestClass();
        new MyPointcuts();
    }
    
    public String someMethod(){
      return "HELLO WORLD";
    }

    @Around("as")
    public static void methodForTest(){
        System.out.println("methodForTest");
    }
    
}
