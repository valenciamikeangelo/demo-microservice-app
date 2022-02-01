/*
  * Modified by: decinam
  * Last updated: Apr 16, 2019 4:40:31 PM
  */

package com.caista.birapps.etis.domain.trs.integration;

import java.util.List;
import com.caista.birapps.etis.domain.trs.entity.TaxPayerTaxType;

public class TCSComplianceMatrixParam {

  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private String createdBy;
  private String tinStatus;
  private List<TaxPayerTaxType> taxTypes;

  public TCSComplianceMatrixParam() {
    super();
  }

  public TCSComplianceMatrixParam(Long taxpayerId, String tin, String branchCode, String tinStatus,
      List<TaxPayerTaxType> taxTypes, String createdBy) {
    super();
    this.taxpayerId = taxpayerId;
    this.tin = tin;
    this.branchCode = branchCode;
    this.createdBy = createdBy;
    this.tinStatus = tinStatus;
    this.taxTypes = taxTypes;
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

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public List<TaxPayerTaxType> getTaxTypes() {
    return taxTypes;
  }

  public void setTaxTypes(List<TaxPayerTaxType> taxTypes) {
    this.taxTypes = taxTypes;
  }
  
  public String getTinStatus() {
    return tinStatus;
  }

  public void setTinStatus(String tinStatus) {
    this.tinStatus = tinStatus;
  }

  @Override
  public String toString() {
    return "TCSLedgerHeaderParam [taxpayerId=" + taxpayerId + ", tin=" + tin + ", branchCode="
        + branchCode + ", createdBy=" + createdBy + ", tinStatus=" + tinStatus + "]";
  }

}
