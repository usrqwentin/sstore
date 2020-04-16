package com.smartystore.core.shops.api.viewmodel;

import com.smartystore.core.common.api.ApiData;
import com.smartystore.core.common.api.Role;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopEditDto extends ApiData {
  @NotBlank(message = "Shop name must be specified")
  private String name;
  @NotBlank(message = "Shop address must be specified")
  private String address;
}
