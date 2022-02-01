/*
  * Modified by: decinam
  * Last updated: Sep 14, 2018 6:48:03 PM
  */
package com.caista.birapps.etis.domain.trs.suspend;

import java.util.*;
import com.caista.birapps.etis.domain.trs.entity.ContactInformation;
import com.caista.birapps.etis.domain.trs.entity.TaxPayerFormType;
import com.caista.birapps.etis.domain.trs.entity.TaxPayerTaxType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class SuspendTaxFormTypeParams {
  private String tin;

  public SuspendTaxFormTypeParams(String tin, String branchCode, TaxPayerTaxType taxPayerTaxType,
      TaxPayerFormType taxPayerFormType, String transactionId, Date transactionDate,
      String taxPayerRegisteredName, String rdoDescription,
      List<ContactInformation> contactInformation, String type) {
    super();
    this.tin = tin;
    this.branchCode = branchCode;
    this.taxPayerTaxType = taxPayerTaxType;
    this.taxPayerFormType = taxPayerFormType;
    this.transactionId = transactionId;
    this.transactionDate = transactionDate;
    this.taxPayerRegisteredName = taxPayerRegisteredName;
    this.rdoDescription = rdoDescription;
    this.contactInformation = contactInformation;
    this.type = type;
  }

  private String branchCode;
  private TaxPayerTaxType taxPayerTaxType;
  private TaxPayerFormType taxPayerFormType;
  private String transactionId;
  private Date transactionDate;
  private String taxPayerRegisteredName;
  private String rdoDescription;
  private List<ContactInformation> contactInformation;
  private String type;

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

  public TaxPayerTaxType getTaxPayerTaxType() {
    return taxPayerTaxType;
  }

  public void setTaxPayerTaxType(TaxPayerTaxType taxPayerTaxType) {
    this.taxPayerTaxType = taxPayerTaxType;
  }

  public TaxPayerFormType getTaxPayerFormType() {
    return taxPayerFormType;
  }

  public void setTaxPayerFormType(TaxPayerFormType taxPayerFormType) {
    this.taxPayerFormType = taxPayerFormType;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public Date getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }

  public String getTaxPayerRegisteredName() {
    return taxPayerRegisteredName;
  }

  public void setTaxPayerRegisteredName(String taxPayerRegisteredName) {
    this.taxPayerRegisteredName = taxPayerRegisteredName;
  }

  public String getRdoDescription() {
    return rdoDescription;
  }

  public void setRdoDescription(String rdoDescription) {
    this.rdoDescription = rdoDescription;
  }

  public List<ContactInformation> getContactInformation() {
    return contactInformation;
  }

  public void setContactInformation(List<ContactInformation> contactInformation) {
    this.contactInformation = contactInformation;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "SuspendTaxFormTypeParams [tin=" + tin + ", branchCode=" + branchCode
        + ", taxPayerTaxType=" + taxPayerTaxType + ", taxPayerFormType=" + taxPayerFormType
        + ", transactionId=" + transactionId + ", transactionDate=" + transactionDate
        + ", taxPayerRegisteredName=" + taxPayerRegisteredName + ", rdoDescription="
        + rdoDescription + ", contactInformation=" + contactInformation + ", type=" + type + "]";
  }
}
