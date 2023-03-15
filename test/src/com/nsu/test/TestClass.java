    package com.nsu.test;

    import com.nsu.aop.annotations.After;
    import com.nsu.aop.annotations.Aspect;
    import com.nsu.aop.annotations.Before;
    import com.nsu.aop.annotations.Finally;

    @Aspect
public class TestClass {
    public void someMethod(){
        System.out.println("Some method");
        System.out.println("Some method1");
        System.out.println("Some method2");
    }
    @Before("MyPointcuts.loggingJoinPoint")
    public static void loggingAdvice(){
        System.out.println("BEFORE-----log-----");
    }

    @After("execution(* *someMethod(..))")
    public static void loggingAdvice2(){
        System.out.println("AFTER-----log-----");
    }
    @Finally("execution(* *someMethod(..))")
    public static void loggingAdvice3(){
        System.out.println("FINALLY-----log-----");
    }
}