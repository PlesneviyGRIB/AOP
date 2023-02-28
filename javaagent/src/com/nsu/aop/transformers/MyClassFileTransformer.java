package com.nsu.aop.transformers;

import javassist.ClassMap;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class MyClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        ClassMap classMap = new ClassMap();
        return null;
    }
}