package com.smartystore.core.security.api.exception;

import org.springframework.security.access.AccessDeniedException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface AccessDeniedMapping {
  Class<? extends AccessDeniedException> value();

  String message() default "access denied";
}
