package com.nsu.aop.utils;

import com.nsu.aop.enums.AdviceType;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.MethodInfo;
import java.util.Arrays;

public class ParseUtils {
    public static AdviceType parseAdviceType(String annotationString){
        String adviceTypeString = parseTypeName(annotationString);

        switch (adviceTypeString){
            case "BEFORE" -> { return AdviceType.BEFORE; }
            case "AFTER" -> { return AdviceType.AFTER; }
            case "AFTERTHROWING" -> { return AdviceType.AFTERTHROWING; }
            case "FINALLY" -> { return AdviceType.FINALLY; }
            case "AROUND" -> { return AdviceType.AROUND; }
        }
        return null;
    }

    public static boolean isAdviceBody(MethodInfo methodInfo){
        if(methodInfo == null) return false;

        AnnotationsAttribute attr = (AnnotationsAttribute) methodInfo.getAttribute(AnnotationsAttribute.visibleTag);

        if(attr != null) {
            return Arrays.stream(attr.getAnnotations())
                    .anyMatch(annotation -> parseAdviceType(annotation.toString()) != null);
        }
        return false;
    }

    public static boolean parsePointcutAnnotation(String annotationString){
        return parseTypeName(annotationString).equals("POINTCUT");
    }

    public static boolean parseCflowAnnotation(String annotationString){
        return parseTypeName(annotationString).equals("CFLOW");
    }

    public static boolean isNeededMethod(String targetName, String methodName){
        return targetName.contains(methodName);
    }

    public static String parseSimpleClassName(String className){
        String[] words = className.split("\\.");

        return words[words.length - 1];
    }

    public static String parseExpression(String annotationString){

        String tmp = annotationString.substring(annotationString.indexOf("\"") + 1, annotationString.length() - 1);

        return tmp.substring(0, tmp.indexOf("\""));
    }

    public static String[] vmClassNamesStyle(String[] classNames){
        String[] vmClassNames = new String[classNames.length];

        for(int i = 0; i < classNames.length; i++)
            vmClassNames[i] = classNames[i].replace(".", "/");

        return vmClassNames;
    }

    public static boolean typeAnnotationsIsPresent(String annotationString){
        String[] words = annotationString.split("\\.");
        String annotationName = words[words.length - 1].toUpperCase();

        return annotationName.equals("ASPECT") || annotationName.equals("POINTCUTPOOL");
    }

    private static String parseTypeName(String annotationString){
        String tmp = annotationString.substring(0, annotationString.indexOf("("));
        String[] words = tmp.split("\\.");
        return words[words.length - 1].toUpperCase();
    }

    public static String parsePackageName(String args) {
        if (args.contains("package:")){
            return args.replace("package:", "");
        }
        else{
            System.out.println("""
                    Provide package name as a CLI option of javaagent
                    Proper way:
                    -javaagent:<path to agent.jar>=package:<package name>
                    """);
            throw new IllegalArgumentException();
        }
    }
}
