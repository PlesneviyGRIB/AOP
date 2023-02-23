package com.nsu.aopa.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@InnerAspect
@Target(ElementType.TYPE)
public @interface PointCutPool {
}
