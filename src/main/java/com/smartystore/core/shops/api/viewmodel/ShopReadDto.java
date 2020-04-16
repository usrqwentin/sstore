package com.smartystore.core.shops.api.viewmodel;

import com.smartystore.core.common.api.ApiData;
import com.smartystore.core.common.api.Role;
import com.smartystore.core.common.domain.EntityStatus;
import com.smartystore.core.shops.domain.Shop;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ShopReadDto extends ApiData {
  private String name;
  private String address;
  private EntityStatus status;

  public static ShopReadDto fromEntity(Shop shop) {
    ShopReadDto vm = new ShopReadDto();
    vm.setId(shop.getId());
    vm.setName(shop.getName());
    vm.setAddress(shop.getAddress());
    vm.setStatus(shop.getStatus());
    return vm;
  }
}
