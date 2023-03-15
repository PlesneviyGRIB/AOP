package com.nsu.aop;

import com.nsu.aop.annotations.Aspect;
import com.nsu.aop.annotations.PointCutPool;
import com.nsu.aop.javassist.ClassNamesFinder;
import com.nsu.aop.javassist.JavassistReader;
import com.nsu.aop.models.ExpressionWrapper;
import com.nsu.aop.models.PointcutBody;
import com.nsu.aop.models.ToolInfo;
import com.nsu.aop.transformers.InnerMethodTransformer;
import java.lang.instrument.Instrumentation;
import java.util.Map;

@Aspect
@PointCutPool
public class Processor {
    public static void premain(String args, Instrumentation inst) throws Exception {
        String packageName = "com.nsu.test";

        String[] classNames = new ClassNamesFinder().getClassNamesGuava(packageName);

        Map<ExpressionWrapper, PointcutBody> map = new JavassistReader(classNames).readClasses();

        ToolInfo.init(map);

        inst.addTransformer(new InnerMethodTransformer(classNames));
    }
}
