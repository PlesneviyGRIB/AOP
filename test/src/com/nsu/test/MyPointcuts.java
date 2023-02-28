package com.nsu.test;

import com.nsu.aop.annotations.JoinPoint;
import com.nsu.aop.annotations.PointCutPool;

@PointCutPool
public class MyPointcuts{
    @JoinPoint("execution(* *(..))")
    public void loggingJoinPoint(){}
}