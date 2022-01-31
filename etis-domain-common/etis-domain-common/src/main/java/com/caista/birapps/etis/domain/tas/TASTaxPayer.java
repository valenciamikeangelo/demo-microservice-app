/*
 * Modified by: pastolc
 * Last updated: Mar 20, 2019 10:37:57 AM
 */
package com.caista.birapps.etis.domain.tas;

import java.io.Serializable;
import java.util.Date;

public class TASTaxPayer implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 2409728802847411146L;
  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private String registeredName;
  private String registeredAddress;
  private Long officeId;
  private String officeDescription;
  private String officeCode;
  private Date tinIssuanceDate;
  private String tinStatus;
  private String taxpayerTypeId;
  private String taxpayerTypeDescription;
  private String accountingPeriodId;
  private String accountingPeriodDescription;
  private String accountingYearStartMonthId;
  private String accountingYearStartMonthDescription;

  @Override
  public String toString() {
    return "TASTaxPayer [taxpayerId=" + taxpayerId + ", tin=" + tin + ", branchCode=" + branchCode
        + ", registeredName=" + registeredName + ", registeredAddress=" + registeredAddress
        + ", officeId=" + officeId + ", officeDescription=" + officeDescription + ", officeCode="
        + officeCode + ", tinIssuanceDate=" + tinIssuanceDate + ", tinStatus=" + tinStatus
        + ", taxpayerTypeId=" + taxpayerTypeId + ", taxpayerTypeDescription="
        + taxpayerTypeDescription + ", accountingPeriodId=" + accountingPeriodId
        + ", accountingPeriodDescription=" + accountingPeriodDescription
        + ", accountingYearStartMonthId=" + accountingYearStartMonthId
        + ", accountingYearStartMonthDescription=" + accountingYearStartMonthDescription + "]";
  }

  public Long getTaxpayerId() {
    return taxpayerId;
  }

  public void setTaxpayerId(Long taxpayerId) {
    this.taxpayerId = taxpayerId;
  }

  public String getTin() {
    return tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public String getRegisteredName() {
    return registeredName;
  }

  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
  }

  public String getRegisteredAddress() {
    return registeredAddress;
  }

  public void setRegisteredAddress(String registeredAddress) {
    this.registeredAddress = registeredAddress;
  }

  public Long getOfficeId() {
    return officeId;
  }

  public void setOfficeId(Long officeId) {
    this.officeId = officeId;
  }

  public String getOfficeDescription() {
    return officeDescription;
  }

  public void setOfficeDescription(String officeDescription) {
    this.officeDescription = officeDescription;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public Date getTinIssuanceDate() {
    return tinIssuanceDate;
  }

  public void setTinIssuanceDate(Date tinIssuanceDate) {
    this.tinIssuanceDate = tinIssuanceDate;
  }

  public String getTinStatus() {
    return tinStatus;
  }

  public void setTinStatus(String tinStatus) {
    this.tinStatus = tinStatus;
  }

  public String getTaxpayerTypeId() {
    return taxpayerTypeId;
  }

  public void setTaxpayerTypeId(String taxpayerTypeId) {
    this.taxpayerTypeId = taxpayerTypeId;
  }

  public String getTaxpayerTypeDescription() {
    return taxpayerTypeDescription;
  }

  public void setTaxpayerTypeDescription(String taxpayerTypeDescription) {
    this.taxpayerTypeDescription = taxpayerTypeDescription;
  }

  public String getAccountingPeriodId() {
    return accountingPeriodId;
  }

  public void setAccountingPeriodId(String accountingPeriodId) {
    this.accountingPeriodId = accountingPeriodId;
  }

  public String getAccountingPeriodDescription() {
    return accountingPeriodDescription;
  }

  public void setAccountingPeriodDescription(String accountingPeriodDescription) {
    this.accountingPeriodDescription = accountingPeriodDescription;
  }

  public String getAccountingYearStartMonthId() {
    return accountingYearStartMonthId;
  }

  public void setAccountingYearStartMonthId(String accountingYearStartMonthId) {
    this.accountingYearStartMonthId = accountingYearStartMonthId;
  }

  public String getAccountingYearStartMonthDescription() {
    return accountingYearStartMonthDescription;
  }

  public void setAccountingYearStartMonthDescription(String accountingYearStartMonthDescription) {
    this.accountingYearStartMonthDescription = accountingYearStartMonthDescription;
  }

}
