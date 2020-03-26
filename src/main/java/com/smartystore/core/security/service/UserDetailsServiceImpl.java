package com.smartystore.core.security.service;

import com.smartystore.core.common.api.exception.ApiNotFoundException;
import com.smartystore.core.security.domain.UserPrincipal;
import com.smartystore.core.users.domain.User;
import com.smartystore.core.users.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username).
        orElseThrow(() -> new ApiNotFoundException(1, "User was not found"));
    return UserPrincipal.create(user);
  }

}
