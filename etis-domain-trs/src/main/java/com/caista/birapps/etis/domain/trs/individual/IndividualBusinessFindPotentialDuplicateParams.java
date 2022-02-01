/*
 * Last modified by: delmund
 * Last updated date: Oct 11, 2018 4:05:07 PM
 */
package com.caista.birapps.etis.domain.trs.individual;

import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class IndividualBusinessFindPotentialDuplicateParams {

  private String taxPayerTypeId;

  private String taxPayerTypeDescription;

  private String firstName;

  private String middleName;

  private String lastName;

  private Date dateOfBirth;

  public String getTaxPayerTypeId() {
    return taxPayerTypeId;
  }

  public void setTaxPayerTypeId(String taxPayerTypeId) {
    this.taxPayerTypeId = taxPayerTypeId;
  }

  public String getTaxPayerTypeDescription() {
    return taxPayerTypeDescription;
  }

  public void setTaxPayerTypeDescription(String taxPayerTypeDescription) {
    this.taxPayerTypeDescription = taxPayerTypeDescription;
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

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

}
