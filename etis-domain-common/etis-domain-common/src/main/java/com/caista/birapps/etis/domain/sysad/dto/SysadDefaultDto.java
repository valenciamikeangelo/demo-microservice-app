/*
  * Modified by: obregoj
  * Last updated: Nov 4, 2019 3:53:36 PM
  */
package com.caista.birapps.etis.domain.sysad.dto;

import java.io.Serializable;

public class SysadDefaultDto implements Serializable {

  private static final long serialVersionUID = 1L;
  private String id;
  private String code;
  private String description;


  public SysadDefaultDto() {
    super();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }



}
