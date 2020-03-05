package com.smartystore.core.users.service;

import com.smartystore.core.common.api.exception.ApiNotFoundException;
import com.smartystore.core.common.service.BaseService;
import com.smartystore.core.users.api.viewmodel.UserEditDto;
import com.smartystore.core.users.domain.User;
import com.smartystore.core.users.domain.validation.UserException;
import com.smartystore.core.users.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.smartystore.core.common.domain.EntityStatus.ACTIVE;

@Data
@Service
@EqualsAndHashCode(callSuper = true)
public class UserService extends BaseService<User> {

  private final UserRepository repository;

  public User createUser(UserEditDto dto) {
    repository.findByEmail(dto.getEmail()).ifPresent((user) -> {
      throw UserException.create("A user with the specified email address already exists", 2);
    });
    User user = User.builder()
        .login(dto.getLogin())
        .password(dto.getPassword())
        .fullname(dto.getFullname())
        .email(dto.getEmail())
        .phone(dto.getPhone())
        .iconUri(dto.getIconUri())
        .role(dto.getRole())
        .status(ACTIVE)
        .build();
    return repository.save(user);
  }

  public User updateUser(Long id, UserEditDto dto) {
    User user = repository.findById(id).orElseThrow(() -> new ApiNotFoundException(1, "User was not found"));
    user.setLogin(dto.getLogin());
    user.setPassword(dto.getPassword());
    user.setFullname(dto.getFullname());
    user.setEmail(dto.getEmail());
    user.setPhone(dto.getPhone());
    user.setIconUri(dto.getIconUri());
    user.setRole(dto.getRole());
    user.setStatus(dto.getStatus());
    return repository.save(user);
  }

}
