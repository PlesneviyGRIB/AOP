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
        String packageName = parseArgs(args);
        PackageNameStorage.setPackageName(packageName);
        String[] classNames = new ClassNamesFinder().getClassNamesGuava(packageName);

        Map<ExpressionWrapper, PointcutBody> map = new JavassistReader(classNames).readClasses();

        ToolInfo.init(map);

        inst.addTransformer(new InnerMethodTransformer(classNames));
    }

    private static String parseArgs(String args) {
        if (args.contains("package:")){
            return args.replace("package:", "");
        }
        else{
            System.out.println("""
                    Enter package name as a CLI option of javaagent
                    Proper way:
                    -javaagent:<path to agent.jar>=package:<package name>
                    """);
            throw new IllegalArgumentException();
        }
    }
}
