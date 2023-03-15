package com.nsu.test;

import com.nsu.aop.annotations.Around;
import com.nsu.aop.annotations.Aspect;
import com.nsu.aop.annotations.Before;

@Aspect
public class TestClass {
    public void someMethod(){
        System.out.println("Some method");
    }

    public void someMethod1(String str, Integer num){
        System.out.println("Some method1");
    }

    @Before("MyPointcuts.loggingJoinPoint")
    public void loggingAdvice(){
        System.out.println("-----log-----");
    }

    @Around("execution(* *(..))")
    public void loggingAdvice1(){
        System.out.println("-----log-----");
    }
}