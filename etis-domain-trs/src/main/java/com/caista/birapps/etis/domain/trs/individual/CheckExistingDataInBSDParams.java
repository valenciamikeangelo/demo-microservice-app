/*
  * Modified by: adzuara
  * Last updated: Nov 28, 2018 1:41:24 PM
  */
package com.caista.birapps.etis.domain.trs.individual;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class CheckExistingDataInBSDParams {

  private String registrationNumber;
  private String singleBusinessNumber;
  private String tradeName;

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public String getSingleBusinessNumber() {
    return singleBusinessNumber;
  }

  public void setSingleBusinessNumber(String singleBusinessNumber) {
    this.singleBusinessNumber = singleBusinessNumber;
  }

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

  @Override
  public String toString() {
    return "CheckExistingDataInBSDParams [registrationNumber=" + registrationNumber
				+ ", singleBusinessNumber=" + singleBusinessNumber + ", tradeName=" + tradeName + "]";
  }


}
