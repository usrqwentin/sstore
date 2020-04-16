package com.smartystore.core.common.domain;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@FilterDef(name="ownerFilter", parameters = {@ParamDef(name = "ownerId", type = "integer")})
@Filter(name = "ownerFilter", condition = "owner_id = :ownerId")
public interface Rentable {
  void setRenterId(Long renterId);
}
