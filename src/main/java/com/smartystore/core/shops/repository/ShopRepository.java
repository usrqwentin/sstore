package com.smartystore.core.shops.repository;

import com.smartystore.core.common.repository.BaseRepository;
import com.smartystore.core.users.domain.Owner;
import com.smartystore.core.shops.domain.Shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ShopRepository extends BaseRepository<Shop> {

  //TODO: Non active implementation
  @Transactional(readOnly = true)
  Page<Shop> findByOwner(Owner owner, Pageable pageable);

  Optional<Shop> findByIdAndOwner(Long id, Owner owner);
}
