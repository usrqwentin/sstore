package com.smartystore.core.users.api;

import com.smartystore.core.common.api.ApiModel;
import com.smartystore.core.common.api.annotation.ModelLink;
import com.smartystore.core.users.api.viewmodel.UserReadDto;
import com.smartystore.core.users.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@ModelLink(
    value = "self",
    uri = "/users/{id}",
    variables = "getId",
    accesses = {"ROLE_BCSU", "ROLE_BCUSER", "ROLE_ADMIN"})
@ModelLink(
    value = "edit",
    uri = "/users/{id}",
    variables = "getId",
    accesses = {"ROLE_BCSU", "ROLE_BCUSER"})
@Getter
@NoArgsConstructor
public class UserModel extends ApiModel<UserReadDto> {

  public UserModel(UserReadDto data) {
    super(data);
  }

  public static UserModel fromEntity(User user) {
    return new UserModel(UserReadDto.fromEntity(user));
  }

}
