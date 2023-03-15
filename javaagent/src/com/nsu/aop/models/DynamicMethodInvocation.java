package com.nsu.aop.models;

import com.nsu.aop.utils.MethodInvocation;
import com.nsu.aop.utils.ParseUtils;

import java.lang.reflect.Method;
import java.util.Map;

public class DynamicMethodInvocation {
    private final String targetClass;
    private final String targetMethod;
    private final Object[] methodArgs;
    private final Object executionObjectRef;
    private final Map<ExpressionWrapper, PointcutBody> expressionPointcutBodyMap = ToolInfo.getInstance().getExpressionPointcutBodyMap();

    public DynamicMethodInvocation(Object[] methodArgs, Object object, String className, String methodName) {
        this.methodArgs = methodArgs;
        this.executionObjectRef = object;
        this.targetClass = className;
        this.targetMethod = methodName;
    }

    public Object process(){
        Object result = invoke();
        return null;
    }

    private Object invoke(){
        try {
            switch (methodArgs.length) {
                case 0 -> {return MethodInvocation.invoke0(executionObjectRef, methodArgs, targetClass, targetMethod);}
                case 1 -> {return MethodInvocation.invoke1(executionObjectRef, methodArgs, targetClass, targetMethod);}
                case 2 -> {return MethodInvocation.invoke2(executionObjectRef, methodArgs, targetClass, targetMethod);}
                default -> { return null; }
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

}
