package com.smartystore.core.users.api;


import com.smartystore.core.common.api.ApiPage;
import com.smartystore.core.common.api.annotation.PageLink;
import com.smartystore.core.users.api.viewmodel.StaffReadDto;
import com.smartystore.core.users.domain.Staff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@PageLink(value = "prev", uri = "/owner/users?page={page}&rows={rows}&sort={sort}", variables = {"prevPage", "getRows",
    "getSort"}, visible = "isHasPrev")
@PageLink(value = "self", uri = "/owner/users?page={page}&rows={rows}&sort={sort}", variables = {"getPage", "getRows",
    "getSort"})
@PageLink(value = "next", uri = "/owner/users?page={page}&rows={rows}&sort={sort}", variables = {"nextPage", "getRows",
    "getSort"}, visible = "isHasNext")
@PageLink(value = "edit", uri = "owner/users", variables = {})
public class StaffPage extends ApiPage<StaffReadDto> {
  StaffPage(Page<Staff> staff, PageRequest pageRequest) {
    List<StaffModel> staffModels = staff.map(StaffModel::fromEntity).getContent();
    this.getFilter().setPage(pageRequest.getPageNumber());
    this.getFilter().setRows(pageRequest.getPageSize());
    this.getFilter().setSort(pageRequest.getSort());
    this.getFilter().setTotalRowsCnt((int) staff.getTotalElements());

    this.setData(staffModels);
  }
}
