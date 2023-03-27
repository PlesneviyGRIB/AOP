package com.nsu.aop.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvocation {
    public static Object invoke0(Object objRef, Object[] args, String targetClass, String targetMethod) throws Exception {
        Method method = ClassLoader.getSystemClassLoader().loadClass(targetClass).getMethod(targetMethod);
        return method.invoke(objRef);
    }
    public static Object invoke1(Object objRef, Object[] args, String targetClass, String targetMethod) throws Exception {
        Method method = ClassLoader.getSystemClassLoader().loadClass(targetClass).getMethod(targetMethod, args[0].getClass());
        return method.invoke(objRef, args[0]);
    }
    public static Object invoke2(Object objRef, Object[] args, String targetClass, String targetMethod) throws Exception {
        Method method = ClassLoader.getSystemClassLoader().loadClass(targetClass).getMethod(targetMethod, args[0].getClass(), args[1].getClass());
        return method.invoke(objRef, args[0], args[1]);
    }
    public static Object invoke3(Object objRef, Object[] args, String targetClass, String targetMethod) throws Exception {
        Method method = ClassLoader.getSystemClassLoader().loadClass(targetClass).getMethod(targetMethod, args[0].getClass(), args[1].getClass(), args[2].getClass());
        return method.invoke(objRef, args[0], args[1], args[2]);
    }
    public static Object invoke4(Object objRef, Object[] args, String targetClass, String targetMethod) throws Exception {
        Method method = ClassLoader.getSystemClassLoader().loadClass(targetClass).getMethod(targetMethod, args[0].getClass(), args[1].getClass(), args[2].getClass(), args[3].getClass());
        return method.invoke(objRef, args[0], args[1], args[2], args[3]);
    }
    public static Object invoke5(Object objRef, Object[] args, String targetClass, String targetMethod) throws Exception {
        Method method = ClassLoader.getSystemClassLoader().loadClass(targetClass).getMethod(targetMethod, args[0].getClass(), args[1].getClass(), args[2].getClass(), args[3].getClass(), args[4].getClass());
        return method.invoke(objRef, args[0], args[1], args[2], args[3], args[4]);
    }

    public static Method getMethod0(Object[] args, String targetClass, String targetMethod) throws ClassNotFoundException, NoSuchMethodException {
        return ClassLoader.getSystemClassLoader().loadClass(targetClass).getMethod(targetMethod);
    }
    public static Method getMethod1(Object[] args, String targetClass, String targetMethod) throws ClassNotFoundException, NoSuchMethodException {
        return ClassLoader.getSystemClassLoader().loadClass(targetClass).getMethod(targetMethod, args[0].getClass());
    }
    public static Method getMethod2(Object[] args, String targetClass, String targetMethod) throws ClassNotFoundException, NoSuchMethodException {
        return ClassLoader.getSystemClassLoader().loadClass(targetClass).getMethod(targetMethod, args[0].getClass(), args[1].getClass());
    }
    public static Method getMethod3(Object[] args, String targetClass, String targetMethod) throws ClassNotFoundException, NoSuchMethodException {
        return ClassLoader.getSystemClassLoader().loadClass(targetClass).getMethod(targetMethod, args[0].getClass(), args[1].getClass(), args[2].getClass());
    }
    public static Method getMethod4(Object[] args, String targetClass, String targetMethod) throws ClassNotFoundException, NoSuchMethodException {
        return ClassLoader.getSystemClassLoader().loadClass(targetClass).getMethod(targetMethod, args[0].getClass(), args[1].getClass(), args[2].getClass(), args[3].getClass());
    }
    public static Method getMethod5(Object[] args, String targetClass, String targetMethod) throws ClassNotFoundException, NoSuchMethodException {
        return ClassLoader.getSystemClassLoader().loadClass(targetClass).getMethod(targetMethod, args[0].getClass(), args[1].getClass(), args[2].getClass(), args[3].getClass(), args[4].getClass());
    }
}
