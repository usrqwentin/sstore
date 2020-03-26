package com.smartystore.core.security.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDto implements Serializable {

  private static final long serialVersionUID = -8091879091924046844L;
  private String jwttoken;

}
