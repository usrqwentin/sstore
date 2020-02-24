package com.smartystore.core.common.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.smartystore.core.common.api.ApiData;
import com.smartystore.core.common.api.ApiModel;

public final class ExceptionModel extends ApiModel<ApiData> {
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class ExceptionApiData extends ApiData {

    private String message;

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }

  public static ExceptionModel createModel(String message, int code) {
    ExceptionModel.ExceptionApiData data = new ExceptionModel.ExceptionApiData();
    data.setId((long) code);
    data.setMessage(message);

    ExceptionModel model = new ExceptionModel();
    model.setData(data);
    return model;
  }

}
