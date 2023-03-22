package com.nsu.aop.models;

public class ExpressionWrapper {
    private final String expression;

    private final boolean isCflow;

    public ExpressionWrapper(String expression, Boolean isCflow) {
        this.expression = expression;
        this.isCflow = isCflow;
    }

    public String getExpression() {
        return expression;
    }

    public boolean isCflow() {
        return isCflow;
    }
}
