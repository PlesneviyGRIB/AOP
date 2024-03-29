package com.nsu.aop.models;

import com.nsu.aop.interfaces.IMethodInvocation;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppropriateMethodsInvocations {
    private final List<IMethodInvocation> beforeInv;
    private final List<IMethodInvocation> afterInv;
    private final List<IMethodInvocation> afterThrowingInv;
    private final List<IMethodInvocation> finallyInv;
    private final List<PointcutExpression> expressions;
    private boolean isCflow;
    private static final List<Map.Entry<ExpressionWrapper, PointcutBody>> expressionPointcutBody = new ArrayList<>(ToolInfo.getInstance().getExpressionPointcutBodyMap().entrySet());
    private final Method method;

    public AppropriateMethodsInvocations(Method method, PointcutPrimitive pointcutPrimitive) {
        this.beforeInv = new ArrayList<>();
        this.afterInv = new ArrayList<>();
        this.afterThrowingInv = new ArrayList<>();
        this.finallyInv = new ArrayList<>();
        this.method = method;
        expressions = PointcutParsers.getExpressions();
        parse(pointcutPrimitive);
    }

    private void parse(PointcutPrimitive pointcutPrimitive){
        for(int i = 0; i < expressions.size(); i++) {
            if (pointcutPrimitive.equals(PointcutPrimitive.CALL)) {
                if(ToolInfo.getInstance().getIsCflow()) {
                    addToContext(ToolInfo.getInstance().getCflowMethodDescriptor());
                    continue;
                }
                if (expressions.get(i).matchesMethodCall(method, method.getClass()).alwaysMatches())
                    registryMethod(expressionPointcutBody.get(i));
            }

            if (pointcutPrimitive.equals(PointcutPrimitive.EXECUTION)) {
                if(ToolInfo.getInstance().getIsCflow()) {
                    addToContext(ToolInfo.getInstance().getCflowMethodDescriptor());
                    continue;
                }
                if (expressions.get(i).matchesMethodExecution(method).alwaysMatches())
                    registryMethod(expressionPointcutBody.get(i));
            }
        }
    }

    private void registryMethod(Map.Entry<ExpressionWrapper, PointcutBody> entry){
        addToContext(entry);
        if(entry.getKey().isCflow()) {
            if (!ToolInfo.getInstance().getIsCflow()) {
                this.isCflow = true;
                ToolInfo.getInstance().setIsCflow(true);
                ToolInfo.getInstance().setCflowMethodDescriptor(entry);
            }
        }
    }

    private void addToContext(Map.Entry<ExpressionWrapper, PointcutBody> entry){
        switch (entry.getValue().getAdviceType()){
            case BEFORE -> beforeInv.add(new MethodInvocationImpl(entry.getValue().getMethodInfo().getName(), entry.getValue().getClassName()));
            case AFTER -> afterInv.add(new MethodInvocationImpl(entry.getValue().getMethodInfo().getName(), entry.getValue().getClassName()));
            case AFTERTHROWING -> afterThrowingInv.add(new MethodInvocationImpl(entry.getValue().getMethodInfo().getName(), entry.getValue().getClassName()));
            case FINALLY -> finallyInv.add(new MethodInvocationImpl(entry.getValue().getMethodInfo().getName(), entry.getValue().getClassName()));
        }
    }

    public List<IMethodInvocation> getBeforeInv() {
        return beforeInv;
    }

    public List<IMethodInvocation> getAfterInv() {
        return afterInv;
    }

    public List<IMethodInvocation> getAfterThrowingInv() {
        return afterThrowingInv;
    }

    public List<IMethodInvocation> getFinallyInv() {
        return finallyInv;
    }

    public void releaseCflowFlag(){
        if(this.isCflow){
            ToolInfo.getInstance().setCflowMethodDescriptor(null);
            ToolInfo.getInstance().setIsCflow(false);
        }
    }
}
