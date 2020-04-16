package com.smartystore.core.common.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
  ROLE_ANONYMOUS(Constants.ROLE_ANONYMOUS),
  ROLE_ADMIN(Constants.ROLE_ADMIN),
  ROLE_OWNER(Constants.ROLE_OWNER),
  ROLE_STAFF(Constants.ROLE_STAFF);

  @Getter
  private final String value;

  public static class Constants {
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_OWNER = "ROLE_OWNER";
    public static final String ROLE_STAFF = "ROLE_STAFF";
  }
}
