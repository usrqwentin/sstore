package com.smartystore.core.shops.api;

import com.smartystore.core.common.api.ApiPage;
import com.smartystore.core.common.api.annotation.PageLink;
import com.smartystore.core.shops.api.viewmodel.ShopReadDto;
import com.smartystore.core.shops.domain.Shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

//TODO: Refactor for user with @RequestMapping
@PageLink(value = "prev", uri = "/owner/shops?page={page}&rows={rows}&sort={sort}", variables = {"prevPage", "getRows",
    "getSort"}, visible = "isHasPrev")
@PageLink(value = "self", uri = "/owner/shops?page={page}&rows={rows}&sort={sort}", variables = {"getPage", "getRows",
    "getSort"})
@PageLink(value = "next", uri = "/owner/shops?page={page}&rows={rows}&sort={sort}", variables = {"nextPage", "getRows",
    "getSort"}, visible = "isHasNext")
@PageLink(value = "edit", uri = "/owner/shop", variables = {})
public class ShopPage extends ApiPage<ShopReadDto> {

  ShopPage(Page<Shop> shops, PageRequest pageRequest) {
    List<ShopModel> shopModels = shops.map(ShopModel::fromEntity).getContent();
    this.getFilter().setPage(pageRequest.getPageNumber());
    this.getFilter().setRows(pageRequest.getPageSize());
    this.getFilter().setSort(pageRequest.getSort());
    this.getFilter().setTotalRowsCnt((int) shops.getTotalElements());

    this.setData(shopModels);
  }
}
