package com.nsu.aop.transformers;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class MyClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        System.out.println(className);
        System.out.println("HELLO");
        return null;
    }

    public static void init(){
        System.out.println("TRANSFORMER");
    }
}