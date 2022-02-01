/*
  * Modified by: decinam
  * Last updated: Feb 25, 2019 2:21:01 PM
  */
package com.caista.birapps.etis.domain.trs.reactivate;

import java.util.*;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ReactivateTaxFormTypeParams {

  private String tin;
  private String branchCode;
  private List<TaxPayerTaxType> taxPayerTaxType;
  private List<TaxPayerFormType> taxPayerFormType;
  private String transactionId;
  private Date transactionDate;
  private String taxPayerRegisteredName;
  private String rdoDescription;
  private List<ContactInformation> contactInformation;
  private String type;
  private String taxPayerType;
  private String registeredName;
  private String estateName;
  private String trustName;
  private String firstName;
  private String middleName;
  private String lastName;
  private String suffixId;
  private String suffixDescription;
  private Date reactivationDate;
  private String reasonId;
  private String reasonDescription;
  private String remarks;
	private String documentLocatorNumber;

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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getTaxPayerType() {
		return taxPayerType;
	}

	public void setTaxPayerType(String taxPayerType) {
		this.taxPayerType = taxPayerType;
	}

  public String getRegisteredName() {
    return registeredName;
  }

  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
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

  public String getSuffixId() {
    return suffixId;
  }

  public void setSuffixId(String suffixId) {
    this.suffixId = suffixId;
  }

  public String getSuffixDescription() {
    return suffixDescription;
  }

  public void setSuffixDescription(String suffixDescription) {
    this.suffixDescription = suffixDescription;
  }

  public Date getReactivationDate() {
		return reactivationDate;
	}

	public void setReactivationDate(Date reactivationDate) {
		this.reactivationDate = reactivationDate;
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

	public String getDocumentLocatorNumber() {
		return documentLocatorNumber;
	}

	public void setDocumentLocatorNumber(String documentLocatorNumber) {
		this.documentLocatorNumber = documentLocatorNumber;
	}

}
