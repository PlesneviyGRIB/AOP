package com.nsu.aop.parser;

import javassist.ClassPool;
import javassist.CtClass;

import java.util.Arrays;

public class ClassParser {
    public ClassParser() {
        parse();
    }

    private void parse(){
        ClassPool pool = ClassPool.getDefault();

        try {
            CtClass cc = pool.get("com.nsu.test.Main");
            Object[] annotations = cc.getAnnotations();

            Arrays.stream(annotations).forEach(a -> System.out.println(a));


        } catch (Exception e) {
            System.out.println("HERE");
            //throw new RuntimeException(e);
        }
    }
}
