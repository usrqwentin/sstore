package com.smartystore.core.users.service;

import com.smartystore.core.common.Application;
import com.smartystore.core.common.api.exception.ApiNotFoundException;
import com.smartystore.core.users.api.viewmodel.UserEditDto;
import com.smartystore.core.users.domain.Staff;
import com.smartystore.core.users.domain.User;
import com.smartystore.core.users.domain.validation.UserException;
import com.smartystore.core.users.repository.StaffRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.Data;

import static com.smartystore.core.common.domain.EntityStatus.ACTIVE;

@Data
@Service
public class StaffService {
  private final StaffRepository repository;

  public Page<Staff> findStaffOfCurrentOwner(PageRequest pageRequest) {
    return repository.findByOwner(Application.getAuthorizedOwner(), pageRequest);
  }

  public Staff findByIdOfCurrentOwner(Long id) {
    return repository.findByIdAndOwner(id, Application.getAuthorizedOwner())
        .orElseThrow(() -> new ApiNotFoundException("User was not found"));
  }

}
