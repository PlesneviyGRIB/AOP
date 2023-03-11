package com.nsu.aop.models;

import com.nsu.aop.enums.AdviceType;
import javassist.bytecode.MethodInfo;

public class PointcutBody {
    private final AdviceType adviceType;
    private final MethodInfo methodInfo;

    public PointcutBody(AdviceType adviceType, MethodInfo methodInfo) {
        this.adviceType = adviceType;
        this.methodInfo = methodInfo;
    }

    public AdviceType getAdviceType() {
        return adviceType;
    }

    public MethodInfo getMethodInfo() {
        return methodInfo;
    }
}
