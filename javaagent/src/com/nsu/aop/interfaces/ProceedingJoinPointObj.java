package com.nsu.aop.interfaces;

public interface ProceedingJoinPointObj {
    Object[] getArgs();
    Object invoke(Object[] args) throws Exception;
    Object invoke() throws Exception;
}
