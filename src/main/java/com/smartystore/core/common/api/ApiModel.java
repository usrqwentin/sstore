package com.smartystore.core.common.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartystore.core.common.api.annotation.ModelLink;

import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApiModel<T extends ApiData> {
  private Set<Link> links = new HashSet<>();
  private T data;


  public ApiModel(T data) {
    setData(data);
  }

  public void setData(T data) {
    this.data = data;
    buildLink();
  }

  @JsonIgnore
  public Long getId() {
    return data.getId();
  }

  public void addLink(String rel, String urlName, Object... argumentValues) {
    buildUrlFromUrl(rel, urlName, argumentValues);
  }

  public void buildUrlFromUrl(String rel, String urlName, Object... argumentValues) {
    String url = UriComponentsBuilder.fromUriString(urlName)
        .buildAndExpand(argumentValues)
        .toString()
        .replaceAll("[^?=&]+=(&|$)", "")
        .replaceAll("&$", "")
        .replaceAll("[?]$", "")
        .replaceAll(" ", "%20");
    links.add(new Link(rel, url));
  }

  private void buildLink() {
    ModelLink[] modelLinks = getClass().getAnnotationsByType(ModelLink.class);
    if (modelLinks == null) {
      return;
    }

    for (ModelLink modelLink : modelLinks) {
      List<String> permittedRoles = Arrays.asList(modelLink.accesses());

      Object[] objects = new Object[modelLink.variables().length];
      for (int i = 0; i < modelLink.variables().length; i++) {
        try {
          String name = modelLink.variables()[i];
          Method method = data.getClass().getMethod(name);
          objects[i] = method.invoke(data);
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
      addLink(modelLink.value(), modelLink.uri(), objects);
    }
  }

  private boolean isLinkVisible(ModelLink modelLink) {
    String visibleMethodName = modelLink.visible();
    if (!visibleMethodName.isEmpty()) {
      Class<? extends ApiModel> clazz = this.getClass();
      try {
        Method method = clazz.getMethod(visibleMethodName);
        if (method == null) {
          return false;
        }
        return (boolean) method.invoke(this);
      } catch (Exception e) {
        throw new RuntimeException(
            String.format(
                "ApiModel::isLinkVisible on method %s::%s: %s",
                clazz.getSimpleName(),
                visibleMethodName,
                e.getMessage()),
            e);
      }
    }
    return true;
  }
}
