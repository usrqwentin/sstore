package com.smartystore.core.common.api.exception;

public class ApiNotFoundException extends ApiException {

  public ApiNotFoundException(int errorCode, String errorMessage) {
    super(errorCode, errorMessage);
  }
}
