package com.nsu.aop.models;

import com.nsu.aop.interfaces.IMethodInvocation;
import com.nsu.aop.interfaces.ProceedingJoinPointObj;

import java.lang.reflect.Method;

public class MethodInvocationAroundImpl implements IMethodInvocation {
    private final String className;
    private final String methodName;

    private final ProceedingJoinPointObj proceedingJoinPoint;
    public MethodInvocationAroundImpl(String methodName, String className, ProceedingJoinPointObj proceedingJoinPoint) {
        this.methodName = methodName;
        this.className = className;
        this.proceedingJoinPoint = proceedingJoinPoint;
    }

    @Override
    public Object invoke() {
        try {
            Object[] args = new Object[]{proceedingJoinPoint};
            Method method = ClassLoader.getSystemClassLoader().loadClass(className).getMethod(methodName, args[0].getClass());
            return method.invoke(null, args[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
