package com.nsu.aop.models;

import com.nsu.aop.interfaces.IMethodInvocation;
import com.nsu.aop.interfaces.ProceedingJoinPoint;
import com.nsu.aop.utils.MethodInvocationUtils;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class DynamicMethodInvocation implements ProceedingJoinPoint {
    private final String targetClass;
    private final String targetMethod;
    private Object[] methodArgs;
    private final Object executedObjectRef;

    private List<IMethodInvocation> aroundAdvicesChain;
    private int aroundAdvicesCounter = -1;

    public DynamicMethodInvocation(Object[] methodArgs, Object object, String className, String methodName) {
        this.methodArgs = methodArgs;
        this.executedObjectRef = object;
        this.targetClass = className;
        this.targetMethod = methodName;
    }

    public Object process() throws Exception{
        AppropriateMethodsInvocations appropriateMethodsInvocations =
                new AppropriateMethodsInvocations(
                        MethodInvocationUtils.getCurrentMethod(methodArgs, targetClass, targetMethod),
                        PointcutPrimitive.CALL
                );

        Object result = null;
        Exception exception = null;

        MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getBeforeInv());

        try{
            result = around();

            MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getAfterInv());
        } catch (Exception e){
            MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getAfterThrowingInv());
            exception = e;
        }
        MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getFinallyInv());

        appropriateMethodsInvocations.releaseCflowFlag();

        if(exception != null) throw exception;

        return result;
    }

    private Object around() throws Exception {
        AppropriateAroundMethodsInvocations appropriateAroundMethodsInvocations =
                new AppropriateAroundMethodsInvocations(
                        MethodInvocationUtils.getCurrentMethod(methodArgs, targetClass, targetMethod),
                        PointcutPrimitive.EXECUTION,
                        this
                );

        this.aroundAdvicesChain = appropriateAroundMethodsInvocations.getAroundInv();
        this.aroundAdvicesCounter = 0;

        appropriateAroundMethodsInvocations.releaseCflowFlag();

        return invoke(methodArgs);
    }

    @Override
    public Object invoke(Object[] methodArgs) throws Exception {
        this.methodArgs = methodArgs;

        if(aroundAdvicesChain.size() > aroundAdvicesCounter)
                return aroundAdvicesChain.get(aroundAdvicesCounter++).invoke();

        return execution(methodArgs);
    }

    private Object execution(Object[] methodArgs) throws Exception {
        AppropriateMethodsInvocations appropriateMethodsInvocations =
                new AppropriateMethodsInvocations(
                        MethodInvocationUtils.getCurrentMethod(methodArgs, targetClass, targetMethod),
                        PointcutPrimitive.EXECUTION
                );

        Object result = null;
        Exception exception = null;

        MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getBeforeInv());
        try{
            result = MethodInvocationUtils.invoke(executedObjectRef, methodArgs, targetClass, targetMethod);

            MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getAfterInv());
        } catch (Exception e){
            MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getAfterThrowingInv());
            exception = e;
        }
        MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getFinallyInv());

        appropriateMethodsInvocations.releaseCflowFlag();

        if(exception != null) throw exception;

        return result;
    }

    @Override
    public Object[] getArgs() {
        return methodArgs.clone();
    }

    @Override
    public Object invoke() throws Exception {
        return invoke(methodArgs);
    }

    @Override
    public Object getExecutedObject() {
        return executedObjectRef;
    }

    @Override
    public Method getCurrentMethod(){
        return MethodInvocationUtils.getCurrentMethod(methodArgs, targetClass, targetMethod);
    }
}