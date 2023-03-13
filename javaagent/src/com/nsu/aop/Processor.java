package com.nsu.aop;

import com.nsu.aop.annotations.Aspect;
import com.nsu.aop.annotations.PointCutPool;
import com.nsu.aop.javassist.JavassistReader;
import com.nsu.aop.models.ExpressionWrapper;
import com.nsu.aop.models.PointcutBody;
import com.nsu.aop.transformers.InnerMethodTransformer;
import com.nsu.aop.transformers.Transformer1;

import java.lang.instrument.Instrumentation;
import java.util.Map;

@Aspect
@PointCutPool
public class Processor {
    public static void premain(String args, Instrumentation inst) throws Exception {
        // String packageName = "com.nsu.test";

        String[] classNames = new String[]
        {
                "com.nsu.test.Main",
                "com.nsu.test.MyPointcuts",
                "com.nsu.test.TestClass"
        };

        // String[] classNames = new ClassNamesFinder().getClassNames(packageName);

        Map<ExpressionWrapper, PointcutBody> map = new JavassistReader(classNames).readClasses();

        inst.addTransformer(new InnerMethodTransformer(map, classNames));
        inst.addTransformer(new Transformer1(map, classNames));
    }
}