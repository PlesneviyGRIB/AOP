package com.nsu.aop;

import com.nsu.aop.transformers.MyClassFileTransformer;

import java.lang.instrument.Instrumentation;

public class Processor {
    public static void premain(String args, Instrumentation inst) {
        MyClassFileTransformer.init();

        inst.addTransformer(new MyClassFileTransformer());

        System.out.println("PREMAIN12");
    }
}