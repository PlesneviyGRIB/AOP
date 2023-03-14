package com.nsu.aop.javassist;

import com.google.common.reflect.ClassPath;
import java.io.*;

public class ClassNamesFinder {
    public String[] getClassNamesGuava(String packageName) throws IOException {
        ClassPath.ClassInfo[] classes = ClassPath.from(ClassLoader.getSystemClassLoader())
                .getAllClasses()
                .stream()
                .filter(clazz -> clazz.getPackageName()
                        .equalsIgnoreCase(packageName))
                .toArray(ClassPath.ClassInfo[]::new);
        if (classes.length == 0){
            System.err.println("No classes found in " + packageName + " package");
            throw new FileNotFoundException();
        }
        String[] classnames = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            classnames[i] = classes[i].getName();
            System.out.println("Found " + classnames[i]);
        }
        return classnames;
    }

}
