package com.smartystore.core.shops.domain;

import com.smartystore.core.common.domain.BaseEntity;
import com.smartystore.core.users.domain.Owner;
import com.smartystore.core.users.domain.Staff;

import org.hibernate.annotations.Where;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "shops")
public class Shop extends BaseEntity {
  private String name;
  private String address;

  //Keep it
  /*@ManyToOne
  @JoinTable(name = "user_shop",
      joinColumns = {@JoinColumn(name = "shop_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
  )
  private Owner owner;*/

  //TODO: deal with cascade
  @ManyToOne
  @JoinColumn(name="owner_id")
  private Owner owner;

  @ManyToMany(cascade = CascadeType.PERSIST, targetEntity = Staff.class)
  @JoinTable(name = "user_shop",
      joinColumns = {@JoinColumn(name = "shop_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
  )
  @Where(clause = "role = 'ROLE_STAFF'")
  private Set<Staff> staff;
}