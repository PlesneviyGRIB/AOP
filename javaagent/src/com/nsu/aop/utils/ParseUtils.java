package com.nsu.aop.utils;

import com.nsu.aop.enums.AdviceType;

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

    public static boolean parsePointcutAnnotation(String annotationString){
        return parseTypeName(annotationString).equals("POINTCUT");
    }

    public static String parseSimpleClassName(String className){
        String[] words = className.split("\\.");

        return words[words.length - 1];
    }

    public static String parseExpression(String annotationString){

        String tmp = annotationString.substring(annotationString.indexOf("\"") + 1, annotationString.length() - 1);

        return tmp.substring(0, tmp.indexOf("\""));
    }

    private static String parseTypeName(String annotationString){
        String tmp = annotationString.substring(0, annotationString.indexOf("("));
        String[] words = tmp.split("\\.");
        return words[words.length - 1].toUpperCase();
    }
}
