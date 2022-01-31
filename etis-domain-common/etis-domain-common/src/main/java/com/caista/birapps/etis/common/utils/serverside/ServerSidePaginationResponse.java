/*
  * Modified by: decinam
  * Last updated: Apr 15, 2019 9:07:33 AM
  */
package com.caista.birapps.etis.common.utils.serverside;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ServerSidePaginationResponse<T> implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private List<T> result;
  private Long totalCount;


  public ServerSidePaginationResponse() {
    super();
  }


  public ServerSidePaginationResponse(List<T> result, Long totalCount) {
    super();
    this.result = result;
    this.totalCount = totalCount;
  }


  public List<T> getResult() {
    return result;
  }


  public void setResult(List<T> result) {
    this.result = result;
  }


  public Long getTotalCount() {
    return totalCount;
  }


  public void setTotalCount(Long totalCount) {
    this.totalCount = totalCount;
  }


  public static long getSerialversionuid() {
    return serialVersionUID;
  }


  @Override
  public String toString() {
    return "AdvancedSearchResponse [result=" + result + ", totalCount=" + totalCount + "]";
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int lresult = 1;
    lresult = prime * lresult + ((this.result == null)
        ? 0
        : this.result.hashCode());
    lresult = prime * lresult + ((totalCount == null)
        ? 0
        : totalCount.hashCode());
    return lresult;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ServerSidePaginationResponse other = (ServerSidePaginationResponse) obj;
    if (result == null) {
      if (other.result != null)
        return false;
    } else if (!result.equals(other.result))
      return false;
    if (totalCount == null) {
      if (other.totalCount != null)
        return false;
    } else if (!totalCount.equals(other.totalCount))
      return false;
    return true;
  }


}
