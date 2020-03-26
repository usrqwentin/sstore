package com.smartystore.core.users.api.viewmodel;

import com.smartystore.core.common.api.ApiData;
import com.smartystore.core.common.api.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEditDto extends ApiData {
  @NotBlank(message = "Username must be specified")
  private String username;
  @NotBlank(message = "Password must be specified")
  private String password;
  private String fullname;
  @NotBlank(message = "Email must be specified")
  @Email
  private String email;
  @NotBlank(message = "Phone must be specified")
  private String phone;
  private String iconUri;
  private Role role;

}
