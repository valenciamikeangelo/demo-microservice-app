/*
  * Modified by: logronj
  * Last updated: 03 6, 20 2:09:47 PM
  */
package com.caista.birapps.etis.domain.sysad.dto;

import java.util.Date;
import com.caista.birapps.etis.domain.sysad.entity.*;

public class MaintTcrSignatoryDTO {

  private Long id;
  private String category;
  private String correspondenceType;
  private String taxType;
  private String tresholdAmt;
  private StaffPrimaryOffice office;
  private Office officeId;
  private String updatedBy;
  private Date updatedDate;
  private String signatoryFirstName;
  private String signatoryLastName;
  private String signatoryName;
  private Date effectiveDate;
  private Date expiryDate;
  private String createdBy;
  private Date createdDate;
  private String position;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }



  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getCorrespondenceType() {
    return correspondenceType;
  }

  public void setCorrespondenceType(String correspondenceType) {
    this.correspondenceType = correspondenceType;
  }

  public String getTaxType() {
    return taxType;
  }

  public void setTaxType(String taxType) {
    this.taxType = taxType;
  }

  public String getTresholdAmt() {
    return tresholdAmt;
  }

  public void setTresholdAmt(String tresholdAmt) {
    this.tresholdAmt = tresholdAmt;
  }

  public StaffPrimaryOffice getOffice() {
    return office;
  }

  public void setOffice(StaffPrimaryOffice office) {
    this.office = office;
  }

  public Office getOfficeId() {
    return officeId;
  }

  public void setOfficeId(Office officeId) {
    this.officeId = officeId;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getSignatoryFirstName() {
    return signatoryFirstName;
  }

  public void setSignatoryFirstName(String signatoryFirstName) {
    this.signatoryFirstName = signatoryFirstName;
  }

  public String getSignatoryLastName() {
    return signatoryLastName;
  }

  public void setSignatoryLastName(String signatoryLastName) {
    this.signatoryLastName = signatoryLastName;
  }

  public String getSignatoryName() {
    return signatoryName;
  }

  public void setSignatoryName(String signatoryName) {
    this.signatoryName = signatoryName;
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

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  @Override
  public String toString() {
    return "MaintTcrSignatoryDTO [id=" + id + ", category=" + category + ", correspondenceType="
        + correspondenceType + ", taxType=" + taxType + ", tresholdAmt=" + tresholdAmt + ", office="
        + office + ", officeId=" + officeId + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + ", signatoryFirstName=" + signatoryFirstName + ", signatoryLastName="
        + signatoryLastName + ", signatoryName=" + signatoryName + ", effectiveDate="
        + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy
        + ", createdDate=" + createdDate + ", position=" + position + "]";
  }




}
