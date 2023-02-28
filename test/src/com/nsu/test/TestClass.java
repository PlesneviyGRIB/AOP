package com.nsu.test;

import com.nsu.aop.annotations.Aspect;
import com.nsu.aop.annotations.Before;

@Aspect
public class TestClass {
    @Before("MyPointcuts.loggingPointcut")
    public void loggingAdvice(){
        System.out.println("-----log-----");
    }
}