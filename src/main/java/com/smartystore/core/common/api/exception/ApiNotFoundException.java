package com.smartystore.core.common.api.exception;

public class ApiNotFoundException extends ApiException {

  public ApiNotFoundException(String errorMessage) {
    super(404, errorMessage);
  }
}
