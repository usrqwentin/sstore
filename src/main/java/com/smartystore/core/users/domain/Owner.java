package com.smartystore.core.users.domain;

import com.smartystore.core.common.api.Role;
import com.smartystore.core.shops.domain.Shop;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@DiscriminatorValue(Role.Constants.ROLE_OWNER)
@Entity
public class Owner extends User {
  @OneToMany(mappedBy = "owner")
  Set<Shop> shops;

  @OneToMany(mappedBy = "owner")
  Set<Staff> staff;
}
