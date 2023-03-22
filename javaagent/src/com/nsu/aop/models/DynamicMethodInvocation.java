package com.nsu.aop.models;

import com.nsu.aop.interfaces.ProceedingJoinPointObj;
import com.nsu.aop.utils.MethodInvocationUtils;
import org.aspectj.weaver.tools.PointcutPrimitive;
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

    //CALL
    public Object process(){
        AppropriateMethodsInvocations appropriateMethodsInvocations =
                new AppropriateMethodsInvocations(
                        MethodInvocationUtils.getCurrentMethod(methodArgs, targetClass, targetMethod),
                        PointcutPrimitive.CALL
                );

        Object result = null;

        //before
        MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getBeforeInv());
        //after
        MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getAfterInv());
        //afterThrowing
        MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getAfterThrowingInv());
        //finally
        MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getFinallyInv());


        AppropriateAroundMethodsInvocations appropriateAroundMethodsInvocations =
                new AppropriateAroundMethodsInvocations(
                        MethodInvocationUtils.getCurrentMethod(methodArgs, targetClass, targetMethod),
                        PointcutPrimitive.EXECUTION,
                        this
                );

        try{
            // Around EXECUTION
            if(appropriateAroundMethodsInvocations.getAroundInv().size() > 0)
                result = MethodInvocationUtils.invokeAsChain(appropriateAroundMethodsInvocations.getAroundInv());
            else
                result = invoke(methodArgs);

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //EXECUTION
    @Override
    public Object invoke(Object[] methodArgs) throws Exception {
        AppropriateMethodsInvocations appropriateMethodsInvocations =
                new AppropriateMethodsInvocations(
                        MethodInvocationUtils.getCurrentMethod(methodArgs, targetClass, targetMethod),
                        PointcutPrimitive.EXECUTION
                );

        Object result = null;
        Exception exception = null;

        //before
        MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getBeforeInv());
        try{
            //method invocation
            result = MethodInvocationUtils.invoke(executionObjectRef, methodArgs, targetClass, targetMethod);
            //after
            MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getAfterInv());
        } catch (Exception e){
            //afterThrowing
            MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getAfterThrowingInv());
            exception = e;
        }
        //finally
        MethodInvocationUtils.invokeAll(appropriateMethodsInvocations.getFinallyInv());

        if(exception != null) throw exception;

        return result;
    }

    @Override
    public Object[] getArgs() {
        return methodArgs.clone();
    }

    @Override
    public Object invoke() throws Exception{
        return invoke(methodArgs);
    }
}