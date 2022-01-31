/*
 * Modified by: fuentem
 * Last updated: Nov 8, 2018 5:15:44 PM
 */
package com.caista.birapps.etis.common.report;

import java.util.Date;

public class CertificateOfRegistrationTaxTypeParam {

  private Long taxTypeId;
  private String taxTypeDesc;
  private String formTypeCode;
  private Date filingStartDate;
  private String filingFrequency;
  private String filingDueDate;

  public CertificateOfRegistrationTaxTypeParam() {
    super();
  }

  public CertificateOfRegistrationTaxTypeParam(Long taxTypeId, String taxTypeDesc,
      String formTypeCode, Date filingStartDate, String filingFrequency, String filingDueDate) {
    super();
    this.taxTypeId = taxTypeId;
    this.taxTypeDesc = taxTypeDesc;
    this.formTypeCode = formTypeCode;
    this.filingStartDate = filingStartDate;
    this.filingFrequency = filingFrequency;
    this.filingDueDate = filingDueDate;
  }

  public Long getTaxTypeId() {
    return taxTypeId;
  }

  public void setTaxTypeId(Long taxTypeId) {
    this.taxTypeId = taxTypeId;
  }

  public String getTaxTypeDesc() {
    return taxTypeDesc;
  }

  public void setTaxTypeDesc(String taxTypeDesc) {
    this.taxTypeDesc = taxTypeDesc;
  }

  public String getFormTypeCode() {
    return formTypeCode;
  }

  public void setFormTypeCode(String formTypeCode) {
    this.formTypeCode = formTypeCode;
  }

  public Date getFilingStartDate() {
    return filingStartDate;
  }

  public void setFilingStartDate(Date filingStartDate) {
    this.filingStartDate = filingStartDate;
  }

  public String getFilingFrequency() {
    return filingFrequency;
  }

  public void setFilingFrequency(String filingFrequency) {
    this.filingFrequency = filingFrequency;
  }

  public String getFilingDueDate() {
    return filingDueDate;
  }

  public void setFilingDueDate(String filingDueDate) {
    this.filingDueDate = filingDueDate;
  }

}
