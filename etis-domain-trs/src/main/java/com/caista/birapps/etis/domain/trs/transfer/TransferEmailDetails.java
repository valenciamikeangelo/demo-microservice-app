/*
 * Modified by: fuentem
 * Last updated: Nov 13, 2018 9:06:13 PM
 */
package com.caista.birapps.etis.domain.trs.transfer;

import java.util.List;

public class TransferEmailDetails {

  private String transactionNumber;
  private String registeredName;
  private String estateName;
  private String trustName;
  private String lastName;
  private String firstName;
  private String middleName;
  private String suffx;
  private String oldOfficeCode;
  private String oldOfficeDescription;
  private String newOfficeCode;
  private String newOfficeDescription;
  private List<String> emails;

  public String getTransactionNumber() {
    return transactionNumber;
  }

  public void setTransactionNumber(String transactionNumber) {
    this.transactionNumber = transactionNumber;
  }

  public List<String> getEmails() {
    return emails;
  }

  public void setEmails(List<String> emails) {
    this.emails = emails;
  }

  public String getRegisteredName() {
    return registeredName;
  }

  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
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

  public String getSuffx() {
    return suffx;
  }

  public void setSuffx(String suffx) {
    this.suffx = suffx;
  }

  public String getOldOfficeCode() {
    return oldOfficeCode;
  }

  public void setOldOfficeCode(String oldOfficeCode) {
    this.oldOfficeCode = oldOfficeCode;
  }

  public String getOldOfficeDescription() {
    return oldOfficeDescription;
  }

  public void setOldOfficeDescription(String oldOfficeDescription) {
    this.oldOfficeDescription = oldOfficeDescription;
  }

  public String getNewOfficeCode() {
    return newOfficeCode;
  }

  public void setNewOfficeCode(String newOfficeCode) {
    this.newOfficeCode = newOfficeCode;
  }

  public String getNewOfficeDescription() {
    return newOfficeDescription;
  }

  public void setNewOfficeDescription(String newOfficeDescription) {
    this.newOfficeDescription = newOfficeDescription;
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

}
