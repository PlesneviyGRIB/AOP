package com.nsu.test;

import com.nsu.aop.annotations.*;
import com.nsu.aop.interfaces.ProceedingJoinPoint;

@Aspect
public class Main {
    public static void main(String[] args) throws Exception {
        new TestClass().A();
    }

    @Cflow("call(* *(..)) && within(com.nsu.test.TestClass)")
    public static void loggingCflow(){}
    @Before("Main.loggingCflow")
    public static void Cflow(){
        System.out.println("Before");
    }
}
