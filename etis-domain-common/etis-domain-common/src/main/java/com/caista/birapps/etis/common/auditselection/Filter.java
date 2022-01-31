/*
 * Modified by: tolentf
 * Last updated: May 15, 2020 12:24:51 PM
 */
package com.caista.birapps.etis.common.auditselection;

public class Filter {

  private String property;
  private String comparisonType;
  private Object value;

  public Filter() {
    super();
  }

  public Filter(String property, String comparisonType, Object value) {
    super();
    this.property = property;
    this.comparisonType = comparisonType;
    this.value = value;
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public String getComparisonType() {
    return comparisonType;
  }

  public void setComparisonType(String comparisonType) {
    this.comparisonType = comparisonType;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Filter [property=");
    builder.append(property);
    builder.append(", comparisonType=");
    builder.append(comparisonType);
    builder.append(", value=");
    builder.append(value);
    builder.append("]");
    return builder.toString();
  }

}
