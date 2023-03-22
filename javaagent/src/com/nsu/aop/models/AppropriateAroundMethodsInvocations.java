package com.nsu.aop.models;

import com.nsu.aop.enums.AdviceType;
import com.nsu.aop.interfaces.IMethodInvocation;
import com.nsu.aop.interfaces.ProceedingJoinPointObj;
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
    private final ProceedingJoinPointObj proceedingJoinPoint;

    public AppropriateAroundMethodsInvocations(Method method, PointcutPrimitive pointcutPrimitive, ProceedingJoinPointObj proceedingJoinPoint) {
        this.aroundInv = new ArrayList<>();
        this.method = method;
        expressions = PointcutParsers.getExpressions();
        this.proceedingJoinPoint = proceedingJoinPoint;
        parse(pointcutPrimitive);
    }

    private void parse(PointcutPrimitive pointcutPrimitive){
        for(int i = 0; i < expressions.size(); i++) {
            if(pointcutPrimitive.equals(PointcutPrimitive.CALL)) {
                if (expressions.get(i).matchesMethodCall(method, method.getClass()).alwaysMatches()) {
                    addToContext(expressionPointcutBody.get(i));
                }
            }

            if(pointcutPrimitive.equals(PointcutPrimitive.EXECUTION))
                if (expressions.get(i).matchesMethodExecution(method).alwaysMatches())
                    addToContext(expressionPointcutBody.get(i));
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
}
