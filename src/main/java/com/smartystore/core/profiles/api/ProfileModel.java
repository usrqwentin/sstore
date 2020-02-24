package com.smartystore.core.profiles.api;

import com.smartystore.core.common.api.annotation.ModelLink;
import com.smartystore.core.common.api.ApiModel;
import com.smartystore.core.profiles.api.viewmodel.ProfileReadDto;
import com.smartystore.core.profiles.domain.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ModelLink(
    value = "self",
    uri = "/profiles/{id}",
    variables = "getId",
    accesses = {"ROLE_BCSU", "ROLE_BCUSER", "ROLE_ADMIN"})
@ModelLink(
    value = "edit",
    uri = "/profiles/{id}",
    variables = "getId",
    accesses = {"ROLE_BCSU", "ROLE_BCUSER"})
@Getter
@NoArgsConstructor
public class ProfileModel extends ApiModel<ProfileReadDto> {

  public ProfileModel(ProfileReadDto data) {
    super(data);
  }

  public static ProfileModel fromEntity(Profile profile) {
    return new ProfileModel(ProfileReadDto.fromEntity(profile));
  }

}
