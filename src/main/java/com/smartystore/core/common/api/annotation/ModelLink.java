package com.smartystore.core.common.api.annotation;

import java.lang.annotation.*;

@Repeatable(value = ModelLinks.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface ModelLink {
    String value();
    String uri();
    String[] variables();
    String[] accesses() default {};
    String visible() default "";
}
