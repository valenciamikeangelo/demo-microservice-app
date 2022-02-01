/*
  * Modified by: bacosal
  * Last updated: Sep 3, 2018 5:16:04 PM
  */
package com.caista.birapps.etis.domain.trs.utils.paginated;

import java.util.List;

public class PaginatedSearchRequest<T> {

  private T param;
  private Integer offset;
  private Integer limit;
  private List<PaginatedSearchColumnSort> columnSort;

  private Integer computedOffset;

  public T getParam() {
    return param;
  }

  public void setParam(T param) {
    this.param = param;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public List<PaginatedSearchColumnSort> getColumnSort() {
    return columnSort;
  }

  public void setColumnSort(List<PaginatedSearchColumnSort> columnSort) {
    this.columnSort = columnSort;
  }

  public Integer getComputedOffset() {
    if (offset != null && limit != null) {
      computedOffset = (offset - 1) * limit;
    }
    return computedOffset;
  }

  @Override
  public String toString() {
    return "PaginatedSearchRequest [param=" + param + ", offset=" + offset + ", limit=" + limit
        + ", columnSort=" + columnSort + "]";
  }


}
