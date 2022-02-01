/*
  * Modified by: bacosal
  * Last updated: Jun 26, 2018 5:05:38 PM
  */
package com.caista.birapps.etis.domain.trs;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class TaxPayerGetRdoParams {

  private String tin;

  private String branchCode;

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


}
