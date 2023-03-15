package com.nsu.aop.models;

import com.nsu.aop.interfaces.IMethodInvocation;
import com.nsu.aop.interfaces.ProceedingJoinPointObj;
import com.nsu.aop.transformers.InnerMethodTransformer;
import com.nsu.aop.utils.MethodInvocation;
import org.aspectj.weaver.tools.PointcutParser;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class DynamicMethodInvocation implements ProceedingJoinPointObj {
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
        AppropriateMethodsInvocations appropriateMethodsInvocations = new AppropriateMethodsInvocations(getCurrentMethod());

        Object result = null;

        invokeAll(appropriateMethodsInvocations.getBeforeInv());
        try{
            result = invoke();
            invokeAll(appropriateMethodsInvocations.getAfterInv());
        } catch (Exception e){
            invokeAll(appropriateMethodsInvocations.getAfterThrowingInv());
        }
        invokeAll(appropriateMethodsInvocations.getFinallyInv());

        return result;
    }

    private void invokeAll(List<IMethodInvocation> methodInvocationQueue){
        methodInvocationQueue.forEach(m -> m.invoke());
    }

    private Method getCurrentMethod(){
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
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Object invoke(Object[] methodArgs){
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
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Object[] getArgs() {
        return methodArgs.clone();
    }

    @Override
    public Object invoke() {
        return invoke(methodArgs);
    }
}