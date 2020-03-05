package com.smartystore.core.users.domain.validation;

import com.smartystore.core.common.api.exception.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserException extends ValidationException {

  private UserException(String message, int id) {
    super(message, "user", id);
  }

  public static UserException create(String message, int id) {
    return new UserException(message, id);
  }
}
