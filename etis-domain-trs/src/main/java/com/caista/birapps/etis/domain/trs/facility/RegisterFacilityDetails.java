/*
 * Last modified by: delmund
 * Last updated date: Nov 27, 2018 3:24:37 PM
 */
package com.caista.birapps.etis.domain.trs.facility;

import java.util.*;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.caista.birapps.etis.domain.trs.utils.enums.*;

/**
 * @author bongalr
 *
 */
public class RegisterFacilityDetails {

  public RegisterFacilityDetails() {
    super();
    // TODO Auto-generated constructor stub
  }
  private String transactionId;
  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private String registeredName;
  private String firstName;
  private String middleName;
  private String lastName;
  private String suffixId;
  private String suffixDescription;
  private TinStatusEnum tinStatus;
  private BusinessStatusEnum businessStatus;
  private Long officeId;
  private String officeDescription;
  private String accountingPeriodId;
  private String accountingPeriodDescription;
  private String accountingYearStartMonthId;
  private String accountingYearStartMonthDescription;
  private Date accountingEffectiveDate;
  private String taxpayerTypeId;
  private String taxpayerTypeDescription;
  private Boolean taxpayerTypeIsBusiness;
  private Address primaryAddress;
  private List<TaxPayerTaxType> tpTaxTypes;
  private List<ContactInformation> contactInformation;
  private Address addresses;
  private TaxPayerTaxType taxTypes;
  private TaxPayerFormType formTypes;
  private String taxpayerClassificationId;
  private String taxpayerClassificationDescription;
  private Date submissionDate;
  private String documentLocatorNumber;
  private Date tinIssuanceDate;
  private String taxpayerGroupId;
  private String taxpayerGroupDescription;
  private Boolean isTamp;
  private Boolean isVIP;
  private Date incorporationDate;
  private String regulatoryBodyId;
  private String regulatoryBodyDescription;
  private String registrationNumber;

  public String getTransactionId() {
    return transactionId;
  }
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }
  public Long getTaxpayerId() {
    return taxpayerId;
  }
  public void setTaxpayerId(Long taxpayerId) {
    this.taxpayerId = taxpayerId;
  }
  public String getTin() {
    return tin;
  }
  public void setTin(String tin) {
    this.tin = tin;
  }
  public String getRegisteredName() {
    return registeredName;
  }
  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
  }
  public TinStatusEnum getTinStatus() {
    return tinStatus;
  }
  public void setTinStatus(TinStatusEnum tinStatus) {
    this.tinStatus = tinStatus;
  }
  public BusinessStatusEnum getBusinessStatus() {
    return businessStatus;
  }
  public void setBusinessStatus(BusinessStatusEnum businessStatus) {
    this.businessStatus = businessStatus;
  }

  public Long getOfficeId() {
    return officeId;
  }

  public void setOfficeId(Long officeId) {
    this.officeId = officeId;
  }

  public String getOfficeDescription() {
    return officeDescription;
  }

  public void setOfficeDescription(String officeDescription) {
    this.officeDescription = officeDescription;
  }

  public String getAccountingPeriodId() {
    return accountingPeriodId;
  }

  public void setAccountingPeriodId(String accountingPeriodId) {
    this.accountingPeriodId = accountingPeriodId;
  }

  public String getAccountingPeriodDescription() {
    return accountingPeriodDescription;
  }

  public void setAccountingPeriodDescription(String accountingPeriodDescription) {
    this.accountingPeriodDescription = accountingPeriodDescription;
  }

  public String getAccountingYearStartMonthId() {
    return accountingYearStartMonthId;
  }

  public void setAccountingYearStartMonthId(String accountingYearStartMonthId) {
    this.accountingYearStartMonthId = accountingYearStartMonthId;
  }

  public String getAccountingYearStartMonthDescription() {
    return accountingYearStartMonthDescription;
  }

  public void setAccountingYearStartMonthDescription(String accountingYearStartMonthDescription) {
    this.accountingYearStartMonthDescription = accountingYearStartMonthDescription;
  }
  public Date getAccountingEffectiveDate() {
    return accountingEffectiveDate;
  }
  public void setAccountingEffectiveDate(Date accountingEffectiveDate) {
    this.accountingEffectiveDate = accountingEffectiveDate;
  }

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
  public Address getPrimaryAddress() {
    return primaryAddress;
  }
  public void setPrimaryAddress(Address primaryAddress) {
    this.primaryAddress = primaryAddress;
  }
  public List<TaxPayerTaxType> getTpTaxTypes() {
    return tpTaxTypes;
  }
  public void setTpTaxTypes(List<TaxPayerTaxType> tpTaxTypes) {
    this.tpTaxTypes = tpTaxTypes;
  }
  public List<ContactInformation> getContactInformation() {
    return contactInformation;
  }
  public void setContactInformation(List<ContactInformation> contactInformation) {
    this.contactInformation = contactInformation;
  }
  public Address getAddresses() {
    return addresses;
  }
  public void setAddresses(Address addresses) {
    this.addresses = addresses;
  }
  public TaxPayerTaxType getTaxTypes() {
    return taxTypes;
  }
  public void setTaxTypes(TaxPayerTaxType taxTypes) {
    this.taxTypes = taxTypes;
  }
  public TaxPayerFormType getFormTypes() {
    return formTypes;
  }
  public void setFormTypes(TaxPayerFormType formTypes) {
    this.formTypes = formTypes;
  }

  public String getTaxpayerClassificationId() {
    return taxpayerClassificationId;
  }

  public void setTaxpayerClassificationId(String taxpayerClassificationId) {
    this.taxpayerClassificationId = taxpayerClassificationId;
  }

  public String getTaxpayerClassificationDescription() {
    return taxpayerClassificationDescription;
  }

  public void setTaxpayerClassificationDescription(String taxpayerClassificationDescription) {
    this.taxpayerClassificationDescription = taxpayerClassificationDescription;
  }

  public Date getSubmissionDate() {
    return submissionDate;
  }

  public void setSubmissionDate(Date submissionDate) {
    this.submissionDate = submissionDate;
  }

  public Boolean getTaxpayerTypeIsBusiness() {
    return taxpayerTypeIsBusiness;
  }

  public void setTaxpayerTypeIsBusiness(Boolean taxpayerTypeIsBusiness) {
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
  }
  public String getDocumentLocatorNumber() {
    return documentLocatorNumber;
  }

  public void setDocumentLocatorNumber(String documentLocatorNumber) {
    this.documentLocatorNumber = documentLocatorNumber;
  }

  public Date getTinIssuanceDate() {
    return tinIssuanceDate;
  }

  public void setTinIssuanceDate(Date tinIssuanceDate) {
    this.tinIssuanceDate = tinIssuanceDate;
  }

  public String getTaxpayerGroupId() {
    return taxpayerGroupId;
  }

  public void setTaxpayerGroupId(String taxpayerGroupId) {
    this.taxpayerGroupId = taxpayerGroupId;
  }

  public String getTaxpayerGroupDescription() {
    return taxpayerGroupDescription;
  }

  public void setTaxpayerGroupDescription(String taxpayerGroupDescription) {
    this.taxpayerGroupDescription = taxpayerGroupDescription;
  }

  public Boolean getIsTamp() {
    return isTamp;
  }

  public void setIsTamp(Boolean isTamp) {
    this.isTamp = isTamp;
  }

  public Boolean getIsVIP() {
    return isVIP;
  }

  public void setIsVIP(Boolean isVIP) {
    this.isVIP = isVIP;
  }

  public Date getIncorporationDate() {
    return incorporationDate;
  }

  public void setIncorporationDate(Date incorporationDate) {
    this.incorporationDate = incorporationDate;
  }

  public String getRegulatoryBodyId() {
    return regulatoryBodyId;
  }

  public void setRegulatoryBodyId(String regulatoryBodyId) {
    this.regulatoryBodyId = regulatoryBodyId;
  }

  public String getRegulatoryBodyDescription() {
    return regulatoryBodyDescription;
  }

  public void setRegulatoryBodyDescription(String regulatoryBodyDescription) {
    this.regulatoryBodyDescription = regulatoryBodyDescription;
  }

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
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

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

}
