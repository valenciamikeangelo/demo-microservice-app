/*
 * Last modified by: delmund
 * Last updated date: Oct 11, 2018 3:47:58 PM
 */
package com.caista.birapps.etis.domain.trs.atp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class AtpFindTaxPayerParams {

  // Find Branch/TradeName Details
  private String tin;

  private String branchCode;

  private Long rdoId;

  private String rdoDescription;

  // Application Details
  private String lastName;

  private String firstName;

  private String middleName;


  private String registeredName;

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

  public Long getRdoId() {
    return rdoId;
  }

  public void setRdoId(Long rdoId) {
    this.rdoId = rdoId;
  }

  public String getRdoDescription() {
    return rdoDescription;
  }

  public void setRdoDescription(String rdoDescription) {
    this.rdoDescription = rdoDescription;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getRegisteredName() {
    return registeredName;
  }

  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
  }


}
