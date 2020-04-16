package com.smartystore.core.users.domain;

import com.smartystore.core.common.api.Role;
import com.smartystore.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(Role.Constants.ROLE_ADMIN)
public class User extends BaseEntity {
  private String username;
  private String password;
  private String fullname;
  private String email;
  private String phone;
  @Column(name = "icon_uri")
  private String iconUri;
  @Enumerated(EnumType.STRING)
  @Column(name = "role", updatable = false, insertable = false)
  private Role role;
  @Transient
  private Long owner_id;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "owner_id")
  private Owner owner;
}

