package com.smartystore.core.common.api.annotation;

import java.lang.annotation.*;

@Repeatable(value = PageLinks.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface PageLink {
    String value();
    String uri();
    String[] variables();
    String[] accesses() default {};
    String visible() default "";
}