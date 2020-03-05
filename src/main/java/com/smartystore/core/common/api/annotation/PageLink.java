package com.smartystore.core.common.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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