/*
 * Modified by: gonzalm
 * Last updated: 11 6, 19 6:59:29 PM
*/

package com.caista.birapps.etis.common.report;

public class StatementOfAccountParam {

  private String ocn;
  private String tin;
  private String branchCode;
  private String taxpayerName;
  private String accountingPeriodId;
  private String rr;
  private String officeCode;
  private String rdo;
  private String rrOfficeCode;
  private String quarter;
  private String taxpayerAddress;
  private String taxableYear;

  public StatementOfAccountParam() {
    super();
  }

  public StatementOfAccountParam(String ocn, String tin, String branchCode,
      String accountingPeriodId, String officeCode, String quarter) {
    super();
    this.ocn = ocn;
    this.tin = tin;
    this.branchCode = branchCode;
    this.accountingPeriodId = accountingPeriodId;
    this.officeCode = officeCode;
    this.quarter = quarter;
  }

  public StatementOfAccountParam(String tin, String branchCode, String accountingPeriodId,
      String officeCode, String quarter) {
    super();
    this.tin = tin;
    this.branchCode = branchCode;
    this.accountingPeriodId = accountingPeriodId;
    this.officeCode = officeCode;
    this.quarter = quarter;
  }

  public String getOcn() {
    return ocn;
  }

  public void setOcn(String ocn) {
    this.ocn = ocn;
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

  public String getTaxpayerName() {
    return taxpayerName;
  }

  public void setTaxpayerName(String taxpayerName) {
    this.taxpayerName = taxpayerName;
  }

  public String getAccountingPeriodId() {
    return accountingPeriodId;
  }

  public void setAccountingPeriodId(String accountingPeriodId) {
    this.accountingPeriodId = accountingPeriodId;
  }

  public String getRr() {
    return rr;
  }

  public void setRr(String rr) {
    this.rr = rr;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getRdo() {
    return rdo;
  }

  public void setRdo(String rdo) {
    this.rdo = rdo;
  }

  public String getRrOfficeCode() {
    return rrOfficeCode;
  }

  public void setRrOfficeCode(String rrOfficeCode) {
    this.rrOfficeCode = rrOfficeCode;
  }

  public String getQuarter() {
    return quarter;
  }

  public void setQuarter(String quarter) {
    this.quarter = quarter;
  }

  public String getTaxpayerAddress() {
    return taxpayerAddress;
  }

  public void setTaxpayerAddress(String taxpayerAddress) {
    this.taxpayerAddress = taxpayerAddress;
  }

  public String getTaxableYear() {
    return taxableYear;
  }

  public void setTaxableYear(String taxableYear) {
    this.taxableYear = taxableYear;
  }

}
