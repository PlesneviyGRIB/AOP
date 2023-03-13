package com.nsu.aop;

import com.nsu.aop.annotations.Aspect;
import com.nsu.aop.annotations.PointCutPool;
import com.nsu.aop.javassist.ClassNamesFinder;
import com.nsu.aop.javassist.JavassistReader;
import com.nsu.aop.models.ExpressionWrapper;
import com.nsu.aop.models.PointcutBody;
import com.nsu.aop.transformers.InnerMethodTransformer;
import com.nsu.aop.transformers.Transformer1;

import java.lang.instrument.Instrumentation;
import com.nsu.aop.utils.Logger;
import javassist.ClassPool;
import javassist.bytecode.ClassFile;

import java.io.*;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Aspect
@PointCutPool
public class Processor {
    public static void premain(String args, Instrumentation inst) throws Exception {
        String packageName = "com.nsu.test";
        String[] classNames = new ClassNamesFinder().getClassNamesGuava(packageName);
        Map<ExpressionWrapper, PointcutBody> map = new JavassistReader(classNames).readClasses();
        inst.addTransformer(new InnerMethodTransformer(map, classNames));
        inst.addTransformer(new Transformer1(map, classNames));
        
    }
}
