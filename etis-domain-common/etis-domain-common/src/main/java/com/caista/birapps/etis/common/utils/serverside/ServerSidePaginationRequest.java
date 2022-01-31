/*
 * Modified by: santojo
 * Last updated: Sep 12, 2018 1:59:31 PM
 */
package com.caista.birapps.etis.common.utils.serverside;

import java.io.Serializable;

public class ServerSidePaginationRequest<T> implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -5674131893452680993L;
  private T clientParam;
  private ServerSidePaginationRowParameter columnParam;
  private Integer pageSize;
  private Integer pageIndex;

  public ServerSidePaginationRequest() {
    super();
  }

  public ServerSidePaginationRequest(T clientParam, ServerSidePaginationRowParameter columnParam,
      Integer pageSize, Integer pageIndex) {
    super();
    this.clientParam = clientParam;
    this.columnParam = columnParam;
    this.pageSize = pageSize;
    this.pageIndex = pageIndex;
  }


  public T getClientParam() {
    return clientParam;
  }

  public void setClientParam(T clientParam) {
    this.clientParam = clientParam;
  }


  public ServerSidePaginationRowParameter getColumnParam() {
    return columnParam;
  }

  public void setColumnParam(ServerSidePaginationRowParameter columnParam) {
    this.columnParam = columnParam;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getPageIndex() {
    return pageIndex;
  }

  public void setPageIndex(Integer pageIndex) {
    this.pageIndex = pageIndex;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "ServerSidePaginationRequest [clientParam=" + clientParam + ", columnParam="
        + columnParam + ", pageSize=" + pageSize + ", pageIndex=" + pageIndex + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((clientParam == null)
        ? 0
        : clientParam.hashCode());
    result = prime * result + ((pageIndex == null)
        ? 0
        : pageIndex.hashCode());
    result = prime * result + ((pageSize == null)
        ? 0
        : pageSize.hashCode());
    result = prime * result + ((columnParam == null)
        ? 0
        : columnParam.hashCode());
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
    ServerSidePaginationRequest other = (ServerSidePaginationRequest) obj;
    if (clientParam == null) {
      if (other.clientParam != null)
        return false;
    } else if (!clientParam.equals(other.clientParam))
      return false;
    if (pageIndex == null) {
      if (other.pageIndex != null)
        return false;
    } else if (!pageIndex.equals(other.pageIndex))
      return false;
    if (pageSize == null) {
      if (other.pageSize != null)
        return false;
    } else if (!pageSize.equals(other.pageSize))
      return false;
    if (columnParam == null) {
      if (other.columnParam != null)
        return false;
    } else if (!columnParam.equals(other.columnParam))
      return false;
    return true;
  }



}
