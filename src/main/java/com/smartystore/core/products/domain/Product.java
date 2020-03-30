package com.smartystore.core.products.domain;

import com.smartystore.core.common.domain.BaseEntity;
import com.smartystore.core.common.domain.EntityStatus;
import com.smartystore.core.common.typedef.PostgreSQLEnumType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class Product extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String ean;
  private String imageUrl;
  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "product_type")
  @Type(type = "pgsql_enum")
  private ProductType productType;
  @Enumerated(EnumType.STRING)
  private EntityStatus status;
}
