package com.nsu.aop.interfaces;

import java.lang.reflect.Method;

public interface ProceedingJoinPoint {
    Object invoke() throws Exception;
    Object invoke(Object[] args) throws Exception;
    Object[] getArgs();
    Method getCurrentMethod();
    Object getExecutedObject();
}
