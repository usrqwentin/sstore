package com.smartystore.core.products.api;

import com.smartystore.core.common.api.ApiModel;
import com.smartystore.core.common.api.annotation.ModelLink;
import com.smartystore.core.products.api.viewmodel.ProductReadDto;
import com.smartystore.core.products.domain.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;

@ModelLink(
    value = "self",
    uri = "/products/{id}",
    variables = "getId",
    accesses = {"ROLE_BCSU", "ROLE_BCUSER", "ROLE_ADMIN"})
@ModelLink(
    value = "edit",
    uri = "/products/{id}",
    variables = "getId",
    accesses = {"ROLE_BCSU", "ROLE_BCUSER"})
@Getter
@NoArgsConstructor
public class ProductModel extends ApiModel<ProductReadDto> {

  public ProductModel(ProductReadDto data) {
    super(data);
  }

  public static ProductModel fromEntity(Product product) {
    return new ProductModel(ProductReadDto.fromEntity(product));
  }
}
