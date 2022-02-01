/*
  * Modified by: bongalr
  * Last updated: Oct 11, 2018 2:12:54 PM
  */
package com.caista.birapps.etis.domain.trs.individual;

import java.util.Date;

public class EstateTrustPotentialDuplicateParam {

  private String taxPayerTypeId;
  private String taxPayerTypeDescription;
	private String estateName;
	private String trustName;
	private Date birthDate;


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

  public String getEstateName() {
    return estateName;
  }

  public void setEstateName(String estateName) {
    this.estateName = estateName;
  }

  public String getTrustName() {
    return trustName;
  }

  public void setTrustName(String trustName) {
    this.trustName = trustName;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }



}
