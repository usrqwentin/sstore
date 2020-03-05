package com.smartystore.core.users.api.viewmodel;

import com.smartystore.core.common.api.ApiData;
import com.smartystore.core.common.api.Role;
import com.smartystore.core.common.domain.EntityStatus;
import com.smartystore.core.users.domain.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserReadDto extends ApiData {
  private String login;
  private String password;
  private String fullname;
  private String email;
  private String phone;
  private String iconUri;
  private EntityStatus status;
  private Role role;

  public static UserReadDto fromEntity(User user) {
    UserReadDto vm = new UserReadDto();
    vm.setId(user.getId());
    vm.setLogin(user.getLogin());
    vm.setPassword(user.getPassword());
    vm.setFullname(user.getFullname());
    vm.setEmail(user.getEmail());
    vm.setPhone(user.getPhone());
    vm.setIconUri(user.getIconUri());
    vm.setRole(user.getRole());
    vm.setStatus(user.getStatus());
    return vm;
  }
}
