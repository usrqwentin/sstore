package com.smartystore.core.products.validation;

import com.smartystore.core.common.api.exception.ValidationException;

public class ProductException extends ValidationException {
  public ProductException(String message, int id) { super(message, "product", id); }
  public static ProductException create(String message, int id) {
    return new ProductException(message, id);
  }
}
