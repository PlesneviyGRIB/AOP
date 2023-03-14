package com.nsu.aop.transformers;

import com.nsu.aop.models.ExpressionWrapper;
import com.nsu.aop.models.PointcutBody;
import com.nsu.aop.utils.ParseUtils;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InnerMethodTransformer implements ClassFileTransformer {
    private final Map<ExpressionWrapper, PointcutBody> expressionPointcutBodyMap;
    private final Set<String> classNamesSet;
    public InnerMethodTransformer(Map<ExpressionWrapper, PointcutBody> expressionPointcutBodyMap, String[] classNames) {
        this.expressionPointcutBodyMap = expressionPointcutBodyMap;
        this.classNamesSet = new HashSet<>(Arrays.asList(ParseUtils.vmClassNamesStyle(classNames)));
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        if(!classNamesSet.contains(className)) return null;
        System.out.println("Transforming " + className);

        return null;
    }
}