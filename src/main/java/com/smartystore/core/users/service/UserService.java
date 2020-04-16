package com.smartystore.core.users.service;

import com.smartystore.core.common.Application;
import com.smartystore.core.common.api.exception.ApiNotFoundException;
import com.smartystore.core.common.service.BaseService;
import com.smartystore.core.users.api.viewmodel.UserEditDto;
import com.smartystore.core.users.domain.Owner;
import com.smartystore.core.users.domain.Staff;
import com.smartystore.core.users.domain.User;
import com.smartystore.core.users.domain.validation.UserException;
import com.smartystore.core.users.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.smartystore.core.common.domain.EntityStatus.ACTIVE;

@Data
@Service
@EqualsAndHashCode(callSuper = true)
public class UserService extends BaseService<User> {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  public User createUser(UserEditDto dto) {
    repository.findByUsername(dto.getUsername()).ifPresent((user) -> {
      throw UserException.create("Username already exists", 2);
    });
    User user = new User();
    fillUserFromDto(user, dto);
    user.setStatus(ACTIVE);
    return repository.save(user);
  }

  public User updateUser(Long id, UserEditDto dto) {
    User user = repository.findById(id).orElseThrow(() -> new ApiNotFoundException("User was not found"));
    fillUserFromDto(user, dto);
    return repository.save(user);
  }

  private void fillUserFromDto(User user, UserEditDto dto) {
    user.setUsername(dto.getUsername());
    user.setPassword(passwordEncoder.encode(dto.getPassword()));
    user.setFullname(dto.getFullname());
    user.setEmail(dto.getEmail());
    user.setPhone(dto.getPhone());
    user.setIconUri(dto.getIconUri());
    user.setRole(dto.getRole());
  }
}
