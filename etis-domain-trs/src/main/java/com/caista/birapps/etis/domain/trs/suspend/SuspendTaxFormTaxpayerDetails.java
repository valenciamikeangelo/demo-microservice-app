/*
  * Modified by: decinam
  * Last updated: Feb 25, 2019 2:07:03 PM
  */
package com.caista.birapps.etis.domain.trs.suspend;

import java.util.*;
import org.apache.commons.lang3.StringUtils;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.caista.birapps.etis.domain.trs.utils.enums.*;

/**
 * The Class SuspendTaxFormTaxpayerDetails.
 */
public class SuspendTaxFormTaxpayerDetails {

  private String transactionId;
  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private Date tinIssuanceDate;
  private String registeredName;
  private String trustName;
  private String estateName;
  private String firstName;
  private String lastName;
  private String middleName;
  private String suffixId;
  private String suffixDescription;
  private Date birthDate;
  private Date dateOfRegistration;
  private String status;
  private TinStatusEnum tinStatus;
  private BusinessStatusEnum businessStatus;
  private Long officeId;
  private String officeDescription;
  private String officeCode;
  private String taxpayerTypeId;
  private String taxpayerTypeDescription;
  private Boolean taxpayerTypeIsBusiness;
  private String taxpayerClassificationId;
  private String taxpayerClassificationDescription;
  private String taxpayerClassificationCode;
  private Date suspensionStartDate;
  private Date suspensionEndDate;
  private String reasonId;
  private String reasonDescription;
  private String remarks;
  private List<TaxPayerTaxType> tpTaxTypes;
  private List<TaxPayerTaxType> suspendTaxTypes;
  private List<TaxPayerFormType> suspendFormTypes;
  private TaxPayerTaxType suspendTaxType;
  private TaxPayerFormType suspendFormType;
  private Boolean isTaxType;
  private String type;
  private String transactionNumber;
  private String dln;
  private String taxpayerRegistrationType;
  private String taskNumber;

  private List<ContactInformation> contactInformation;

  public SuspendTaxFormTaxpayerDetails() {
    super();
    // TODO Auto-generated constructor stub
  }

  public String getTransactionNumber() {
    return transactionNumber;
  }

  public void setTransactionNumber(String transactionNumber) {
    this.transactionNumber = transactionNumber;
  }

  public String getDln() {
    return dln;
  }

  public void setDln(String dln) {
    this.dln = dln;
  }

  public String getTaxpayerRegistrationType() {
    return taxpayerRegistrationType;
  }

  public void setTaxpayerRegistrationType(String taxpayerRegistrationType) {
    this.taxpayerRegistrationType = taxpayerRegistrationType;
  }

  public String getTaskNumber() {
    return StringUtils.leftPad(taskNumber, 8, "0");
  }

  public void setTaskNumber(String taskNumber) {
    this.taskNumber = taskNumber;
  }

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

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public Date getTinIssuanceDate() {
    return tinIssuanceDate;
  }

  public void setTinIssuanceDate(Date tinIssuanceDate) {
    this.tinIssuanceDate = tinIssuanceDate;
  }

  public String getRegisteredName() {
    return registeredName;
  }

  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
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
    if (null == this.middleName) {
      middleName = "";
    }
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getSuffixId() {
    return suffixId;
  }

  public void setSuffixId(String suffixId) {
    this.suffixId = suffixId;
  }

  public String getSuffixDescription() {
    if (null == this.suffixDescription) {
      return "";
    }
    return suffixDescription;
  }

  public void setSuffixDescription(String suffixDescription) {
    this.suffixDescription = suffixDescription;
  }

  public Date getDateOfRegistration() {
    return dateOfRegistration;
  }

  public void setDateOfRegistration(Date dateOfRegistration) {
    this.dateOfRegistration = dateOfRegistration;
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

  public String getTaxpayerClassificationCode() {
    return taxpayerClassificationCode;
  }

  public void setTaxpayerClassificationCode(String taxpayerClassificationCode) {
    this.taxpayerClassificationCode = taxpayerClassificationCode;
  }

  public Date getSuspensionStartDate() {
    return suspensionStartDate;
  }

  public void setSuspensionStartDate(Date suspensionStartDate) {
    this.suspensionStartDate = suspensionStartDate;
  }

  public Date getSuspensionEndDate() {
    return suspensionEndDate;
  }

  public void setSuspensionEndDate(Date suspensionEndDate) {
    this.suspensionEndDate = suspensionEndDate;
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

  public List<TaxPayerTaxType> getTpTaxTypes() {
    return tpTaxTypes;
  }

  public void setTpTaxTypes(List<TaxPayerTaxType> tpTaxTypes) {
    this.tpTaxTypes = tpTaxTypes;
  }

  public List<TaxPayerTaxType> getSuspendTaxTypes() {
    return suspendTaxTypes;
  }

  public void setSuspendTaxTypes(List<TaxPayerTaxType> suspendTaxTypes) {
    this.suspendTaxTypes = suspendTaxTypes;
  }

  public List<TaxPayerFormType> getSuspendFormTypes() {
    return suspendFormTypes;
  }

  public void setSuspendFormTypes(List<TaxPayerFormType> suspendFormTypes) {
    this.suspendFormTypes = suspendFormTypes;
  }

  public TaxPayerTaxType getSuspendTaxType() {
    return suspendTaxType;
  }

  public void setSuspendTaxType(TaxPayerTaxType suspendTaxType) {
    this.suspendTaxType = suspendTaxType;
  }

  public TaxPayerFormType getSuspendFormType() {
    return suspendFormType;
  }

  public void setSuspendFormType(TaxPayerFormType suspendFormType) {
    this.suspendFormType = suspendFormType;
  }

  public Boolean getIsTaxType() {
    return isTaxType;
  }

  public void setIsTaxType(Boolean isTaxType) {
    this.isTaxType = isTaxType;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<ContactInformation> getContactInformation() {
    return contactInformation;
  }

  public void setContactInformation(List<ContactInformation> contactInformation) {
    this.contactInformation = contactInformation;
  }

  public SuspendTaxFormTaxpayerDetails(String transactionId, Long taxpayerId, String tin,
      String branchCode, Date tinIssuanceDate, String registeredName, String trustName,
      String estateName, String firstName, String lastName, String middleName, String suffixId,
      String suffixDescription, Date birthDate, Date dateOfRegistration, String status,
      TinStatusEnum tinStatus, BusinessStatusEnum businessStatus, Long officeId,
      String officeDescription, String officeCode, String taxpayerTypeId,
      String taxpayerTypeDescription, Boolean taxpayerTypeIsBusiness,
      String taxpayerClassificationId, String taxpayerClassificationDescription,
      String taxpayerClassificationCode, Date suspensionStartDate, Date suspensionEndDate,
      String reasonId, String reasonDescription, String remarks, List<TaxPayerTaxType> tpTaxTypes,
      List<TaxPayerTaxType> suspendTaxTypes, List<TaxPayerFormType> suspendFormTypes,
      TaxPayerTaxType suspendTaxType, TaxPayerFormType suspendFormType, Boolean isTaxType,
      String type, String transactionNumber, String dln, String taxpayerRegistrationType,
      String taskNumber, List<ContactInformation> contactInformation) {
    super();
    this.transactionId = transactionId;
    this.taxpayerId = taxpayerId;
    this.tin = tin;
    this.branchCode = branchCode;
    this.tinIssuanceDate = tinIssuanceDate;
    this.registeredName = registeredName;
    this.trustName = trustName;
    this.estateName = estateName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.suffixId = suffixId;
    this.suffixDescription = suffixDescription;
    this.birthDate = birthDate;
    this.dateOfRegistration = dateOfRegistration;
    this.status = status;
    this.tinStatus = tinStatus;
    this.businessStatus = businessStatus;
    this.officeId = officeId;
    this.officeDescription = officeDescription;
    this.officeCode = officeCode;
    this.taxpayerTypeId = taxpayerTypeId;
    this.taxpayerTypeDescription = taxpayerTypeDescription;
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
    this.taxpayerClassificationId = taxpayerClassificationId;
    this.taxpayerClassificationDescription = taxpayerClassificationDescription;
    this.taxpayerClassificationCode = taxpayerClassificationCode;
    this.suspensionStartDate = suspensionStartDate;
    this.suspensionEndDate = suspensionEndDate;
    this.reasonId = reasonId;
    this.reasonDescription = reasonDescription;
    this.remarks = remarks;
    this.tpTaxTypes = tpTaxTypes;
    this.suspendTaxTypes = suspendTaxTypes;
    this.suspendFormTypes = suspendFormTypes;
    this.suspendTaxType = suspendTaxType;
    this.suspendFormType = suspendFormType;
    this.isTaxType = isTaxType;
    this.type = type;
    this.transactionNumber = transactionNumber;
    this.dln = dln;
    this.taxpayerRegistrationType = taxpayerRegistrationType;
    this.taskNumber = taskNumber;
    this.contactInformation = contactInformation;
  }


}
