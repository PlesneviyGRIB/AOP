package com.nsu.aop.models;

import com.nsu.aop.interfaces.IMethodInvocation;
import com.nsu.aop.interfaces.ProceedingJoinPoint;

import java.lang.reflect.Method;

public class MethodInvocationAroundImpl implements IMethodInvocation {
    private final String className;
    private final String methodName;

    private final ProceedingJoinPoint proceedingJoinPoint;
    public MethodInvocationAroundImpl(String methodName, String className, ProceedingJoinPoint proceedingJoinPoint) {
        this.methodName = methodName;
        this.className = className;
        this.proceedingJoinPoint = proceedingJoinPoint;
    }

    @Override
    public Object invoke() throws Exception {
        Method method = ClassLoader.getSystemClassLoader().loadClass(className).getMethod(methodName, ProceedingJoinPoint.class);
        return method.invoke(null, proceedingJoinPoint);
    }
}
