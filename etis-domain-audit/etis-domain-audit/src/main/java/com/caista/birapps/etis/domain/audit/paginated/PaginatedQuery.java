/*
 * Modified by: fuentem
 * Last updated: Aug 20, 2018 11:07:34 AM
 */
package com.caista.birapps.etis.domain.audit.paginated;

public class PaginatedQuery {

  private String queryString;
  private String orderByString;
  private String offsetString;
  private String limitString;

  public PaginatedQuery() {
    super();
  }

  public PaginatedQuery(String queryString, String orderByString, String offsetString,
      String limitString) {
    super();
    this.queryString = queryString;
    this.orderByString = orderByString;
    this.offsetString = offsetString;
    this.limitString = limitString;
  }

  public String getQueryString() {
    return queryString;
  }

  public void setQueryString(String queryString) {
    this.queryString = queryString;
  }

  public String getOrderByString() {
    return orderByString;
  }

  public void setOrderByString(String orderByString) {
    this.orderByString = orderByString;
  }

  public String getOffsetString() {
    return offsetString;
  }

  public void setOffsetString(String offsetString) {
    this.offsetString = offsetString;
  }

  public String getLimitString() {
    return limitString;
  }

  public void setLimitString(String limitString) {
    this.limitString = limitString;
  }

  @Override
  public String toString() {
    return "PaginatedQuery [queryString=" + queryString + ", orderByString=" + orderByString
        + ", offsetString=" + offsetString + ", limitString=" + limitString + "]";
  }

}
