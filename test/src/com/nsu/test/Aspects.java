package com.nsu.test;

import com.nsu.aop.annotations.*;
import com.nsu.aop.interfaces.ProceedingJoinPoint;

@Aspect
public class Aspects {

    // Entry point of Application also can be introspected (Main.main() in our case)
    @Before("execution(static void *main(..))")
    public static void beforeMain(){
        System.out.println("Before main method execution");
    }

    @Before("Pointcuts.callEveryMethodWithinMainClass")
    public static void logger(){
        System.out.println("Write logs before method call");
    }

    @Around("execution(static Integer *(Integer))")
    public static Object aroundAdvice0(ProceedingJoinPoint proceedingJoinPoint) throws Exception{

        Object[] args = proceedingJoinPoint.getArgs();
        args[0] = (Integer)args[0] + 100;

        return proceedingJoinPoint.invoke(args);
    }

    @Around("execution(static Integer *(Integer))")
    public static Object aroundAdvice1(ProceedingJoinPoint proceedingJoinPoint) throws Exception{

        Object[] args = proceedingJoinPoint.getArgs();
        args[0] = (Integer)args[0] + 500;

        return proceedingJoinPoint.invoke(args);
    }

    @AfterThrowing("execution(void *(..))")
    public static void exceptionLogger(){
        System.out.println("Exception caught!");
    }

    @Before("Pointcuts.cflowExample")
    public static void cflowLogger(){
        System.out.println("Test cflow: Before method");
    }
}