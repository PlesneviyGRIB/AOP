package com.nsu.test;

import com.nsu.aop.annotations.*;
import com.nsu.aop.interfaces.ProceedingJoinPoint;

@Aspect
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(new TestClass().simple(0));
    }

    @AfterThrowing("execution(* *(..)) && within(com.nsu.test.TestClass)")
    public static void log(){
        System.out.println("AfterThrowing");
    }

    @After("execution(* *(..)) && within(com.nsu.test.TestClass)")
    public static void log1(){
        System.out.println("After");
    }

    @Finally("call(* *(..)) && within(com.nsu.test.TestClass)")
    public static void log2(){
        System.out.println("Finally");
    }

    @Around("execution(* *(..)) && within(com.nsu.test.TestClass)")
    public static Object loggingAdvice0(ProceedingJoinPoint proceedingJoinPoint) throws Exception{
        System.out.println("AROUND 1");

        Object[] args = proceedingJoinPoint.getArgs();

        args[0] = (Integer)args[0] + 70;

        return proceedingJoinPoint.invoke(args);
    }

    @Around("execution(* *(..)) && within(com.nsu.test.TestClass)")
    public static Object loggingAdvice10(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
        System.out.println("AROUND 2");

        proceedingJoinPoint.getExecutedObject();

        Object[] args = proceedingJoinPoint.getArgs();

        args[0] = (Integer)args[0] + 10;

        return proceedingJoinPoint.invoke(args);
    }

    @Around("execution(* *(..)) && within(com.nsu.test.TestClass)")
    public static Object loggingAdvice11(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
        System.out.println("AROUND 2");

        proceedingJoinPoint.getExecutedObject();

        Object[] args = proceedingJoinPoint.getArgs();

        args[0] = (Integer)args[0] + 100;

        return proceedingJoinPoint.invoke(args);
    }

    @Before("execution(* *(..)) && within(com.nsu.test.TestClass)")
    public static void loggingAdvice1(){
        System.out.println("Before execution");
    }

    @Before("call(* com.nsu.test.TestClass.simple(..))")
    public static void loggingAdvice4(){
        System.out.println("Before call");
    }

    @After("execution(* *(..)) && within(com.nsu.test.TestClass)")
    public static void loggingAdvice2(){
        System.out.println("After execution");
    }
}
