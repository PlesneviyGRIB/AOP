package com.nsu.aop.models;

import java.util.Map;

public class ToolInfo {
    private Map<ExpressionWrapper, PointcutBody> expressionPointcutBodyMap;
    private static ToolInfo toolInfo;

    private boolean isCflow = false;

    private Map.Entry<ExpressionWrapper, PointcutBody> cflowMethodDescriptor = null;

    public Map<ExpressionWrapper, PointcutBody> getExpressionPointcutBodyMap() {
        return expressionPointcutBodyMap;
    }

    private ToolInfo(){}

    public static void init(Map<ExpressionWrapper, PointcutBody> map){
        toolInfo = new ToolInfo();
        toolInfo.expressionPointcutBodyMap = map;
    }

    public static ToolInfo getInstance(){ return toolInfo; }

    public boolean getIsCflow() {
        return isCflow;
    }

    public void setIsCflow(boolean flag){
        this.isCflow = flag;
    }

    public void setCflowMethodDescriptor(Map.Entry<ExpressionWrapper, PointcutBody> cflowMethodDescriptor) {
        this.cflowMethodDescriptor = cflowMethodDescriptor;
    }

    public Map.Entry<ExpressionWrapper, PointcutBody> getCflowMethodDescriptor() {
        return cflowMethodDescriptor;
    }
}
