package com.nsu.aop.javassist;

import com.google.common.reflect.ClassPath;
import javassist.ClassPool;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ClassNamesFinder {
    public String[] getClassNamesGuava(String packageName) throws IOException {
        ClassPath.ClassInfo[] classes = ClassPath.from(ClassLoader.getSystemClassLoader())
                .getAllClasses()
                .stream()
                .filter(clazz -> clazz.getPackageName()
                        .equalsIgnoreCase(packageName))
                .toArray(ClassPath.ClassInfo[]::new);
        String[] classnames = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            classnames[i] = classes[i].getName();
        }
        return classnames;
    }

}
