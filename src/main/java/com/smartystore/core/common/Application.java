package com.smartystore.core.common;

import com.smartystore.core.common.exception.UnauthenticatedException;
import com.smartystore.core.security.domain.UserPrincipal;
import com.smartystore.core.users.domain.Owner;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Application {
  public static UserPrincipal getAuthorizedUserPrincipal() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth instanceof AnonymousAuthenticationToken) {
      throw new UnauthenticatedException();
    }

    return (UserPrincipal) auth.getPrincipal();
  }

  public static Owner getAuthorizedOwner() {
    Owner owner = new Owner();
    //TODO: Это безопасно?
    owner.setId(Application.getAuthorizedUserPrincipal().getId());
    return owner;
  }
}
