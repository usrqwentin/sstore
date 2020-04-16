package com.smartystore.core.users.repository;

import com.smartystore.core.common.repository.BaseRepository;
import com.smartystore.core.users.domain.Owner;
import com.smartystore.core.users.domain.Staff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StaffRepository extends BaseRepository<Staff> {
  @Transactional(readOnly = true)
  Page<Staff> findByOwner(Owner owner, Pageable pageable);
  Optional <Staff> findByIdAndOwner(Long id, Owner owner);
}
