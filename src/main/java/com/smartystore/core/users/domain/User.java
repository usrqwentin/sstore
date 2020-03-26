package com.smartystore.core.users.domain;

import com.smartystore.core.common.api.Role;
import com.smartystore.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {
  private String username;
  private String password;
  private String fullname;
  private String email;
  private String phone;
  @Column(name = "icon_uri")
  private String iconUri;
  @Enumerated(EnumType.STRING)
  private Role role;

}

