/*
 * Modified by: romerore
 * Last updated: Dec 27, 2018 3:49:53 PM
 */
package com.caista.birapps.etis.domain.trs;

import java.util.Date;
import java.util.List;
import com.caista.birapps.etis.domain.trs.entity.Address;

public class HeadOfficeResult {
  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private String tinStatus;
  private String taxpayerClassificationId;
  private String taxpayerClassificationDescription;
  private Date tinIssuanceDate;
  private Long officeId;
  private String officeDescription;
  private String officeCode;
  private Boolean officeLargeTaxpayerOfficeFlag;
  private String registeringOfficeId;
  private String registeringOfficeDescription;
  private String registrationNumber;
  private String taxpayerTypeId;
  private String taxpayerTypeDescription;
  private String taxpayerTypeIsBusiness;
  private String taxpayerGroupId;
  private String taxpayerGroupDescription;
  private String registeredName;
  private String suffixDescription;
  private String lastName;
  private String firstName;
  private String middleName;
  private String trustName;
  private String estateName;
  private Date incorporationDate;
  private Date organizationDate;
  private String accountingPeriodId;
  private String accountingPeriodDescription;
  private String accountingYearStartMonthId;
  private String accountingYearStartMonthDescription;
  private Date accountingEffectiveDate;
  private Boolean isVip;
  private Boolean isTamp;
  private Boolean isBIRInitiated;
  private Date submissionDate;
  private Boolean maxBranchCode;  

  private List<Address> addresses;

  private Address primaryAddress;

  public HeadOfficeResult() {
    super();
    // TODO Auto-generated constructor stub
  }

  public HeadOfficeResult(Long taxpayerId, String tin, String branchCode, String tinStatus,
      String taxpayerClassificationId, String taxpayerClassificationDescription,
      Date tinIssuanceDate, Long officeId, String officeDescription, String officeCode,
      Boolean officeLargeTaxpayerOfficeFlag, String registeringOfficeId,
      String registeringOfficeDescription, String registrationNumber, 
      String taxpayerTypeId, String taxpayerTypeDescription,
      String taxpayerTypeIsBusiness, String taxpayerGroupId, String taxpayerGroupDescription,
      String registeredName, String suffixDescription, String lastName, String firstName,
      String middleName, String trustName, String estateName, Date incorporationDate,
      Date organizationDate, String accountingPeriodId, String accountingPeriodDescription,
      String accountingYearStartMonthId, String accountingYearStartMonthDescription,
      Date accountingEffectiveDate, Boolean isVip, Boolean isTamp, Boolean isBIRInitiated,
      Date submissionDate, Boolean maxBranchCode, List<Address> addresses, Address primaryAddress) {
    super();
    this.taxpayerId = taxpayerId;
    this.tin = tin;
    this.branchCode = branchCode;
    this.tinStatus = tinStatus;
    this.taxpayerClassificationId = taxpayerClassificationId;
    this.taxpayerClassificationDescription = taxpayerClassificationDescription;
    this.tinIssuanceDate = tinIssuanceDate;
    this.officeId = officeId;
    this.officeDescription = officeDescription;
    this.officeCode = officeCode;
    this.officeLargeTaxpayerOfficeFlag = officeLargeTaxpayerOfficeFlag;
    this.registeringOfficeId = registeringOfficeId;
    this.registeringOfficeDescription = registeringOfficeDescription;
    this.registrationNumber = registrationNumber;
    this.taxpayerTypeId = taxpayerTypeId;
    this.taxpayerTypeDescription = taxpayerTypeDescription;
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
    this.taxpayerGroupId = taxpayerGroupId;
    this.taxpayerGroupDescription = taxpayerGroupDescription;
    this.registeredName = registeredName;
    this.suffixDescription = suffixDescription;
    this.lastName = lastName;
    this.firstName = firstName;
    this.middleName = middleName;
    this.trustName = trustName;
    this.estateName = estateName;
    this.incorporationDate = incorporationDate;
    this.organizationDate = organizationDate;
    this.accountingPeriodId = accountingPeriodId;
    this.accountingPeriodDescription = accountingPeriodDescription;
    this.accountingYearStartMonthId = accountingYearStartMonthId;
    this.accountingYearStartMonthDescription = accountingYearStartMonthDescription;
    this.accountingEffectiveDate = accountingEffectiveDate;
    this.isVip = isVip;
    this.isTamp = isTamp;
    this.isBIRInitiated = isBIRInitiated;
    this.submissionDate = submissionDate;
    this.maxBranchCode = maxBranchCode;
    this.addresses = addresses;
    this.primaryAddress = primaryAddress;
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

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public String getTinStatus() {
    return tinStatus;
  }

  public void setTinStatus(String tinStatus) {
    this.tinStatus = tinStatus;
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

  public Date getTinIssuanceDate() {
    return tinIssuanceDate;
  }

  public void setTinIssuanceDate(Date tinIssuanceDate) {
    this.tinIssuanceDate = tinIssuanceDate;
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

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public Boolean getOfficeLargeTaxpayerOfficeFlag() {
    return officeLargeTaxpayerOfficeFlag;
  }

  public void setOfficeLargeTaxpayerOfficeFlag(Boolean officeLargeTaxpayerOfficeFlag) {
    this.officeLargeTaxpayerOfficeFlag = officeLargeTaxpayerOfficeFlag;
  }

  public String getRegisteringOfficeId() {
    return registeringOfficeId;
  }

  public void setRegisteringOfficeId(String registeringOfficeId) {
    this.registeringOfficeId = registeringOfficeId;
  }

  public String getRegisteringOfficeDescription() {
    return registeringOfficeDescription;
  }

  public void setRegisteringOfficeDescription(String registeringOfficeDescription) {
    this.registeringOfficeDescription = registeringOfficeDescription;
  }
  
  public String getRegistrationNumber() {
	return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
	this.registrationNumber = registrationNumber;
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

  public String getTaxpayerTypeIsBusiness() {
    return taxpayerTypeIsBusiness;
  }

  public void setTaxpayerTypeIsBusiness(String taxpayerTypeIsBusiness) {
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
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

  public String getRegisteredName() {
    return registeredName;
  }

  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
  }

  public String getSuffixDescription() {
    return suffixDescription;
  }

  public void setSuffixDescription(String suffixDescription) {
    this.suffixDescription = suffixDescription;
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

  public String getTrustName() {
    return trustName;
  }

  public void setTrustName(String trustName) {
    this.trustName = trustName;
  }

  public String getEstateName() {
    return estateName;
  }

  public void setEstateName(String estateName) {
    this.estateName = estateName;
  }

  public Date getIncorporationDate() {
    return incorporationDate;
  }

  public void setIncorporationDate(Date incorporationDate) {
    this.incorporationDate = incorporationDate;
  }

  public Date getOrganizationDate() {
    return organizationDate;
  }

  public void setOrganizationDate(Date organizationDate) {
    this.organizationDate = organizationDate;
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

  public Boolean getIsVip() {
    return isVip;
  }

  public void setIsVip(Boolean isVip) {
    this.isVip = isVip;
  }

  public Boolean getIsTamp() {
    return isTamp;
  }

  public void setIsTamp(Boolean isTamp) {
    this.isTamp = isTamp;
  }

  public Boolean getIsBIRInitiated() {
    return isBIRInitiated;
  }

  public void setIsBIRInitiated(Boolean isBIRInitiated) {
    this.isBIRInitiated = isBIRInitiated;
  }

  public Date getSubmissionDate() {
    return submissionDate;
  }

  public void setSubmissionDate(Date submissionDate) {
    this.submissionDate = submissionDate;
  }

  public Boolean getMaxBranchCode() {
    return maxBranchCode;
  }

  public void setMaxBranchCode(Boolean maxBranchCode) {
    this.maxBranchCode = maxBranchCode;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public Address getPrimaryAddress() {
    return primaryAddress;
  }

  public void setPrimaryAddress(Address primaryAddress) {
    this.primaryAddress = primaryAddress;
  }

}
