package com.smartystore.core.security.api;

import com.smartystore.core.security.dto.JwtRequestDto;
import com.smartystore.core.security.dto.JwtResponseDto;
import com.smartystore.core.security.service.UserDetailsServiceImpl;
import com.smartystore.core.security.util.JwtUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

  private final JwtUtil jwtUtil;
  private final AuthenticationManager authenticationManager;
  private final UserDetailsServiceImpl userDetailsService;

  @PostMapping("/authenticate")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequestDto authenticationRequest)
      throws Exception {
    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String token = jwtUtil.generateToken(userDetails);
    return ResponseEntity.ok(new JwtResponseDto(token));
  }

  private void authenticate(String username, String password) throws Exception {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (Exception e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }

}
