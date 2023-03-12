package com.nsu.aop;

import com.nsu.aop.javassist.ClassNamesFinder;
import com.nsu.aop.javassist.JavassistReader;
import com.nsu.aop.models.ExpressionWrapper;
import com.nsu.aop.models.PointcutBody;

import java.io.*;
import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Processor {
    public static void premain(String args, Instrumentation inst) throws Exception {
        String packageName = "com.nsu.test";
        // Somehow we need to know what methods we should parse for annotations
        // TODO: Think move about how to get classes from assembly
        String[] classNames = new ClassNamesFinder().getClassNames(packageName);

        // Contains Info about expression, AdviceType, And method body
        Map<ExpressionWrapper, PointcutBody> map = new JavassistReader(classNames).readClasses();

        // just for test
        map.forEach((key, value) -> System.out.println(key.getExpression() + " = " + value.getAdviceType().toString() + " " + value.getMethodInfo().getName()));

        // TODO: Write class transformer which rename ALL METHODS that somehow could be involved into intercepting with our AOP tool.
        // TODO: Names of methods should be changed: (prev name: getInfo() -> new name: inner_getInfo()) and call of such methods should be encapsulated.

        System.out.println("----------------");
        System.out.println("JavaAgent return");
    }
}