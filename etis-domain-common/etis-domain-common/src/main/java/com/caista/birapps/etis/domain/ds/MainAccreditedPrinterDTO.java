/*
  * Modified by: bongalr
  * Last updated: Feb 4, 2020 5:18:18 PM
  */
package com.caista.birapps.etis.domain.ds;

import java.util.Date;

public class MainAccreditedPrinterDTO {

  private String officeCode;
  private String printerTin;
  private String accredNum;
  private Date accredDate;
  private Date accredExpiryDate;

  public MainAccreditedPrinterDTO() {
    super();
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getPrinterTin() {
    return printerTin;
  }

  public void setPrinterTin(String printerTin) {
    this.printerTin = printerTin;
  }

  public String getAccredNum() {
    return accredNum;
  }

  public void setAccredNum(String accredNum) {
    this.accredNum = accredNum;
  }

  public Date getAccredDate() {
    return accredDate;
  }

  public void setAccredDate(Date accredDate) {
    this.accredDate = accredDate;
  }

  public Date getAccredExpiryDate() {
    return accredExpiryDate;
  }

  public void setAccredExpiryDate(Date accredExpiryDate) {
    this.accredExpiryDate = accredExpiryDate;
  }

  @Override
  public String toString() {
    return "MainAccreditedPrinterDTO [officeCode=" + officeCode + ", printerTin=" + printerTin
        + ", accredNum=" + accredNum + ", accredDate=" + accredDate + ", accredExpiryDate="
        + accredExpiryDate + "]";
  }

}
