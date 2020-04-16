package com.smartystore.core.users.api;

import com.smartystore.core.common.api.ApiModel;
import com.smartystore.core.common.api.annotation.ModelLink;
import com.smartystore.core.users.api.viewmodel.StaffReadDto;
import com.smartystore.core.users.api.viewmodel.UserReadDto;
import com.smartystore.core.users.domain.Staff;
import com.smartystore.core.users.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@ModelLink(
    value = "self",
    uri = "/owner/users/{id}",
    variables = "getId",
    accesses = {"ROLE_BCSU", "ROLE_BCUSER", "ROLE_ADMIN"})
@ModelLink(
    value = "edit",
    uri = "/owner/users/{id}",
    variables = "getId",
    accesses = {"ROLE_BCSU", "ROLE_BCUSER"})
@Getter
@NoArgsConstructor
public class StaffModel extends ApiModel<StaffReadDto> {

  public StaffModel(StaffReadDto data) {
    super(data);
  }

  public static StaffModel fromEntity(Staff user) {
    return new StaffModel(StaffReadDto.fromEntity(user));
  }

}
