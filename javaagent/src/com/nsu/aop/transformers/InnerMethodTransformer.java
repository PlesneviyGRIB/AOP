package com.nsu.aop.transformers;

import com.nsu.aop.models.ExpressionWrapper;
import com.nsu.aop.models.PointcutBody;
import com.nsu.aop.utils.ParseUtils;
import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.*;

public class InnerMethodTransformer implements ClassFileTransformer {
    private final Map<ExpressionWrapper, PointcutBody> expressionPointcutBodyMap;
    private final Set<String> classNamesSet;

    public InnerMethodTransformer(Map<ExpressionWrapper, PointcutBody> expressionPointcutBodyMap, String[] classNames) {
        this.expressionPointcutBodyMap = expressionPointcutBodyMap;
        this.classNamesSet = new HashSet<>(Arrays.asList(ParseUtils.vmClassNamesStyle(classNames)));
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        if (!classNamesSet.contains(className)) return null;
        System.out.println("Transforming " + className);
        ClassPool classPool = ClassPool.getDefault();
        try {
            CtClass ctClass = classPool.get(className.replaceAll("/", "."));
            CtMethod[] methods = ctClass.getDeclaredMethods();
            System.out.println(Arrays.toString(methods));
            for (int i = 0; i < methods.length; i++) {
                if (Objects.equals(methods[i].getName(), "main")){
                    continue;
                }
                String previousName = methods[i].getName();
                String newName = "inner" + previousName.substring(0, 1).toUpperCase(Locale.ROOT) + previousName.substring(1);
                methods[i].setName(newName);
                CtMethod newMethod = CtNewMethod.copy(methods[i], previousName, ctClass, null);
                System.out.println();
                ctClass.addMethod(newMethod);
                changeBody(newMethod, newName);
            }
            System.out.println(Arrays.toString(ctClass.getDeclaredMethods()));
        } catch (NotFoundException e) {
            System.err.println("Class " + className + "not found");
            throw new RuntimeException(e);
        } catch (CannotCompileException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    private void changeBody(CtMethod method, String name) throws NotFoundException, CannotCompileException {
        CtClass returnType = method.getReturnType();
        if (Objects.equals(returnType.getName(), "void")){
            method.setBody("{ " + name +"(); }");
        }
        else{
            method.setBody("{ return " + name +"(); }");
        }
    }
}