package com.smartystore.core.shops.service;

import com.smartystore.core.common.Application;
import com.smartystore.core.common.api.exception.ApiNotFoundException;
import com.smartystore.core.common.service.BaseService;
import com.smartystore.core.shops.api.viewmodel.ShopEditDto;
import com.smartystore.core.users.domain.Owner;
import com.smartystore.core.shops.domain.Shop;
import com.smartystore.core.shops.repository.ShopRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.smartystore.core.common.domain.EntityStatus.ACTIVE;

@Data
@Service
@EqualsAndHashCode(callSuper = true)
public class ShopService extends BaseService<Shop> {
  private final ShopRepository repository;

  @Transactional
  public Shop createShopAndAttachOwner(ShopEditDto dto) {
    Shop shop = new Shop();
    fillShopFromDto(shop, dto);
    shop.setStatus(ACTIVE);
    shop.setOwner(Application.getAuthorizedOwner());
    return repository.save(shop);
  }

  public Shop findByIdOfOwner(Long id) {
    return repository.findByIdAndOwner(id, Application.getAuthorizedOwner()).orElseThrow(
        () -> new ApiNotFoundException("Shop was not found")
    );
  }

  public Page<Shop> listOfOwner(PageRequest pageRequest) {
    return getRepository().findByOwner(Application.getAuthorizedOwner(), pageRequest);
  }

  private void fillShopFromDto(Shop shop, ShopEditDto dto) {
    shop.setName(dto.getName());
    shop.setAddress(dto.getAddress());
  }
}
