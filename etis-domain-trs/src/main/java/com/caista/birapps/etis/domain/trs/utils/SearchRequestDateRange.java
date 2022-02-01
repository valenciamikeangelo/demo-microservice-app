/*
 * Modified by: fuentem
 * Last updated: Aug 20, 2018 10:25:54 AM
 */
package com.caista.birapps.etis.domain.trs.utils;

public class SearchRequestDateRange {

  private String dateFromString;
  private String dateToString;

  public SearchRequestDateRange() {
    super();
  }

  public SearchRequestDateRange(String dateFromString, String dateToString) {
    super();
    this.dateFromString = dateFromString;
    this.dateToString = dateToString;
  }

  public String getDateFromString() {
    return dateFromString;
  }

  public void setDateFromString(String dateFromString) {
    this.dateFromString = dateFromString;
  }

  public String getDateToString() {
    return dateToString;
  }

  public void setDateToString(String dateToString) {
    this.dateToString = dateToString;
  }

  @Override
  public String toString() {
    return "SearchRequestDateRange [dateFromString=" + dateFromString + ", dateToString="
        + dateToString + "]";
  }
}
