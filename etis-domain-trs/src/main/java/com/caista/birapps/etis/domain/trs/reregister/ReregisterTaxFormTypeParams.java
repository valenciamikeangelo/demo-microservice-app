/*
 * Modified by: santojo
 * Last updated: Aug 29, 2019 9:36:40 AM
 */
package com.caista.birapps.etis.domain.trs.reregister;

import java.util.*;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.Gson;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReregisterTaxFormTypeParams {

  private String tin;
  private String branchCode;
  private List<TaxPayerTaxType> taxPayerTaxType;
  private List<TaxPayerFormType> taxPayerFormType;
  private String transactionId;
  private Date transactionDate;
  private String taxPayerRegisteredName;
  private String rdoDescription;
  private String rdoCode;
  private List<ContactInformation> contactInformation;
  private String type;
  private String reasonId;
  private String reasonDescription;
  private String remarks;
  private String taxpayerTypeId;
  private String taxpayerTypeDescription;
  private Date reregistrationDate;
  private String dln;
  private String accountingPeriodId;


  public String getTaxpayerTypeId() {
    return taxpayerTypeId;
  }

  public void setTaxpayerTypeId(String taxpayerTypeId) {
    this.taxpayerTypeId = taxpayerTypeId;
  }

  public String getTaxpayerTypeDescription() {
    return taxpayerTypeDescription;
  }

  public void setTaxpayerTypeDescription(String taxpayerTypeDescription) {
    this.taxpayerTypeDescription = taxpayerTypeDescription;
  }

  public List<TaxPayerTaxType> getTaxPayerTaxType() {
    return taxPayerTaxType;
  }

  public void setTaxPayerTaxType(List<TaxPayerTaxType> taxPayerTaxType) {
    this.taxPayerTaxType = taxPayerTaxType;
  }

  public List<TaxPayerFormType> getTaxPayerFormType() {
    return taxPayerFormType;
  }

  public void setTaxPayerFormType(List<TaxPayerFormType> taxPayerFormType) {
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

  public String getReasonId() {
    return reasonId;
  }

  public void setReasonId(String reasonId) {
    this.reasonId = reasonId;
  }

  public String getReasonDescription() {
    return reasonDescription;
  }

  public void setReasonDescription(String reasonDescription) {
    this.reasonDescription = reasonDescription;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Date getReregistrationDate() {
    return reregistrationDate;
  }

  public void setReregistrationDate(Date reregistrationDate) {
    this.reregistrationDate = reregistrationDate;
  }

  public String getDln() {
    return dln;
  }

  public void setDln(String dln) {
    this.dln = dln;
  }

  public String getAccountingPeriodId() {
    return accountingPeriodId;
  }

  public void setAccountingPeriodId(String accountingPeriodId) {
    this.accountingPeriodId = accountingPeriodId;
  }


  public String getRdoCode() {
    return rdoCode;
  }

  public void setRdoCode(String rdoCode) {
    this.rdoCode = rdoCode;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }

}
