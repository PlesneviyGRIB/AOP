package com.nsu.aop.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvocation {

    public static Object invoke0(Object objRef, Object[] args, String targetClass, String targetMethod) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = ClassLoader.getSystemClassLoader().loadClass(targetClass).getMethod(targetMethod);
        return method.invoke(objRef);
    }
    public static Object invoke1(Object objRef, Object[] args, String targetClass, String targetMethod) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = ClassLoader.getSystemClassLoader().loadClass(targetClass).getMethod(targetMethod, args[0].getClass());
        return method.invoke(objRef, args[0]);
    }

    public static Object invoke2(Object objRef, Object[] args, String targetClass, String targetMethod) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = ClassLoader.getSystemClassLoader().loadClass(targetClass).getMethod(targetMethod, args[0].getClass(), args[1].getClass());
        return method.invoke(objRef, args[0], args[1]);
    }
}
