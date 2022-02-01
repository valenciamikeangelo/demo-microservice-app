/*
  * Modified by: obregoj
  * Last updated: Dec 17, 2018 4:53:24 PM
  */
package com.caista.birapps.etis.domain.sysad.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OfficeWithRr {
  private Long rdoId;
  private String rdoCode;
  private String rdoDescription;
  private Long rrId;
  private String rrCode;
  private String rrDescription;

  public OfficeWithRr() {
    super();
  }

  public Long getRdoId() {
    return rdoId;
  }

  public void setRdoId(Long rdoId) {
    this.rdoId = rdoId;
  }

  public String getRdoCode() {
    return rdoCode;
  }

  public void setRdoCode(String rdoCode) {
    this.rdoCode = rdoCode;
  }

  public String getRdoDescription() {
    return rdoDescription;
  }

  public void setRdoDescription(String rdoDescription) {
    this.rdoDescription = rdoDescription;
  }

  public Long getRrId() {
    return rrId;
  }

  public void setRrId(Long rrId) {
    this.rrId = rrId;
  }

  public String getRrCode() {
    return rrCode;
  }

  public void setRrCode(String rrCode) {
    this.rrCode = rrCode;
  }

  public String getRrDescription() {
    return rrDescription;
  }

  public void setRrDescription(String rrDescription) {
    this.rrDescription = rrDescription;
  }

  @Override
  public String toString() {
    return "OfficeWithRr [rdoId=" + rdoId + ", rdoCode=" + rdoCode + ", rdoDescription="
        + rdoDescription + ", rrId=" + rrId + ", rrCode=" + rrCode + ", rrDescription="
        + rrDescription + "]";
  }



}
