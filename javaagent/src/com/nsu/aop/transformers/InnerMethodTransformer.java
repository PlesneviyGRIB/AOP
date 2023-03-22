package com.nsu.aop.transformers;

import com.nsu.aop.utils.ParseUtils;
import javassist.*;
import javassist.bytecode.ClassFile;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class InnerMethodTransformer implements ClassFileTransformer {
    public static final String METHOD_PREFIX = "inner_";
    private final Set<String> classNamesSet;
    public InnerMethodTransformer(String[] classNames) {
        this.classNamesSet = new HashSet<>(Arrays.asList(ParseUtils.vmClassNamesStyle(classNames)));
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        if (!classNamesSet.contains(className)) return null;

        try {
            ClassPool classPool = ClassPool.getDefault();
            ClassFile classFile = new ClassFile(new DataInputStream(new ByteArrayInputStream(classfileBuffer)));
            CtClass ctClass = classPool.makeClass(classFile);

            CtMethod[] methods = ctClass.getDeclaredMethods();

            for (CtMethod method : methods) {
                String previousName = method.getName();
                String newName = METHOD_PREFIX + previousName;
                method.setName(newName);
                CtMethod newMethod = CtNewMethod.copy(method, previousName, ctClass, null);
                ctClass.addMethod(newMethod);
                changeBody(newMethod, ctClass.getName());
            }

            return ctClass.toBytecode();

        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void changeBody(CtMethod method, String className) throws NotFoundException, CannotCompileException {
            method.setBody("{"
                    + " return ($r)" + produceObjectInjection(method, className)
                    + " }");
    }

    private String produceObjectInjection(CtMethod prevMethod, String className) throws NotFoundException {
        StringBuilder stringBuilder = new StringBuilder("new com.nsu.aop.models.DynamicMethodInvocation(new Object[]{");

        for(int i = 0; i<prevMethod.getParameterTypes().length; i++)
            stringBuilder.append("$").append(i+1).append(",");
        if(prevMethod.getParameterTypes().length != 0) stringBuilder.deleteCharAt(stringBuilder.length()-1);

        stringBuilder.append("},");

        if(!Modifier.isStatic(prevMethod.getModifiers()))
            stringBuilder.append("this,");
        else stringBuilder.append("null,");

        stringBuilder.append("\"").append(className).append("\",");

        stringBuilder.append("\"").append(METHOD_PREFIX).append(prevMethod.getName()).append("\"");

        stringBuilder.append(").process();");

        return stringBuilder.toString();
    }
}