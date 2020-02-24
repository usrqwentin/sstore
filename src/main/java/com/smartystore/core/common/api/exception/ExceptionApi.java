package com.smartystore.core.common.api.exception;

import com.smartystore.core.profiles.domain.validation.ProfileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;

import static com.smartystore.core.common.api.exception.ExceptionModel.createModel;


@ControllerAdvice
public class ExceptionApi {
  private static final Logger LOG = LoggerFactory.getLogger(ExceptionApi.class);

  @ExceptionHandler(ValidationException.class)
  @ResponseBody
  public ExceptionModel handleValidationErrors(ValidationException ex, HttpServletResponse response) {
    if (ex.getId() == 20)
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    else
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    return createModel(ex.getMessage(), ex.getId());
  }

  @ExceptionHandler(ProfileException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionModel handleUserHasNoCompanyException(ProfileException ex) {
    return createModel(ex.getMessage(), 2);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionModel handleHibernateValidationErrors(ConstraintViolationException ex) {
    return createModel(ex.getMessage(), 1);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionModel handleHibernateValidationErrors(MethodArgumentNotValidException ex) {
    return createModel(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), 1);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionModel handleNotReadableException() {
    return createModel("required request body is missing", 1);
  }

  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public void handleUnauthorizedAccess2() {
  }

  @ExceptionHandler(ApiNotFoundException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ExceptionModel handleObjectNotFound(ApiException ex) {
    return createModel(ex.getErrorMessage(), ex.getErrorCode());
  }

  @ExceptionHandler(Exception.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ExceptionModel handleUnexpectedCheckedException(Exception t) {
    LOG.error("Unexpected server error", t);
    return createModel(t.getMessage(), 1000);
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ExceptionModel handleUnexpectedUncheckedException(RuntimeException t) {
    LOG.error("Unexpected server error", t);
    return createModel(t.getMessage(), 1000);
  }

}
