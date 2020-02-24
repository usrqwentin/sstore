package com.smartystore.core.profiles.repository;

import com.smartystore.core.profiles.domain.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long>, QueryByExampleExecutor<Profile> {
}
