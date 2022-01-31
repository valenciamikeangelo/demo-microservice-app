/*
  * Modified by: decinam
  * Last updated: Apr 24, 2019 2:07:58 PM
  */
package com.caista.birapps.etis.common.tcs;

import java.io.Serializable;
import java.util.Date;

public class ReturnPeriodRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String taxType;
  private String formType;
  private Date registrationDate;
  private String accountingPeriod;
  private Date accountingEffectiveDate;

  public ReturnPeriodRequest() {
    super();

  }

  public ReturnPeriodRequest(String taxType, String formType, Date registrationDate,
      String accountingPeriod) {
    super();
    this.taxType = taxType;
    this.formType = formType;
    this.registrationDate = registrationDate;
    this.accountingPeriod = accountingPeriod;
  }
  
  public ReturnPeriodRequest(String taxType, String formType, Date registrationDate,
      String accountingPeriod, Date accountingEffectiveDate) {
    super();
    this.taxType = taxType;
    this.formType = formType;
    this.registrationDate = registrationDate;
    this.accountingPeriod = accountingPeriod;
    this.accountingEffectiveDate = accountingEffectiveDate;
  }

  public String getTaxType() {
    return taxType;
  }

  public void setTaxType(String taxType) {
    this.taxType = taxType;
  }

  public String getFormType() {
    return formType;
  }

  public void setFormType(String formType) {
    this.formType = formType;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public String getAccountingPeriod() {
    return accountingPeriod;
  }

  public void setAccountingPeriod(String accountingPeriod) {
    this.accountingPeriod = accountingPeriod;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Date getAccountingEffectiveDate() {
    return accountingEffectiveDate;
  }

  public void setAccountingEffectiveDate(Date accountingEffectiveDate) {
    this.accountingEffectiveDate = accountingEffectiveDate;
  }

  @Override
  public String toString() {
    return "ReturnPeriodRequest [taxType=" + taxType + ", formType=" + formType
        + ", registrationDate=" + registrationDate + ", accountingPeriod=" + accountingPeriod
        + ", accountingEffectiveDate=" + accountingEffectiveDate + "]";
  }

}
