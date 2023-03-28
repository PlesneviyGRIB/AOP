package com.nsu.test;

import com.nsu.aop.annotations.*;

@Aspect
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("+++++ MAIN starts");

        new TestClass().a();

        Integer result = TestClass1.increment(0);
        System.out.println("Result: " + result);

        try {
            new TestClass1().exception();
        } catch (Exception e){
            System.out.println(e);
        }

        System.out.println("+++++ MAIN ends");
    }
}
