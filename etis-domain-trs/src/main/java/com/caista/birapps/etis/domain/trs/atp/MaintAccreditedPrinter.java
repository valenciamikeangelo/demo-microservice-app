/*
 * Modified by: romerov
 * Last updated: Sep 2, 2019 2:16:32 PM
 */
package com.caista.birapps.etis.domain.trs.atp;

import java.util.Date;

public class MaintAccreditedPrinter {

  private String id;

  private String printerTin;

  private String printerName;

  private String businessName;

  private String address;

  private String accreditationNumber;

  private Date accreditationDate;

  private Date effectiveDate;

  private Date expiryDate;

  private String email;

  private String contactNumber;

  public MaintAccreditedPrinter() {
    super();
  }

  public MaintAccreditedPrinter(String id, String printerTin, String printerName,
      String businessName, String address, String accreditationNumber, Date accreditationDate,
      Date effectiveDate, Date expiryDate, String email, String contactNumber) {
    super();
    this.id = id;
    this.printerTin = printerTin;
    this.printerName = printerName;
    this.businessName = businessName;
    this.address = address;
    this.accreditationNumber = accreditationNumber;
    this.accreditationDate = accreditationDate;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.email = email;
    this.contactNumber = contactNumber;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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


}
