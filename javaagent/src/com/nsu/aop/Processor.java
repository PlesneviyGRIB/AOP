package com.nsu.aop;

import com.nsu.aop.annotations.Aspect;
import com.nsu.aop.annotations.PointCutPool;
import com.nsu.aop.javassist.ClassNamesFinder;
import com.nsu.aop.javassist.JavassistReader;
import com.nsu.aop.models.ToolInfo;
import com.nsu.aop.transformers.InnerMethodTransformer;
import com.nsu.aop.utils.ParseUtils;
import java.lang.instrument.Instrumentation;

@Aspect
@PointCutPool
public class Processor {
    public static void premain(String args, Instrumentation inst) throws Exception {
        String packageName = ParseUtils.parsePackageName(args);

        String[] classNames = new ClassNamesFinder().getClassNamesGuava(packageName);

        ToolInfo.init(new JavassistReader(classNames).readClasses());

        inst.addTransformer(new InnerMethodTransformer(classNames));
    }
}
