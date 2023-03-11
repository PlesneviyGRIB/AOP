package com.nsu.test;

import com.nsu.aop.annotations.PointCut;
import com.nsu.aop.annotations.PointCutPool;

@PointCutPool
public class MyPointcuts{
    @PointCut("execution(* *(..))")
    public void loggingJoinPoint(){}
}