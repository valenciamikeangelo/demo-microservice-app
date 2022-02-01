/*
  * Modified by: decinam
  * Last updated: Apr 22, 2019 4:47:12 PM
  */

package com.caista.birapps.etis.domain.trs.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TASLedgerHeaderParam {

  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private String taxTypeId;
  private String createdBy;

  public TASLedgerHeaderParam() {
    super();
  }

  public TASLedgerHeaderParam(Long taxpayerId, String tin, String branchCode, String taxTypeId,
      String createdBy) {
    super();
    this.taxpayerId = taxpayerId;
    this.tin = tin;
    this.branchCode = branchCode;
    this.taxTypeId = taxTypeId;
    this.createdBy = createdBy;
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

  public String getTaxTypeId() {
    return taxTypeId;
  }

  public void setTaxTypeId(String taxTypeId) {
    this.taxTypeId = taxTypeId;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  @Override
  public String toString() {
    return "TASLedgerHeaderParam [taxpayerId=" + taxpayerId + ", tin=" + tin + ", branchCode="
        + branchCode + ", taxTypeId=" + taxTypeId + ", createdBy=" + createdBy + "]";
  }

}
