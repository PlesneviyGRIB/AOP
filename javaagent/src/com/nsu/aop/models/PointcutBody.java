package com.nsu.aop.models;

import com.nsu.aop.enums.AdviceType;
import javassist.bytecode.MethodInfo;

public class PointcutBody {
    private final AdviceType adviceType;
    private final MethodInfo methodInfo;

    private final String className;

    public PointcutBody(AdviceType adviceType, MethodInfo methodInfo, String className) {
        this.adviceType = adviceType;
        this.methodInfo = methodInfo;
        this.className = className;
    }

    public AdviceType getAdviceType() {
        return adviceType;
    }

    public MethodInfo getMethodInfo() {
        return methodInfo;
    }

    public String getClassName() {
        return className;
    }

}
