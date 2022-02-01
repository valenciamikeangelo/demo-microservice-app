/*
  * Modified by: obregoj
  * Last updated: 05 19, 20 11:42:23 AM
  */
package com.caista.birapps.etis.domain.sysad.dto;

import java.util.Date;
import com.caista.birapps.etis.domain.sysad.entity.*;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceCorrespondence;

public class ManageSignatoryPaginationDTO {

  private Long id;
  private StaffPrimaryOffice office;
  private ReferenceCorrespondence correspondenceType;
  private Office officeId;
  private String updatedBy;
  private Date updatedDate;
  private String signatoryFirstName;
  private String signatoryLastName;
  private String signatorySuffix;
  private String signatoryName;
  private Date effectiveDate;
  private Date expiryDate;
  private String createdBy;
  private Date createdDate;
  private String position;


  public ManageSignatoryPaginationDTO() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public StaffPrimaryOffice getOffice() {
    return office;
  }

  public void setOffice(StaffPrimaryOffice office) {
    this.office = office;
  }

  public ReferenceCorrespondence getCorrespondenceType() {
    return correspondenceType;
  }

  public void setCorrespondenceType(ReferenceCorrespondence correspondenceType) {
    this.correspondenceType = correspondenceType;
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

  public String getSignatorySuffix() {
    return signatorySuffix;
  }

  public void setSignatorySuffix(String signatorySuffix) {
    this.signatorySuffix = signatorySuffix;
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

  public Office getOfficeId() {
    return officeId;
  }

  public void setOfficeId(Office officeId) {
    this.officeId = officeId;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  @Override
  public String toString() {
    return "ManageSignatoryPaginationDTO [id=" + id + ", office=" + office + ", correspondenceType="
        + correspondenceType + ", officeId=" + officeId + ", updatedBy=" + updatedBy
        + ", updatedDate=" + updatedDate + ", signatoryFirstName=" + signatoryFirstName
        + ", signatoryLastName=" + signatoryLastName + ", signatoryName=" + signatoryName
        + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", position=" + position + "]";
  }

}
