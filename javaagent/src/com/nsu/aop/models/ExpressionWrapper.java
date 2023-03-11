package com.nsu.aop.models;

public class ExpressionWrapper{
    private final String expression;
    public ExpressionWrapper(String expression){
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
