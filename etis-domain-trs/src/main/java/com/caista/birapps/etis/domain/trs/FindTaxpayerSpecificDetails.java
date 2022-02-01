/*
  * Modified by: obregoj
  * Last updated: 04 7, 20 10:10:31 AM
  */
package com.caista.birapps.etis.domain.trs;

import java.util.*;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.caista.birapps.etis.domain.trs.utils.TaxpayerUtil;

public class FindTaxpayerSpecificDetails {
  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private String firstName;
  private String lastName;
  private String middleName;
  private String registeredName;
  private String suffixId;
  private String suffixDescription;
  private String officeId;
  private String officeDescription;
  private String officeCode;
  private String taxpayerClassificationId;
  private String taxpayerClassificationDescription;
  private String taxpayerGroupId;
  private String taxpayerGroupDescription;
  private String taxpayerTypeId;
  private String taxpayerTypeDescription;
  private String tinStatus;
  private Address primaryAddress;
  private List<BusinessSummaryDetail> businessSummaryDetail;
  private String formattedPrimaryAddress;
  private String displayName;
  private Date birthDate;
  private Date incorporationDate;
  private Date organizationDate;


  public FindTaxpayerSpecificDetails() {
    super();
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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
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

  public String getOfficeId() {
    return officeId;
  }

  public void setOfficeId(String officeId) {
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

  public String getRegisteredName() {
    return registeredName;
  }

  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
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

  public String getTinStatus() {
    return tinStatus;
  }

  public void setTinStatus(String tinStatus) {
    this.tinStatus = tinStatus;
  }

  public Address getPrimaryAddress() {
    return primaryAddress;
  }

  public void setPrimaryAddress(Address primaryAddress) {
    this.primaryAddress = primaryAddress;
  }

  public String getFormattedPrimaryAddress() {
    if (getPrimaryAddress() != null) {
      this.formattedPrimaryAddress = TaxpayerUtil.concatAddress(getPrimaryAddress());
    } else {
      this.formattedPrimaryAddress = "N/A";
    }
    return formattedPrimaryAddress;
  }

  public List<BusinessSummaryDetail> getBusinessSummaryDetail() {
    return businessSummaryDetail;
  }

  public void setBusinessSummaryDetail(List<BusinessSummaryDetail> businessSummaryDetail) {
    this.businessSummaryDetail = businessSummaryDetail;
  }

  public String getDisplayName() {
    this.displayName = TaxpayerUtil.getTaxPayerName(registeredName, null, null, firstName,
        middleName, lastName, suffixDescription);
    return displayName;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
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

}
