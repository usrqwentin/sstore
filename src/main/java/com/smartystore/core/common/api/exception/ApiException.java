package com.smartystore.core.common.api.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
  private final int errorCode;
  private final String errorMessage;

  public ApiException(int errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  @Override
  public String getMessage() {
    return errorMessage;
  }
}
