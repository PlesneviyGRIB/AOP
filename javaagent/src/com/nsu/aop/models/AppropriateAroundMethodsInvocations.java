package com.nsu.aop.models;

import com.nsu.aop.enums.AdviceType;
import com.nsu.aop.interfaces.IMethodInvocation;
import com.nsu.aop.interfaces.ProceedingJoinPoint;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AppropriateAroundMethodsInvocations {
    private final List<IMethodInvocation> aroundInv;
    private final List<PointcutExpression> expressions;
    private static final List<Map.Entry<ExpressionWrapper, PointcutBody>> expressionPointcutBody = new ArrayList<>(ToolInfo.getInstance().getExpressionPointcutBodyMap().entrySet());
    private final Method method;
    private final ProceedingJoinPoint proceedingJoinPoint;
    private boolean isCflow;

    public AppropriateAroundMethodsInvocations(Method method, PointcutPrimitive pointcutPrimitive, ProceedingJoinPoint proceedingJoinPoint) {
        this.aroundInv = new ArrayList<>();
        this.method = method;
        expressions = PointcutParsers.getExpressions();
        this.proceedingJoinPoint = proceedingJoinPoint;
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
        if (Objects.requireNonNull(entry.getValue().getAdviceType()) == AdviceType.AROUND) {
            aroundInv.add(new MethodInvocationAroundImpl(entry.getValue().getMethodInfo().getName(), entry.getValue().getClassName(), proceedingJoinPoint));
        }
    }
    public List<IMethodInvocation> getAroundInv() {
        return aroundInv;
    }

    public void releaseCflowFlag(){
        if(this.isCflow){
            ToolInfo.getInstance().setCflowMethodDescriptor(null);
            ToolInfo.getInstance().setIsCflow(false);
        }
    }
}
