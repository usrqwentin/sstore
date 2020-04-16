package com.smartystore.core.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T> extends PagingAndSortingRepository<T, Long>, QueryByExampleExecutor<T> {

  @Query("SELECT t FROM #{#entityName} t WHERE status = com.smartystore.core.common.domain.EntityStatus.ACTIVE")
  Page<T> findAll(Pageable pageable);

  @Query("SELECT t FROM #{#entityName} t WHERE id = :id "
      + "AND status = com.smartystore.core.common.domain.EntityStatus.ACTIVE")
  Optional<T> findById(@Param("id") Long id);

  //TODO: validation failed?
  /*@Modifying
  @Query("UPDATE #{#entityName} SET status = com.smartystore.core.common.domain.EntityStatus.INACTIVE WHERE id = :id ")
  void deleteById(@Param("id") Long id);*/

}
