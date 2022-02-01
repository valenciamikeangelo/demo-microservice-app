/*
 * Modified by: fuentem Last updated: Nov 29, 2018 2:29:27 PM
 */
package com.caista.birapps.etis.domain.trs.taskmanager;

import java.util.Date;
import java.util.List;

import com.caista.birapps.etis.domain.trs.entity.Address;
import com.caista.birapps.etis.domain.trs.entity.BookOfAccount;
import com.caista.birapps.etis.domain.trs.entity.BusinessSummaryDetail;
import com.caista.birapps.etis.domain.trs.entity.IncentiveDetail;
import com.caista.birapps.etis.domain.trs.entity.ProfessionalInformation;

public class UpdateTaxpayerTaskManagerRequest {

  private Long taxpayerId;
  private String taxpayerTypeId;
  private String taxpayerTypeDescription;
  private String oldTaxpayerTypeId;
  private String oldTaxpayerTypeDescription;
  private Boolean taxpayerTypeIsBusiness;
  private List<BusinessSummaryDetail> businessSummaryDetails;
  private List<ProfessionalInformation> professionalInformations;
  private List<IncentiveDetail> incentiveDetails;
  private List<BookOfAccount> bookOfAccounts;
  private List<Address> addresses;

  private String firstName;
  private String middleName;
  private String lastName;
  private String suffixDescription;
  private String estateName;
  private String trustName;
  private String tin;
  private String branchCode;
  private String reason;
  private String remarks;
  private Date tinIssuanceDate;
  private String tinStatus;
  private String officeDescription;

  public Long getTaxpayerId() {
    return taxpayerId;
  }

  public void setTaxpayerId(Long taxpayerId) {
    this.taxpayerId = taxpayerId;
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

  public Boolean getTaxpayerTypeIsBusiness() {
    return taxpayerTypeIsBusiness;
  }

  public void setTaxpayerTypeIsBusiness(Boolean taxpayerTypeIsBusiness) {
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
  }

  public List<BusinessSummaryDetail> getBusinessSummaryDetails() {
    return businessSummaryDetails;
  }

  public void setBusinessSummaryDetails(List<BusinessSummaryDetail> businessSummaryDetails) {
    this.businessSummaryDetails = businessSummaryDetails;
  }

  public List<ProfessionalInformation> getProfessionalInformations() {
    return professionalInformations;
  }

  public void setProfessionalInformations(List<ProfessionalInformation> professionalInformations) {
    this.professionalInformations = professionalInformations;
  }

  public List<IncentiveDetail> getIncentiveDetails() {
    return incentiveDetails;
  }

  public void setIncentiveDetails(List<IncentiveDetail> incentiveDetails) {
    this.incentiveDetails = incentiveDetails;
  }

  public List<BookOfAccount> getBookOfAccounts() {
    return bookOfAccounts;
  }

  public void setBookOfAccounts(List<BookOfAccount> bookOfAccounts) {
    this.bookOfAccounts = bookOfAccounts;
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

  public String getSuffixDescription() {
    return suffixDescription;
  }

  public void setSuffixDescription(String suffixDescription) {
    this.suffixDescription = suffixDescription;
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

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Date getTinIssuanceDate() {
    return tinIssuanceDate;
  }

  public void setTinIssuanceDate(Date tinIssuanceDate) {
    this.tinIssuanceDate = tinIssuanceDate;
  }

  public String getTinStatus() {
    return tinStatus;
  }

  public void setTinStatus(String tinStatus) {
    this.tinStatus = tinStatus;
  }

  public String getOfficeDescription() {
    return officeDescription;
  }

  public void setOfficeDescription(String officeDescription) {
    this.officeDescription = officeDescription;
  }

  public String getOldTaxpayerTypeId() {
  	return oldTaxpayerTypeId;
  }
  
  public void setOldTaxpayerTypeId(String oldTaxpayerTypeId) {
  	this.oldTaxpayerTypeId = oldTaxpayerTypeId;
  }
  
  public String getOldTaxpayerTypeDescription() {
  	return oldTaxpayerTypeDescription;
  }
  
  public void setOldTaxpayerTypeDescription(String oldTaxpayerTypeDescription) {
  	this.oldTaxpayerTypeDescription = oldTaxpayerTypeDescription;
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

public List<Address> getAddresses() {
	return addresses;
}

public void setAddresses(List<Address> addresses) {
	this.addresses = addresses;
}

}
