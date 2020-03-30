package com.smartystore.core.products.api.viewmodel;

import com.smartystore.core.common.api.ApiData;
import com.smartystore.core.products.domain.Product;
import com.smartystore.core.products.domain.ProductType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductReadDto extends ApiData {
  private String name;
  private String ean;
  private String imageUrl;
  private ProductType productType;

  public static ProductReadDto fromEntity(Product product){
    ProductReadDto vm = new ProductReadDto();
    vm.setId(product.getId());
    vm.setName(product.getName());
    vm.setEan(product.getEan());
    vm.setImageUrl(product.getImageUrl());
    vm.setProductType(product.getProductType());
    return vm;
  }
}



