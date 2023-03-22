package com.nsu.aop.models;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.util.*;

public class PointcutParsers {
    private static final List<PointcutExpression> expressions = new ArrayList<>();
    private static final List<Map.Entry<ExpressionWrapper, PointcutBody>> expressionPointcutBody;

    static {
        expressionPointcutBody = new ArrayList<>(ToolInfo.getInstance().getExpressionPointcutBodyMap().entrySet());

        Set<PointcutPrimitive> supportedCall = new HashSet<>();
        supportedCall.add(PointcutPrimitive.CALL);
        supportedCall.add(PointcutPrimitive.EXECUTION);
        supportedCall.add(PointcutPrimitive.WITHIN);

        PointcutParser callPointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedCall);
        expressionPointcutBody.forEach(entry -> expressions.add(callPointcutParser.parsePointcutExpression(entry.getKey().getExpression())));
    }

    private PointcutParsers(){}

    public static List<PointcutExpression> getExpressions(){
        return expressions;
    }
}
