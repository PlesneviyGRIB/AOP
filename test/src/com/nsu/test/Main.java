package com.nsu.test;

import com.nsu.aop.annotations.Aspect;
import com.nsu.aop.annotations.Before;

@Aspect
public class Main {
    public static void main(String[] args) {
        System.out.println("main");
        new TestClass().simple();
        new TestClass1().simple();
    }

    @Before("execution(* *simple(..)) && within(com.nsu.test.TestClass1)")
    //@Before("call(* *simple(..))")
    public static void loggingAdvice0(){
        System.out.println("EXECUTION");
    }
}
