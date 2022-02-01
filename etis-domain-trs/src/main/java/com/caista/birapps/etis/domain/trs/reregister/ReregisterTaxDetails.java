/*
  * Modified by: decinam
  * Last updated: Apr 23, 2019 5:33:16 PM
  */
package com.caista.birapps.etis.domain.trs.reregister;

import java.util.*;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.caista.birapps.etis.domain.trs.utils.enums.TinStatusEnum;

public class ReregisterTaxDetails {

  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private Long officeId;
  private String officeDescription;
  private String officeCode;
  private String taxpayerClassificationId;
  private String taxpayerClassificationDescription;
  private List<ContactInformation> contactInformation;
  private String registeredName;
  private String firstName;
  private String middleName;
  private String lastName;
  private String trustName;
  private String estateName;
  private List<TaxPayerTaxType> tpTaxTypes;
  private Date registrationDate;
  private TinStatusEnum tinStatus;
  private String taxpayerTypeId;
  private String taxpayerTypeDescription;
  private String suffixId;
  private String suffixDescription;
  private Date tinIssuanceDate;
  private String accountingPeriodId;
  private Date accountingEffectiveDate;

  public ReregisterTaxDetails() {
    super();
  }

  public ReregisterTaxDetails(Long taxpayerId, String tin, String branchCode, Long officeId,
      String officeDescription, String officeCode, String taxpayerClassificationId,
      String taxpayerClassificationDescription, List<ContactInformation> contactInformation,
      String registeredName, String firstName, String middleName, String lastName,
      List<TaxPayerTaxType> tpTaxTypes, Date registrationDate, TinStatusEnum tinStatus,
      String taxpayerTypeId, String taxpayerTypeDescription, String suffixId,
      String suffixDescription, Date tinIssuanceDate, String trustName, String estateName,
      String accountingPeriodId, Date accountingEffectiveDate) {
    super();
    this.taxpayerId = taxpayerId;
    this.tin = tin;
    this.branchCode = branchCode;
    this.officeId = officeId;
    this.officeDescription = officeDescription;
    this.officeCode = officeCode;
    this.taxpayerClassificationId = taxpayerClassificationId;
    this.taxpayerClassificationDescription = taxpayerClassificationDescription;
    this.contactInformation = contactInformation;
    this.registeredName = registeredName;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.tpTaxTypes = tpTaxTypes;
    this.registrationDate = registrationDate;
    this.tinStatus = tinStatus;
    this.taxpayerTypeId = taxpayerTypeId;
    this.taxpayerTypeDescription = taxpayerTypeDescription;
    this.suffixId = suffixId;
    this.suffixDescription = suffixDescription;
    this.tinIssuanceDate = tinIssuanceDate;
    this.trustName = trustName;
    this.estateName = estateName;
    this.accountingPeriodId = accountingPeriodId;
    this.accountingEffectiveDate = accountingEffectiveDate;
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

  public List<ContactInformation> getContactInformation() {
    return contactInformation;
  }

  public void setContactInformation(List<ContactInformation> contactInformation) {
    this.contactInformation = contactInformation;
  }

  public String getRegisteredName() {
    return registeredName;
  }

  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
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

  public List<TaxPayerTaxType> getTpTaxTypes() {
    return tpTaxTypes;
  }

  public void setTpTaxTypes(List<TaxPayerTaxType> tpTaxTypes) {
    this.tpTaxTypes = tpTaxTypes;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public TinStatusEnum getTinStatus() {
    return tinStatus;
  }

  public void setTinStatus(TinStatusEnum tinStatus) {
    this.tinStatus = tinStatus;
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

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public Date getTinIssuanceDate() {
    return tinIssuanceDate;
  }

  public void setTinIssuanceDate(Date tinIssuanceDate) {
    this.tinIssuanceDate = tinIssuanceDate;
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

  public String getAccountingPeriodId() {
    return accountingPeriodId;
  }

  public void setAccountingPeriodId(String accountingPeriodId) {
    this.accountingPeriodId = accountingPeriodId;
  }

  public Date getAccountingEffectiveDate() {
    return accountingEffectiveDate;
  }

  public void setAccountingEffectiveDate(Date accountingEffectiveDate) {
    this.accountingEffectiveDate = accountingEffectiveDate;
  }

}
