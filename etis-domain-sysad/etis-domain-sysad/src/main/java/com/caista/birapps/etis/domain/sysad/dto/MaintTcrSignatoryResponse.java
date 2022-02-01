/*
  * Modified by: logronj
  * Last updated: 07 7, 20 4:26:21 PM
  */
package com.caista.birapps.etis.domain.sysad.dto;

public class MaintTcrSignatoryResponse {
  private String fullName;
  private String position;

  public MaintTcrSignatoryResponse(String fullName, String position) {
    super();
    this.fullName = fullName;
    this.position = position;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }



}
