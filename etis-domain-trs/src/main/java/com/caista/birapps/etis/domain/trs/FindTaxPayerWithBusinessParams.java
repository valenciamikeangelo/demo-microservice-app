/*
 * Last modified by: delmund
 * Last updated date: Oct 11, 2018 3:46:05 PM
 */
package com.caista.birapps.etis.domain.trs;

import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class FindTaxPayerWithBusinessParams {

  // General Search Details
  private String tin;

  private String branchCode;

  private Long rdoId;

  private String rdoDescription;

  // Advanced Search Details: Common
  private Date dateOfBirthOrganizationDate;

  // Advanced Search Details: Non-Individual
  private String registeredName;

  // Advanced Search Details: Individual
  private String lastNameTrustName;

  private String firstName;

  private String middleName;

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

  public Date getDateOfBirthOrganizationDate() {
    return dateOfBirthOrganizationDate;
  }

  public void setDateOfBirthOrganizationDate(Date dateOfBirthOrganizationDate) {
    this.dateOfBirthOrganizationDate = dateOfBirthOrganizationDate;
  }

  public String getRegisteredName() {
    return registeredName;
  }

  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
  }

  public String getLastNameTrustName() {
    return lastNameTrustName;
  }

  public void setLastNameTrustName(String lastNameTrustName) {
    this.lastNameTrustName = lastNameTrustName;
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


}
