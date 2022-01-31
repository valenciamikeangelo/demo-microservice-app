/*
  * Modified by: obregoj
  * Last updated: Sep 17, 2019 3:29:47 PM
  */
package com.caista.birapps.etis.domain.report.dto;

import java.util.Date;

public class ReportAuditDto {

  private String refAuditId;

  private String description;

  private String otherDetails;

  private String newValue;

  private String oldValue;

  private Date generationDate;

  public ReportAuditDto() {
    super();
  }

  public ReportAuditDto(String refAuditId, String description, String otherDetails, String newValue,
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

  public Date getGenerationDate() {
    return generationDate;
  }

  public void setGenerationDate(Date generationDate) {
    this.generationDate = generationDate;
  }

  @Override
  public String toString() {
    return "ReportAuditDto [refAuditId=" + refAuditId + ", description=" + description
        + ", otherDetails=" + otherDetails + ", newValue=" + newValue + ", oldValue=" + oldValue
        + ", generationDate=" + generationDate + "]";
  }



}
