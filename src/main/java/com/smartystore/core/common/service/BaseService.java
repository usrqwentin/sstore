package com.smartystore.core.common.service;


import com.smartystore.core.common.api.exception.ApiNotFoundException;
import com.smartystore.core.common.domain.BaseEntity;
import com.smartystore.core.common.repository.BaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public abstract class BaseService<T extends BaseEntity> {

  public abstract BaseRepository<T> getRepository();

  @Transactional(readOnly = true)
  public Page<T> list(PageRequest pageRequest) {
    return getRepository().findAll(pageRequest);
  }

  @Transactional(readOnly = true)
  public T findById(Long id) {
    return getRepository().findById(id).orElseThrow(() -> new ApiNotFoundException(1, "Entity was not found"));
  }

  public void deleteById(Long id) {
    findById(id);
    getRepository().deleteById(id);
  }

}