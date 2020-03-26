package com.smartystore.core.common.util;

import org.springframework.data.domain.Sort;

public final class Paging {
  protected Paging() {
  }

  public static Sort of(String sort) {
    if (sort.toUpperCase().endsWith("DESC")) {
      String fieldName = sort.substring(0, sort.length() - "DESC".length());
      return Sort.by(Sort.Order.desc(fieldName));
    }

    if (sort.toUpperCase().endsWith("ASC")) {
      String fieldName = sort.substring(0, sort.length() - "ASC".length());
      return Sort.by(Sort.Order.asc(fieldName));
    }

    throw new IllegalArgumentException("Invalid sort parameter");
  }
}
