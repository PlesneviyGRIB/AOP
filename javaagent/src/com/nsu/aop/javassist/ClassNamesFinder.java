package com.nsu.aop.javassist;

import javassist.ClassPool;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ClassNamesFinder {
    public String[] getClassNames(String packageName) {
        ClassPool classPool = ClassPool.getDefault();
        ClassLoader classLoader = classPool.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Class[] classes = bufferedReader.lines().filter(line -> line.endsWith("class")).map(line -> getClassInPackage(line, packageName)).collect(Collectors.toSet()).toArray(Class[]::new);
        String[] res = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            res[i] = classes[i].getName();
            System.out.println("Found class: " + res[i]);
        }
        System.out.println("Found all classes in " + packageName + " package");
        System.out.println("===============================================");
        return res;
    }

    private Class getClassInPackage(String className, String packageName) {
        try {
            return Class.forName(packageName + "." +className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
