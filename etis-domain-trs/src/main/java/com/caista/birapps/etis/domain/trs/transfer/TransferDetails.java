/*
  * Modified by: romerov
  * Last updated: 06 11, 20 8:33:33 AM
*/

package com.caista.birapps.etis.domain.trs.transfer;

import java.util.*;
import org.springframework.web.multipart.MultipartFile;
import com.caista.birapps.etis.domain.trs.entity.TaxpayerAttachment;
import com.caista.birapps.etis.domain.trs.utils.enums.*;
import com.google.gson.Gson;

/**
 * The Class TransferDetails.
 */
public class TransferDetails {

  private String transactionNumber;
  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private String registeredName;
  private String estateName;
  private String trustName;
  private String firstName;
  private String lastName;
  private String middleName;
  private String suffixId;
  private String suffixDescription;
  private Date registrationDate;
  private Date organizationDate;
  private Date incorporationDate;
  private String status;
  private TinStatusEnum tinStatus;
  private BusinessStatusEnum businessStatus;
  private String taxpayerTypeId;
  private String taxpayerTypeDescription;
  private String taxpayerClassificationId;
  private Boolean taxpayerTypeIsBusiness;
  private Date tinIssuanceDate;

  private String transferCommitmentForm;
  private Date accessibilityExpireOldRdo;
  private String dln;
  private String reasonId;
  private String reasonDescription;
  private String remarks;
  private String transferredBy;
  private Date transferredDate;

  private Long officeId;
  private String officeCode;
  private String officeDescription;
  private Boolean officeLargeTaxpayerOfficeFlag;
  private Long oldOfficeId;
  private String oldOfficeCode;
  private String oldOfficeDescription;
  private Boolean oldOfficeLargeTaxpayerOfficeFlag;
  private Long userOfficeId;
  private String userOfficeCode;
  private String userOfficeDescription;
  private Boolean userOfficeLargeTaxpayerOfficeFlag;

  private List<TaxpayerAttachment> documentaryRequirements;
  private MultipartFile[] files;

  private List<MultipartFileInfo> filesInfo;

  public String getTransactionNumber() {
    return transactionNumber;
  }

  public void setTransactionNumber(String transactionNumber) {
    this.transactionNumber = transactionNumber;
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

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public Date getOrganizationDate() {
    return organizationDate;
  }

  public void setOrganizationDate(Date organizationDate) {
    this.organizationDate = organizationDate;
  }

  public Date getIncorporationDate() {
    return incorporationDate;
  }

  public void setIncorporationDate(Date incorporationDate) {
    this.incorporationDate = incorporationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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

  public String getTaxpayerTypeId() {
    return taxpayerTypeId;
  }

  public void setTaxpayerTypeId(String taxpayerTypeId) {
    this.taxpayerTypeId = taxpayerTypeId;
  }

  public String getTaxpayerClassificationId() {
    return taxpayerClassificationId;
  }

  public void setTaxpayerClassificationId(String taxpayerClassificationId) {
    this.taxpayerClassificationId = taxpayerClassificationId;
  }

  public Boolean getTaxpayerTypeIsBusiness() {
    return taxpayerTypeIsBusiness;
  }

  public void setTaxpayerTypeIsBusiness(Boolean taxpayerTypeIsBusiness) {
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
  }

  public String getTransferCommitmentForm() {
    return transferCommitmentForm;
  }

  public void setTransferCommitmentForm(String transferCommitmentForm) {
    this.transferCommitmentForm = transferCommitmentForm;
  }

  public Date getAccessibilityExpireOldRdo() {
    return accessibilityExpireOldRdo;
  }

  public void setAccessibilityExpireOldRdo(Date accessibilityExpireOldRdo) {
    this.accessibilityExpireOldRdo = accessibilityExpireOldRdo;
  }

  public String getDln() {
    return dln;
  }

  public void setDln(String dln) {
    this.dln = dln;
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

  public String getTransferredBy() {
    return transferredBy;
  }

  public void setTransferredBy(String transferredBy) {
    this.transferredBy = transferredBy;
  }

  public Date getTransferredDate() {
    return transferredDate;
  }

  public void setTransferredDate(Date transferredDate) {
    this.transferredDate = transferredDate;
  }

  public Long getOfficeId() {
    return officeId;
  }

  public void setOfficeId(Long officeId) {
    this.officeId = officeId;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getOfficeDescription() {
    return officeDescription;
  }

  public void setOfficeDescription(String officeDescription) {
    this.officeDescription = officeDescription;
  }

  public Boolean getOfficeLargeTaxpayerOfficeFlag() {
    return officeLargeTaxpayerOfficeFlag;
  }

  public void setOfficeLargeTaxpayerOfficeFlag(Boolean officeLargeTaxpayerOfficeFlag) {
    this.officeLargeTaxpayerOfficeFlag = officeLargeTaxpayerOfficeFlag;
  }

  public Long getOldOfficeId() {
    return oldOfficeId;
  }

  public void setOldOfficeId(Long oldOfficeId) {
    this.oldOfficeId = oldOfficeId;
  }

  public String getOldOfficeCode() {
    return oldOfficeCode;
  }

  public void setOldOfficeCode(String oldOfficeCode) {
    this.oldOfficeCode = oldOfficeCode;
  }

  public String getOldOfficeDescription() {
    return oldOfficeDescription;
  }

  public void setOldOfficeDescription(String oldOfficeDescription) {
    this.oldOfficeDescription = oldOfficeDescription;
  }

  public Boolean getOldOfficeLargeTaxpayerOfficeFlag() {
    return oldOfficeLargeTaxpayerOfficeFlag;
  }

  public void setOldOfficeLargeTaxpayerOfficeFlag(Boolean oldOfficeLargeTaxpayerOfficeFlag) {
    this.oldOfficeLargeTaxpayerOfficeFlag = oldOfficeLargeTaxpayerOfficeFlag;
  }

  public Long getUserOfficeId() {
    return userOfficeId;
  }

  public void setUserOfficeId(Long userOfficeId) {
    this.userOfficeId = userOfficeId;
  }

  public String getUserOfficeCode() {
    return userOfficeCode;
  }

  public void setUserOfficeCode(String userOfficeCode) {
    this.userOfficeCode = userOfficeCode;
  }

  public String getUserOfficeDescription() {
    return userOfficeDescription;
  }

  public void setUserOfficeDescription(String userOfficeDescription) {
    this.userOfficeDescription = userOfficeDescription;
  }

  public Boolean getUserOfficeLargeTaxpayerOfficeFlag() {
    return userOfficeLargeTaxpayerOfficeFlag;
  }

  public void setUserOfficeLargeTaxpayerOfficeFlag(Boolean userOfficeLargeTaxpayerOfficeFlag) {
    this.userOfficeLargeTaxpayerOfficeFlag = userOfficeLargeTaxpayerOfficeFlag;
  }

  public List<TaxpayerAttachment> getDocumentaryRequirements() {
    return documentaryRequirements;
  }

  public void setDocumentaryRequirements(List<TaxpayerAttachment> documentaryRequirements) {
    this.documentaryRequirements = documentaryRequirements;
  }

  public MultipartFile[] getFiles() {
    return files;
  }

  public void setFiles(MultipartFile[] files) {
    this.files = files;
  }

  public String getTaxpayerTypeDescription() {
    return taxpayerTypeDescription;
  }

  public void setTaxpayerTypeDescription(String taxpayerTypeDescription) {
    this.taxpayerTypeDescription = taxpayerTypeDescription;
  }

  public Date getTinIssuanceDate() {
	return tinIssuanceDate;
  }

  public void setTinIssuanceDate(Date tinIssuanceDate) {
	this.tinIssuanceDate = tinIssuanceDate;
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

  public List<MultipartFileInfo> getFilesInfo() {
    return filesInfo;
  }

  public void setFilesInfo(List<MultipartFileInfo> filesInfo) {
    this.filesInfo = filesInfo;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
