/*
 * Modified by: fuentem
 * Last updated: Nov 7, 2018 4:36:32 PM
 */
package com.caista.birapps.etis.common.report;

/**
 * The Class AuthorityToPrint.
 */
public class AuthorityToPrintParam {

  private String tin;
  private String branchCode;
  private String dln;
  private String officeCode;
  private String tradeName;


  public AuthorityToPrintParam() {
    super();
  }

  public AuthorityToPrintParam(String tin, String branchCode, String dln, String officeCode,
      String tradeName) {
    super();
    this.tin = tin;
    this.branchCode = branchCode;
    this.dln = dln;
    this.officeCode = officeCode;
    this.tradeName = tradeName;
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

  public String getDln() {
    return dln;
  }

  public void setDln(String dln) {
    this.dln = dln;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getTradeName() {
    return tradeName;
  }

  public void setTradeName(String tradeName) {
    this.tradeName = tradeName;
  }



}
