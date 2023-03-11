package com.nsu.aop.transformers;

import com.nsu.aop.models.PointcutBody;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.Map;

public class AnnotationsInterceptor implements ClassFileTransformer {

    private final Map<String, PointcutBody> expressionPointcutBodyMap;
    public AnnotationsInterceptor(Map<String, PointcutBody> expressionPointcutBodyMap) {
        this.expressionPointcutBodyMap = expressionPointcutBodyMap;
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {

        System.out.println("TRANSFORMER: " + className);
        return null;
    }
}