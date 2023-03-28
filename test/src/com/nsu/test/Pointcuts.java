package com.nsu.test;

import com.nsu.aop.annotations.Cflow;
import com.nsu.aop.annotations.PointCut;
import com.nsu.aop.annotations.PointCutPool;

@PointCutPool
public class Pointcuts {
    @PointCut("call(* *(..))")
    public static void callEveryMethod(){}

    @Cflow("execution(* *(..)) && within(com.nsu.test.TestClass)")
    public static void cflowExample(){}
}
