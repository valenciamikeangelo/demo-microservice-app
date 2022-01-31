/*
  * Modified by: sarmier
  * Last updated: Sep 10, 2018 4:20:51 PM
  */
package com.caista.birapps.etis.common.utils.serverside;

import java.io.Serializable;

public class ServerSidePaginationRowParameter implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 5323021052863078086L;
  private String columnName;
  private Integer columnNum;
  private Integer priorityNum;
  private String direction;


  public ServerSidePaginationRowParameter() {
    super();
  }

  public ServerSidePaginationRowParameter(String columnName, Integer columnNum, Integer priorityNum,
      String direction) {
    super();
    this.columnName = columnName;
    this.columnNum = columnNum;
    this.priorityNum = priorityNum;
    this.direction = direction;
  }



  public String getColumnName() {
    return columnName;
  }



  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }



  public Integer getColumnNum() {
    return columnNum;
  }



  public void setColumnNum(Integer columnNum) {
    this.columnNum = columnNum;
  }



  public Integer getPriorityNum() {
    return priorityNum;
  }



  public void setPriorityNum(Integer priorityNum) {
    this.priorityNum = priorityNum;
  }



  public String getDirection() {
    return direction;
  }



  public void setDirection(String direction) {
    this.direction = direction;
  }



  @Override
  public String toString() {
    return "AdvancedSearchRowParam [columnName=" + columnName + ", columnNum=" + columnNum
        + ", priorityNum=" + priorityNum + ", direction=" + direction + "]";
  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((columnName == null)
        ? 0
        : columnName.hashCode());
    result = prime * result + ((columnNum == null)
        ? 0
        : columnNum.hashCode());
    result = prime * result + ((direction == null)
        ? 0
        : direction.hashCode());
    result = prime * result + ((priorityNum == null)
        ? 0
        : priorityNum.hashCode());
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
    ServerSidePaginationRowParameter other = (ServerSidePaginationRowParameter) obj;
    if (columnName == null) {
      if (other.columnName != null)
        return false;
    } else if (!columnName.equals(other.columnName))
      return false;
    if (columnNum == null) {
      if (other.columnNum != null)
        return false;
    } else if (!columnNum.equals(other.columnNum))
      return false;
    if (direction == null) {
      if (other.direction != null)
        return false;
    } else if (!direction.equals(other.direction))
      return false;
    if (priorityNum == null) {
      if (other.priorityNum != null)
        return false;
    } else if (!priorityNum.equals(other.priorityNum))
      return false;
    return true;
  }



}
