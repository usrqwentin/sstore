package com.smartystore.core.security.api.exception;

import org.springframework.security.access.AccessDeniedException;

public class CustomAccessDeniedException extends AccessDeniedException {
  public CustomAccessDeniedException(String msg) {
    super(msg);
  }

  public CustomAccessDeniedException(String msg, Throwable t) {
    super(msg, t);
  }
}
