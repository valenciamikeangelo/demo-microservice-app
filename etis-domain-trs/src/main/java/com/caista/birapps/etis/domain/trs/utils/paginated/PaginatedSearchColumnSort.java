/*
 * Modified by: fuentem
 * Last updated: Aug 22, 2018 6:45:28 PM
 */
package com.caista.birapps.etis.domain.trs.utils.paginated;

public class PaginatedSearchColumnSort implements Comparable<PaginatedSearchColumnSort> {

  private Integer columnIndex;
  private String direction;
  private Integer priority;

  public PaginatedSearchColumnSort() {
    super();
  }

  public PaginatedSearchColumnSort(Integer columnIndex, String direction, Integer priority) {
    super();
    this.columnIndex = columnIndex;
    this.direction = direction;
    this.priority = priority;
  }

  public Integer getColumnIndex() {
    return columnIndex;
  }

  public void setColumnIndex(Integer columnIndex) {
    this.columnIndex = columnIndex;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  @Override
  public int compareTo(PaginatedSearchColumnSort arg0) {
    return this.priority - arg0.priority;
  }
}
