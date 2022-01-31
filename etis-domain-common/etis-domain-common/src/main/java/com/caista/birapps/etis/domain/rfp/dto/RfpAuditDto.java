/*
  * Modified by: logronj
  * Last updated: Aug 29, 2019 6:49:30 PM
  */
package com.caista.birapps.etis.domain.rfp.dto;

public class RfpAuditDto {

  private String refAuditId;

  private String description;

  private String otherDetails;

  private String newValue;

  private String oldValue;

  public RfpAuditDto() {
    super();
  }

  public RfpAuditDto(String refAuditId, String description, String otherDetails, String newValue,
      String oldValue) {
    super();
    this.refAuditId = refAuditId;
    this.description = description;
    this.otherDetails = otherDetails;
    this.newValue = newValue;
    this.oldValue = oldValue;
  }



  public String getRefAuditId() {
    return refAuditId;
  }

  public void setRefAuditId(String refAuditId) {
    this.refAuditId = refAuditId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getOtherDetails() {
    return otherDetails;
  }

  public void setOtherDetails(String otherDetails) {
    this.otherDetails = otherDetails;
  }

  public String getNewValue() {
    return newValue;
  }

  public void setNewValue(String newValue) {
    this.newValue = newValue;
  }


  public String getOldValue() {
    return oldValue;
  }

  public void setOldValue(String oldValue) {
    this.oldValue = oldValue;
  }

  @Override
  public String toString() {
    return "RfpAuditDto [refAuditId=" + refAuditId + ", description=" + description
        + ", otherDetails=" + otherDetails + ", newValue=" + newValue + ", oldValue=" + oldValue
        + "]";
  }



}
