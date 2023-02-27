package com.nsu.aop.annotations;

import com.nsu.aop.enums.AdviceType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Advice {
    AdviceType type();
    String expression();
}
