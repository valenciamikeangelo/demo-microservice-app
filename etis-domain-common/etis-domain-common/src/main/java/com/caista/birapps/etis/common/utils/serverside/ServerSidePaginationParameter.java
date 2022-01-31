/*
  * Modified by: sarmier
  * Last updated: Sep 10, 2018 4:20:30 PM
  */
package com.caista.birapps.etis.common.utils.serverside;

import java.io.Serializable;
import java.util.Map;

public class ServerSidePaginationParameter implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private Map<String, Object> columnValueMap;
  private String queryString;
  private Integer offset;
  private Integer limit;


  public ServerSidePaginationParameter() {
    super();
  }


  public Map<String, Object> getColumnValueMap() {
    return columnValueMap;
  }


  public void setColumnValueMap(Map<String, Object> columnValueMap) {
    this.columnValueMap = columnValueMap;
  }


  public String getQueryString() {
    return queryString;
  }


  public void setQueryString(String queryString) {
    this.queryString = queryString;
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


  public static long getSerialversionuid() {
    return serialVersionUID;
  }


  public ServerSidePaginationParameter(Map<String, Object> columnValueMap, String queryString,
      Integer offset, Integer limit) {
    super();
    this.columnValueMap = columnValueMap;
    this.queryString = queryString;
    this.offset = offset;
    this.limit = limit;
  }


  @Override
  public String toString() {
    return "AdvancedSearchServerParameter [columnValueMap=" + columnValueMap + ", queryString="
        + queryString + ", offset=" + offset + ", limit=" + limit + "]";
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((columnValueMap == null)
        ? 0
        : columnValueMap.hashCode());
    result = prime * result + ((limit == null)
        ? 0
        : limit.hashCode());
    result = prime * result + ((offset == null)
        ? 0
        : offset.hashCode());
    result = prime * result + ((queryString == null)
        ? 0
        : queryString.hashCode());
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ServerSidePaginationParameter other = (ServerSidePaginationParameter) obj;
    if (columnValueMap == null) {
      if (other.columnValueMap != null)
        return false;
    } else if (!columnValueMap.equals(other.columnValueMap))
      return false;
    if (limit == null) {
      if (other.limit != null)
        return false;
    } else if (!limit.equals(other.limit))
      return false;
    if (offset == null) {
      if (other.offset != null)
        return false;
    } else if (!offset.equals(other.offset))
      return false;
    if (queryString == null) {
      if (other.queryString != null)
        return false;
    } else if (!queryString.equals(other.queryString))
      return false;
    return true;
  }



}
