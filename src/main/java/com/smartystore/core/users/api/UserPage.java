package com.smartystore.core.users.api;

import com.smartystore.core.common.api.ApiPage;
import com.smartystore.core.common.api.annotation.PageLink;
import com.smartystore.core.users.api.viewmodel.UserReadDto;
import com.smartystore.core.users.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@PageLink(value = "prev", uri = "/users?page={page}&rows={rows}&sort={sort}", variables = {"prevPage", "getRows",
    "getSort"}, visible = "isHasPrev")
@PageLink(value = "self", uri = "/users?page={page}&rows={rows}&sort={sort}", variables = {"getPage", "getRows",
    "getSort"})
@PageLink(value = "next", uri = "/users?page={page}&rows={rows}&sort={sort}", variables = {"nextPage", "getRows",
    "getSort"}, visible = "isHasNext")
@PageLink(value = "edit", uri = "/users", variables = {})
class UserPage extends ApiPage<UserReadDto> {

  UserPage(Page<User> users, PageRequest pageRequest) {
    List<UserModel> userModels = users.map(UserModel::fromEntity).getContent();
    this.getFilter().setPage(pageRequest.getPageNumber());
    this.getFilter().setRows(pageRequest.getPageSize());
    this.getFilter().setSort(pageRequest.getSort());
    this.getFilter().setTotalRowsCnt((int) users.getTotalElements());

    this.setData(userModels);
  }

}
