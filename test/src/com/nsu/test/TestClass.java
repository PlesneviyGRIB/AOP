package com.nsu.test;

import com.nsu.aop.annotations.After;
import com.nsu.aop.annotations.Aspect;
import com.nsu.aop.annotations.Before;

import java.util.Date;

@Aspect
public class TestClass {
    private static long initialTime;

    public void someMethod(){
        System.out.println("Some method");
    }

    public static void someMethod(String str) {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("Some method with args: " + str);
    }

    @Before("execution(static * *someMethod(String))")
    public static void loggingAdvice0(){
        initialTime = System.currentTimeMillis();
        System.out.println("Initial time: " + new Date(initialTime));
    }

    @After("execution(static * *someMethod(String))")
    public static void loggingAdvice1(){
        long currentTime = System.currentTimeMillis();
        System.out.println("DELTA: " + new Date(currentTime));
        System.out.println("Final time: " + (currentTime-initialTime));
    }
}