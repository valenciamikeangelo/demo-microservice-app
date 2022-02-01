/*
  * Modified by: logronj
  * Last updated: Oct 22, 2018 2:36:03 PM
*/
package com.caista.birapps.etis.domain.sysad.searchcriteria;

import java.util.Date;

public class CaseOutlineHeaderRequest {

  private String code;
  private String description;
  private Date effectiveDate;
  private Date expiryDate;



  public CaseOutlineHeaderRequest() {
    super();
    // TODO Auto-generated constructor stub
  }

  public CaseOutlineHeaderRequest(String code, String description, Date effectiveDate,
      Date expiryDate) {
    super();
    this.code = code;
    this.description = description;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
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

  public Date getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  @Override
  public String toString() {
    return "CaseOutlineHeaderRequest [code=" + code + ", description=" + description
        + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + "]";
  }

}
