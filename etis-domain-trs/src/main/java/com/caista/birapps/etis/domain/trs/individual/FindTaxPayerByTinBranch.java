/*
 * Last modified by: delmund
 * Last updated date: Oct 15, 2018 5:06:29 PM
 */
package com.caista.birapps.etis.domain.trs.individual;

import com.caista.birapps.etis.domain.trs.entity.Address;

public class FindTaxPayerByTinBranch {

  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private Address address;
  private String registrationType;

  public FindTaxPayerByTinBranch() {
    super();
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

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getRegistrationType() {
    return registrationType;
  }

  public void setRegistrationType(String registrationType) {
    this.registrationType = registrationType;
  }


}
