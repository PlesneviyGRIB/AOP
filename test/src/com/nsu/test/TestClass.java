package com.nsu.test;

import com.nsu.aop.annotations.After;
import com.nsu.aop.annotations.Around;
import com.nsu.aop.annotations.Aspect;
import com.nsu.aop.annotations.Before;
import com.nsu.aop.models.DynamicMethodInvocation;

@Aspect
public class TestClass {
    public void simple(){
        System.out.println("SIMPLE " + "TestClass");
    }
}