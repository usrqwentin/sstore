package com.smartystore.core.profiles.api;

import com.smartystore.core.common.api.annotation.PageLink;
import com.smartystore.core.common.api.ApiPage;
import com.smartystore.core.profiles.api.viewmodel.ProfileReadDto;
import com.smartystore.core.profiles.domain.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@PageLink(value = "prev", uri = "/profiles?page={page}&rows={rows}&sort={sort}", variables = {"prevPage", "getRows", "getSort"}, visible = "isHasPrev")
@PageLink(value = "self", uri = "/profiles?page={page}&rows={rows}&sort={sort}", variables = {"getPage", "getRows", "getSort"})
@PageLink(value = "next", uri = "/profiles?page={page}&rows={rows}&sort={sort}", variables = {"nextPage", "getRows", "getSort"}, visible = "isHasNext")
@PageLink(value = "edit", uri = "/profiles", variables = {})
class ProfilePage extends ApiPage<ProfileReadDto> {

  ProfilePage(Page<Profile> profiles, PageRequest pageRequest) {
    List<ProfileModel> profilesModels = profiles.map(ProfileModel::fromEntity).getContent();
    this.getFilter().setPage(pageRequest.getPageNumber());
    this.getFilter().setRows(pageRequest.getPageSize());
    this.getFilter().setSort(pageRequest.getSort());
    this.getFilter().setTotalRowsCnt((int) profiles.getTotalElements());

    this.setData(profilesModels);
  }

}
