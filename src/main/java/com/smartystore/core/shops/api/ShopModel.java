package com.smartystore.core.shops.api;

import com.smartystore.core.common.api.ApiModel;
import com.smartystore.core.shops.api.viewmodel.ShopReadDto;
import com.smartystore.core.shops.domain.Shop;

public class ShopModel extends ApiModel<ShopReadDto> {
  public ShopModel(ShopReadDto data) {
    super(data);
  }
  public static ShopModel fromEntity(Shop shop) {
    return new ShopModel(ShopReadDto.fromEntity(shop));
  }
}
