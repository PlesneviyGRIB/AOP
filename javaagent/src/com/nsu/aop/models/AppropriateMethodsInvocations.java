package com.nsu.aop.models;

import com.nsu.aop.interfaces.IMethodInvocation;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.*;

public class AppropriateMethodsInvocations {
    private final List<IMethodInvocation> beforeInv;
    private final List<IMethodInvocation> afterInv;
    private final List<IMethodInvocation> afterThrowingInv;
    private final List<IMethodInvocation> finallyInv;
    private static final Set<PointcutPrimitive> supportedPointcutKinds;
    private static final PointcutParser pointcutParser;
    private static final List<PointcutExpression> expressions = new ArrayList<>();
    private static final List<Map.Entry<ExpressionWrapper, PointcutBody>> expressionPointcutBody;
    private final Method method;

    static {
        supportedPointcutKinds = new HashSet<>();
        supportedPointcutKinds.add(PointcutPrimitive.EXECUTION);

        pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPointcutKinds);

        expressionPointcutBody = new ArrayList<>(ToolInfo.getInstance().getExpressionPointcutBodyMap().entrySet());
        expressionPointcutBody.forEach(entry -> expressions.add(pointcutParser.parsePointcutExpression(entry.getKey().getExpression())));
    }

    public AppropriateMethodsInvocations(Method method) {
        this.beforeInv = new ArrayList<>();
        this.afterInv = new ArrayList<>();
        this.afterThrowingInv = new ArrayList<>();
        this.finallyInv = new ArrayList<>();
        this.method = method;
        parse();
    }

    private void parse(){
        for(int i = 0; i < expressions.size(); i++)
            if(expressions.get(i).matchesMethodExecution(method).alwaysMatches())
                addToContext(expressionPointcutBody.get(i));
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
}
