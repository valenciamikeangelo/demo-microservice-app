/*
 * Modified by: santojo
 * Last updated: Nov 12, 2018 4:13:48 PM
 */
package com.caista.birapps.etis.domain.sysad.reference.util;

public class ReferencePropertyLengthWrapper {

  private String name;
  private int length;

  public ReferencePropertyLengthWrapper() {
    super();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  @Override
  public String toString() {
    return "ReferencePropertyLengthWrapper [name=" + name + ", length=" + length + "]";
  }
}
