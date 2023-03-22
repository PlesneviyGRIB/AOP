package com.nsu.test;

import com.nsu.aop.annotations.After;
import com.nsu.aop.annotations.Around;
import com.nsu.aop.annotations.Aspect;
import com.nsu.aop.annotations.Before;
import com.nsu.aop.models.DynamicMethodInvocation;

@Aspect
public class TestClass1 {

    public void simple(){
        System.out.println("SIMPLE " + "TestClass1");
    }

//    @Around("execution(* *mutateString(..))")
//    public static Object loggingAdvice1(DynamicMethodInvocation dynamicMethodInvocation) {
//        try {
//            System.out.println("INSIDE AROUND");
//            return dynamicMethodInvocation.invoke(dynamicMethodInvocation.getArgs());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Around("execution(* *mutateString(..))")
//    public static Object loggingAdvice2(DynamicMethodInvocation dynamicMethodInvocation) {
//        try {
//            return dynamicMethodInvocation.invoke();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Before("execution(* *mutateString(..))")
////    @Before("within(*)")
//    public static void loggingAdvice1(){
//        System.out.println("EXECUTION");
//    }
////
//    @Before("call(* *mutateString(..))")
////    @Before("within(*)")
//    public static void loggingAdvice0(){
//        System.out.println("CALL");
//    }
//
//    @After("execution(* *mutateString(..))")
//    public static void loggingAdvice5(){
//        System.out.println("After EXECUTION");
//    }
//
//    @After("call(* *mutateString(..))")
//    public static void loggingAdvice4(){
//        System.out.println("After CALL");
//    }
}