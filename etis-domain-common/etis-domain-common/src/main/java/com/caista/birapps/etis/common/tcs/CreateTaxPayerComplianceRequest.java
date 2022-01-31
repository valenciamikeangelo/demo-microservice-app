/*
  * Modified by: decinam
  * Last updated: Apr 15, 2019 1:16:44 PM
  */
package com.caista.birapps.etis.common.tcs;

public class CreateTaxPayerComplianceRequest {

  private Long taxpayerId;
  private String tin;
  private String branchCode;

  public CreateTaxPayerComplianceRequest() {
    super();
  }

  public CreateTaxPayerComplianceRequest(Long taxpayerId, String tin, String branchCode) {
    super();
    this.taxpayerId = taxpayerId;
    this.tin = tin;
    this.branchCode = branchCode;
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

  public Long getTaxpayerId() {
    return taxpayerId;
  }

  public void setTaxpayerId(Long taxpayerId) {
    this.taxpayerId = taxpayerId;
  }

  @Override
  public String toString() {
    return "CreateTaxPayerComplianceRequest [taxpayerId=" + taxpayerId + ", tin=" + tin
        + ", branchCode=" + branchCode + "]";
  }

}
