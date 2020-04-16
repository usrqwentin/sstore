package com.smartystore.core.shops.api;

import com.smartystore.core.common.api.DataContainer;
import com.smartystore.core.common.util.Paging;
import com.smartystore.core.shops.api.viewmodel.ShopEditDto;
import com.smartystore.core.shops.domain.Shop;
import com.smartystore.core.shops.service.ShopService;
import com.smartystore.core.users.repository.UserRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("owner/shops")
@AllArgsConstructor
@PreAuthorize("hasRole('OWNER')")
public class ShopController {
  private ShopService shopService;

  @PostMapping
  public ResponseEntity<ShopModel> createShop(@RequestBody @Valid DataContainer<ShopEditDto> viewModel) {
    Shop shop = shopService.createShopAndAttachOwner(viewModel.getData());
    return new ResponseEntity<>(ShopModel.fromEntity(shop), HttpStatus.CREATED);
  }

  @GetMapping
  public ShopPage listShops(
      @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
      @RequestParam(value = "rows", required = false, defaultValue = "20") Integer rows,
      @RequestParam(value = "sort", required = false, defaultValue = "idDesc") String sort
  ) {
    PageRequest pageRequest = PageRequest.of(page - 1, rows, Paging.of(sort));

    return new ShopPage(shopService.listOfOwner(pageRequest), pageRequest);
  }

  @GetMapping("/{id}")
  public ShopModel getShop(@PathVariable("id") Long userId) {
    return ShopModel.fromEntity(shopService.findByIdOfOwner(userId));
  }

  private UserRepository userRepository;

  //TODO delete implementation cascade
}
