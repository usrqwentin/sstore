package com.smartystore.core.users.domain;

import com.smartystore.core.common.api.Role;
import com.smartystore.core.shops.domain.Shop;
import com.smartystore.core.users.domain.User;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@DiscriminatorValue(Role.Constants.ROLE_STAFF)
@Entity
@Table(name = "users")
public class Staff extends User {
  @ManyToMany(mappedBy = "staff", targetEntity = Shop.class)
  Set<Shop> shops;
}
