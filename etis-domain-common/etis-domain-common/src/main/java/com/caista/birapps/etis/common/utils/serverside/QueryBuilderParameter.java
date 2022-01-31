/*
  * Modified by: decinam
  * Last updated: Apr 15, 2019 11:16:54 AM
  */
package com.caista.birapps.etis.common.utils.serverside;

public class QueryBuilderParameter {
  private String column;
  private Object value;
  private String name;

  public QueryBuilderParameter(String column, Object value, String name) {
    super();
    this.column = column;
    this.value = value;
    this.name = name;
  }

  public String getColumn() {
    return column;
  }

  public void setColumn(String column) {
    this.column = column;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean isParamValid() {
    if (this.value != null && !"".equals(this.value) && this.column != null
        && !"".equals(this.column) && this.name != null && !"".equals(this.name)) {
      return true;
    }
    return false;
  }



}
