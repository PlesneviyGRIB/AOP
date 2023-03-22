package com.nsu.aop.javassist;

import com.nsu.aop.enums.AdviceType;
import com.nsu.aop.models.ExpressionWrapper;
import com.nsu.aop.models.PointcutBody;
import com.nsu.aop.utils.ParseUtils;
import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavassistReader {
    private final String[] classNames;
    private final Map<String, ExpressionWrapper> methodDeclarationExpressionMap = new HashMap<>();
    private final Map<ExpressionWrapper, PointcutBody> expressionPointcutBodyMap = new HashMap<>();

    public JavassistReader(String[] classNames) {
        this.classNames = classNames;
    }

    public Map<ExpressionWrapper, PointcutBody> readClasses() throws NotFoundException {

        for (String className : classNames) {
            ClassFile classFile = ClassPool.getDefault().get(className).getClassFile();

            AnnotationsAttribute attr = (AnnotationsAttribute) classFile.getAttribute(AnnotationsAttribute.visibleTag);

            if (attr != null && shouldSearchForAdvice(attr.getAnnotations()))
                readClass(classFile);
        }

        return normalizeResultMap(expressionPointcutBodyMap);
    }

    private boolean shouldSearchForAdvice(Annotation[] annotations) {
        for (Annotation annotation : annotations)
            if (ParseUtils.typeAnnotationsIsPresent(annotation.toString()))
                return true;
        return false;
    }

    private Map<ExpressionWrapper, PointcutBody> normalizeResultMap(Map<ExpressionWrapper, PointcutBody> expressionPointcutBodyMap) {
        Map<ExpressionWrapper, PointcutBody> result = new HashMap<>();
        expressionPointcutBodyMap.forEach((key, value) ->
                result.put(
                        methodDeclarationExpressionMap.getOrDefault(key.getExpression(), key),
                        value
                ));
        return result;
    }

    private void readClass(ClassFile classFile) {
        List<MethodInfo> methodInfoList = classFile.getMethods();
        methodInfoList.forEach(methodInfo -> readMethod(methodInfo, classFile.getName()));
    }

    private void readMethod(MethodInfo methodInfo, String className) {
        AnnotationsAttribute attr = (AnnotationsAttribute) methodInfo.getAttribute(AnnotationsAttribute.visibleTag);
        if (attr != null) {
            Arrays.asList(attr.getAnnotations()).forEach(annotation -> {
                String annotationString = annotation.toString();
                AdviceType adviceType = ParseUtils.parseAdviceType(annotationString);
                ExpressionWrapper expression = new ExpressionWrapper(ParseUtils.parseExpression(annotationString), ParseUtils.parseCflow(annotation.getTypeName()));
                if (adviceType != null)
                    expressionPointcutBodyMap.put(
                            expression,
                            new PointcutBody(adviceType, methodInfo, className)
                    );

                else if (ParseUtils.parsePointcutAnnotation(annotationString)){
                    methodDeclarationExpressionMap.put(
                            ParseUtils.parseSimpleClassName(className) + "." + methodInfo.getName(),
                            expression
                    );
                }
                else if (ParseUtils.parseCflowAnnotation(annotationString)){
                    AdviceType cflowAdviceType = null;
                    for (Map.Entry<ExpressionWrapper,PointcutBody> elem: expressionPointcutBodyMap.entrySet()) {
                        if (ParseUtils.isNeededMethod(annotation.getMemberValue("value").toString(), elem.getValue().getMethodInfo().getName())){
                            cflowAdviceType = elem.getValue().getAdviceType();
                        }
                    }
                    if (cflowAdviceType!=null){
                        expressionPointcutBodyMap.put(
                                expression, new PointcutBody(cflowAdviceType, methodInfo, className)
                        );
                    }
                }
            });
        }
    }
}
