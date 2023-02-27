package com.nsu.aop.transformers;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class MyClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {

        System.out.println(className);

        return null;
    }
}