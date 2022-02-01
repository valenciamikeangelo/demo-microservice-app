/*
  * Modified by: bongalr
  * Last updated: Oct 29, 2018 5:35:44 PM
  */
package com.caista.birapps.etis.domain.trs;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.caista.birapps.etis.domain.trs.utils.TaxpayerUtil;

public class BusinessSummaryDetails {

  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private String tinStatus;
  private String firstName;
  private String lastName;
  private String middleName;
  private String registeredName;
  private String birthDate;
  private String suffixId;
  private String suffixDescription;
  private String taxpayerClassificationId;
  private String taxpayerClassificationDescription;
  private String taxpayerClassificationCode;
  private String taxpayerTypeId;
  private String taxpayerTypeDescription;
  private Address primaryAddress;
  private List<BusinessSummaryDetail> businessSummaryDetail;
  private List<ContactInformation> contactInformation;
  private String formattedPrimaryAddress;
  private String tinAndBranchCode;
  private String fullName;

  public BusinessSummaryDetails() {
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

  public String getTinStatus() {
    return tinStatus;
  }

  public void setTinStatus(String tinStatus) {
    this.tinStatus = tinStatus;
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

  public Address getPrimaryAddress() {
    return primaryAddress;
  }

  public void setPrimaryAddress(Address primaryAddress) {
    this.primaryAddress = primaryAddress;
  }

  public List<ContactInformation> getContactInformation() {
    return contactInformation;
  }

  public void setContactInformation(List<ContactInformation> contactInformation) {
    this.contactInformation = contactInformation;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public String getFormattedPrimaryAddress() {
    if (getPrimaryAddress() != null) {
      this.formattedPrimaryAddress = TaxpayerUtil.concatAddress(getPrimaryAddress());
    } else {
      this.formattedPrimaryAddress = "N/A";
    }
    return formattedPrimaryAddress;
  }

  public String getTinAndBranchCode() {
    this.tinAndBranchCode = getTin() + "-" + getBranchCode();
    return tinAndBranchCode;
  }

  public String getFullname() {
    if(StringUtils.isNotBlank(getFirstName())
			&& StringUtils.isNotBlank(getMiddleName())
			&& StringUtils.isNotBlank(getLastName())
			&& StringUtils.isNotBlank(getSuffixId())){
		
		this.fullName = getFirstName() + " " + getMiddleName() + " " + getLastName() + " " + getSuffixDescription();
		
	}else if(StringUtils.isNotBlank(getFirstName())
			&& StringUtils.isBlank(getMiddleName())
			&& StringUtils.isNotBlank(getLastName())
			&& StringUtils.isBlank(getSuffixId())){
		
		this.fullName = getFirstName() + " " + getLastName();
		
	}else if(StringUtils.isNotBlank(getFirstName())
			&& StringUtils.isNotBlank(getMiddleName())
			&& StringUtils.isNotBlank(getLastName())
			&& StringUtils.isBlank(getSuffixId())){
		
		this.fullName = getFirstName() + " " + getMiddleName() + " " + getLastName();
		
	}else if(StringUtils.isNotBlank(getFirstName())
			&& StringUtils.isBlank(getMiddleName())
			&& StringUtils.isNotBlank(getLastName())
			&& StringUtils.isNotBlank(getSuffixId())){
		
		this.fullName = getFirstName() + " " + getLastName() + " " + getSuffixDescription();
		
	}else{
		this.fullName = "N/A";
	}
	return fullName;
  }

  public String getTaxpayerClassificationCode() {
    return taxpayerClassificationCode;
  }

  public void setTaxpayerClassificationCode(String taxpayerClassificationCode) {
    this.taxpayerClassificationCode = taxpayerClassificationCode;
  }

  public List<BusinessSummaryDetail> getBusinessSummaryDetail() {
    return businessSummaryDetail;
  }

  public void setBusinessSummaryDetail(List<BusinessSummaryDetail> businessSummaryDetail) {
    this.businessSummaryDetail = businessSummaryDetail;
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

}
