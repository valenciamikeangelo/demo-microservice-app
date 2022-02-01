package com.caista.birapps.etis.domain.trs.findtaxpayer;

import java.io.Serializable;

public class FindTaxpayerATPForView implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private String tinStatus;
  private String taxpayerTypeDescription;
  private Boolean taxpayerTypeIsBusiness;
  private Boolean isWithinViewATPJurisdiction;
  
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
  public String getTinStatus() {
    return tinStatus;
  }
  public void setTinStatus(String tinStatus) {
    this.tinStatus = tinStatus;
  }
  public String getTaxpayerTypeDescription() {
    return taxpayerTypeDescription;
  }
  public void setTaxpayerTypeDescription(String taxpayerTypeDescription) {
    this.taxpayerTypeDescription = taxpayerTypeDescription;
  }
  public Boolean getTaxpayerTypeIsBusiness() {
    return taxpayerTypeIsBusiness;
  }
  public void setTaxpayerTypeIsBusiness(Boolean taxpayerTypeIsBusiness) {
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
  }
  public Boolean getIsWithinViewATPJurisdiction() {
    return isWithinViewATPJurisdiction;
  }
  public void setIsWithinViewATPJurisdiction(Boolean isWithinViewATPJurisdiction) {
    this.isWithinViewATPJurisdiction = isWithinViewATPJurisdiction;
  }
  
  
  
}
