package com.nsu.aop.models;

import com.nsu.aop.enums.AdviceType;
import javassist.bytecode.MethodInfo;

public class PointcutBody {
    private final AdviceType adviceType;
    private final MethodInfo methodInfo;

    private boolean isCflow;
    private final String className;

    public PointcutBody(AdviceType adviceType, MethodInfo methodInfo, String className, boolean isCflow) {
        this.adviceType = adviceType;
        this.methodInfo = methodInfo;
        this.className = className;
        this.isCflow = isCflow;
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

    public boolean isCflow() {
        return isCflow;
    }
}
