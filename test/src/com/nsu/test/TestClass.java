package com.nsu.test;

import com.nsu.aop.annotations.Aspect;

@Aspect
public class TestClass {
    public Integer simple(Integer i) throws Exception{
        //throw new Exception();
        System.out.println("+++++++++++++METHOD BODY+++++++++++++");
        System.out.println("NUMBER: " + i);
        System.out.println("+++++++++++++METHOD BODY+++++++++++++");
        return i;
    }

    public static void print(){
        System.out.println("TestClass print()");
    }
}