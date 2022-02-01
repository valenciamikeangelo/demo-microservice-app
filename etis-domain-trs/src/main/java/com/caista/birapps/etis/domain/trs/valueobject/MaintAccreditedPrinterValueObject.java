/*
 * Modified by: fuentem
 * Last updated: Nov 25, 2018 9:37:13 PM
 */
package com.caista.birapps.etis.domain.trs.valueobject;

import java.io.Serializable;
import java.util.Date;

public class MaintAccreditedPrinterValueObject implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;
  private MaintReferenceGenericValueObject office;
  private String printerTin;
  private String printerName;
  private String businessName;
  private String address;
  private String accreditationNumber;
  private Date accreditationDate;
  private String createdBy;
  private Date updatedDate;
  private String updatedBy;
  private Date effectiveDate;
  private Date expiryDate;
  private String email;
  private String contactNumber;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public MaintReferenceGenericValueObject getOffice() {
    return office;
  }

  public void setOffice(MaintReferenceGenericValueObject office) {
    this.office = office;
  }

  public String getPrinterTin() {
    return printerTin;
  }

  public void setPrinterTin(String printerTin) {
    this.printerTin = printerTin;
  }

  public String getPrinterName() {
    return printerName;
  }

  public void setPrinterName(String printerName) {
    this.printerName = printerName;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAccreditationNumber() {
    return accreditationNumber;
  }

  public void setAccreditationNumber(String accreditationNumber) {
    this.accreditationNumber = accreditationNumber;
  }

  public Date getAccreditationDate() {
    return accreditationDate;
  }

  public void setAccreditationDate(Date accreditationDate) {
    this.accreditationDate = accreditationDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  @Override
  public String toString() {
    return "MaintAccreditedPrinterValueObject [id=" + id + ", office=" + office + ", printerTin="
        + printerTin + ", printerName=" + printerName + ", businessName=" + businessName
        + ", address=" + address + ", accreditationNumber=" + accreditationNumber
        + ", accreditationDate=" + accreditationDate + ", createdBy=" + createdBy + ", updatedDate="
        + updatedDate + ", updatedBy=" + updatedBy + ", effectiveDate=" + effectiveDate
        + ", expiryDate=" + expiryDate + ", email=" + email + ", contactNumber=" + contactNumber
        + "]";
  }

}
