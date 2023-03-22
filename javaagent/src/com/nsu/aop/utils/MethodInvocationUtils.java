package com.nsu.aop.utils;

import com.nsu.aop.interfaces.IMethodInvocation;
import com.nsu.aop.transformers.InnerMethodTransformer;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public class MethodInvocationUtils {
    public static Method getCurrentMethod(Object[] methodArgs, String targetClass, String targetMethod){
        String methodName = targetMethod.replace(InnerMethodTransformer.METHOD_PREFIX, "");

        try {
            switch (methodArgs.length) {
                case 0 -> {return MethodInvocation.getMethod0(methodArgs, targetClass, methodName);}
                case 1 -> {return MethodInvocation.getMethod1(methodArgs, targetClass, methodName);}
                case 2 -> {return MethodInvocation.getMethod2(methodArgs, targetClass, methodName);}
                case 3 -> {return MethodInvocation.getMethod3(methodArgs, targetClass, methodName);}
                case 4 -> {return MethodInvocation.getMethod4(methodArgs, targetClass, methodName);}
                case 5 -> {return MethodInvocation.getMethod5(methodArgs, targetClass, methodName);}
                default -> { return null; }
            }
        } catch (Exception e){
            e.getStackTrace();
        }
        return null;
    }

    public static Object invoke(Object executionObjectRef, Object[] methodArgs, String targetClass, String targetMethod){
        try {
            switch (methodArgs.length) {
                case 0 -> {return MethodInvocation.invoke0(executionObjectRef, methodArgs, targetClass, targetMethod);}
                case 1 -> {return MethodInvocation.invoke1(executionObjectRef, methodArgs, targetClass, targetMethod);}
                case 2 -> {return MethodInvocation.invoke2(executionObjectRef, methodArgs, targetClass, targetMethod);}
                case 3 -> {return MethodInvocation.invoke3(executionObjectRef, methodArgs, targetClass, targetMethod);}
                case 4 -> {return MethodInvocation.invoke4(executionObjectRef, methodArgs, targetClass, targetMethod);}
                case 5 -> {return MethodInvocation.invoke5(executionObjectRef, methodArgs, targetClass, targetMethod);}
                default -> { return null; }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void invokeAll(List<IMethodInvocation> methodInvocationQueue){
        methodInvocationQueue.forEach(IMethodInvocation::invoke);
    }

    public static Object invokeAsChain(List<IMethodInvocation> methodInvocationQueue){
        List<Object> results = methodInvocationQueue.stream().map(IMethodInvocation::invoke).toList();
        return results.get(results.size()-1);
    }
}
