package com.smartystore.core.common.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smartystore.core.common.api.annotation.PageLink;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Method;
import java.util.*;

@Getter
@Setter
@JsonIgnoreProperties({"page", "rows", "auth", "hasNext", "hasPrev"})
public class ApiPage<T extends ApiData> {
  private Set<Link> links;
  private Filter filter;
  private List<? extends ApiModel<T>> data;

  public ApiPage() {
    this.filter = new Filter();
    this.links = new HashSet<>();
  }

  @Getter
  @Setter
  @JsonIgnoreProperties({"hasNext", "hasPrev"})
  public static class Filter {
    private Integer page = 1;
    private Integer rows = 20;
    private String sort = "idASC";
    private int totalRowsCnt = 0;
    private Map<String, Object> queries = new HashMap<>();

    public boolean isHasNext() {
      double tpd = (double) totalRowsCnt / rows;
      long totalPages = (long) Math.ceil(tpd);
      return totalPages > page;
    }

    public boolean isHasPrev() {
      double tpd = (double) totalRowsCnt / rows;
      long totalPages = (long) Math.ceil(tpd);
      return totalPages > 1 && page > 1;
    }

    public <A> void put(String name, A value) {
      if (name != null && value != null) {
        this.queries.put(name, value);
      }
    }

    public void setPage(Integer page) {
      this.page = page + 1;
    }

    public void setSort(Sort sort) {
      String[] parts = sort.toString().split(": ", 2);
      this.sort = parts[0] + parts[1].toUpperCase();
    }

    public <A> A get(String name) {
      return (A) this.queries.get(name);
    }

  }

  public int getPage() {
    return getFilter().getPage();
  }

  public int nextPage() {
    int nextPage = getFilter().getPage();
    return ++nextPage;
  }

  public int prevPage() {
    int prevPage = getFilter().getPage();
    return --prevPage;
  }

  public int getRows() {
    return getFilter().getRows();
  }

  @JsonIgnore
  public String getSort() {
    return getFilter().getSort();
  }

  public void setData(List<? extends ApiModel<T>> data) {
    this.data = data;
    buildLink();
  }

  public void addLink(String rel, String urlName, Object... argumentValues) {
    buildUrlFromUrl(rel, urlName, argumentValues);
  }

  public boolean isHasNext() {
    return getFilter().isHasNext();
  }

  public boolean isHasPrev() {
    return getFilter().isHasPrev();
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
    PageLink[] pageLinks = getClass().getAnnotationsByType(PageLink.class);
    if (pageLinks == null) {
      return;
    }

    for (PageLink pageLink : pageLinks) {
      List<String> permittedLinks = Arrays.asList(pageLink.accesses());
        Object[] objects = new Object[pageLink.variables().length];
        for (int i = 0; i < pageLink.variables().length; i++) {

          try {
            String name = pageLink.variables()[i];
            Method method = getClass().getMethod(name);
            objects[i] = method.invoke(this);
          } catch (Exception exc) {
            // throw new RuntimeException(exc);
          }
        }
        addLink(pageLink.value(), pageLink.uri(), objects);
      }
    }

  private boolean isLinkVisible(PageLink pageLink) {
    try {
      if (!pageLink.visible().isEmpty()) {
        Method vMethod = getClass().getMethod(pageLink.visible());
        if (vMethod != null) {
          return (boolean) vMethod.invoke(this);
        }
      }
    } catch (Exception exc) {
//      throw new RuntimeException(exc);
    }
    return true;
  }
}
