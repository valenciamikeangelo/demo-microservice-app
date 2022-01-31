/*
  * Modified by: logronj
  * Last updated: 08 11, 20 2:44:19 PM
  */
package com.caista.birapps.etis.domain.tcr.dto;

public class ApprovedTcrEmailDTO {
  private String tcrApplicationNumber;
  private String[] emailRecipients;

  public String getTcrApplicationNumber() {
    return tcrApplicationNumber;
  }

  public void setTcrApplicationNumber(String tcrApplicationNumber) {
    this.tcrApplicationNumber = tcrApplicationNumber;
  }

  public String[] getEmailRecipients() {
    return emailRecipients;
  }

  public void setEmailRecipients(String[] emailRecipients) {
    this.emailRecipients = emailRecipients;
  }


}
