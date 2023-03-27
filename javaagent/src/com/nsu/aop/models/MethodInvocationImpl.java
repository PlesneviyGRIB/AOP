package com.nsu.aop.models;

import com.nsu.aop.interfaces.IMethodInvocation;

import java.lang.reflect.Method;

public class MethodInvocationImpl implements IMethodInvocation {
    private final String className;
    private final String methodName;
    public MethodInvocationImpl(String methodName, String className) {
        this.methodName = methodName;
        this.className = className;
    }

    @Override
    public Object invoke() throws Exception {
        Method method = ClassLoader.getSystemClassLoader().loadClass(className).getMethod(methodName);
        return method.invoke(null);
    }
}

