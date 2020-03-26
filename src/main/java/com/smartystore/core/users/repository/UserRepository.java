package com.smartystore.core.users.repository;

import com.smartystore.core.common.repository.BaseRepository;
import com.smartystore.core.users.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    @Query("SELECT u FROM User u WHERE username = :username "
               + "AND status = com.smartystore.core.common.domain.EntityStatus.ACTIVE")
    Optional<User> findByUsername(@Param("username") String username);

}
