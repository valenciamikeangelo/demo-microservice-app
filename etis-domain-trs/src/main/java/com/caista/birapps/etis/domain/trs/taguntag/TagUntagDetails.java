/*
 * Last modified by: delmund
 * Last updated date: Nov 9, 2018 8:53:45 AM
 */
package com.caista.birapps.etis.domain.trs.taguntag;

import java.util.*;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.caista.birapps.etis.domain.trs.utils.enums.*;

public class TagUntagDetails {

  /** The transaction id. */
  private String transactionId;

  /** The taxpayer id. */
  private Long taxpayerId;

  /** The tin. */
  private String tin;

  /** The branch code. */
  private String branchCode;

  /** The registered name. */
  private String registeredName;

  /** The first name. */
  private String firstName;

  /** The last name. */
  private String lastName;

  /** The middle name. */
  private String middleName;

  /** The date of registration. */
  private Date dateOfRegistration;

  /** The status. */
  private String status;

  /** The tin status. */
  private TinStatusEnum tinStatus;

  /** The business status. */
  private BusinessStatusEnum businessStatus;

  private Long officeId;

  private String officeDescription;

  private String taxpayerTypeId;

  private String taxpayerTypeDescription;

  private Boolean taxpayerTypeBusiness;

  private String taxpayerClassificationId;

  private String taxpayerClassificationDescription;

  private String reasonId;

  private String reasonDescription;

  /** The remarks. */
  private String remarks;

  /** The tp tax types. */
  private List<TaxPayerTaxType> tpTaxTypes;

  private List<TaxPayerFormType> tpFormTypes;

  public TagUntagDetails(String transactionId, Long taxpayerId, String tin, String branchCode,
      String registeredName, String firstName, String lastName, String middleName,
      Date dateOfRegistration, String status, TinStatusEnum tinStatus,
      BusinessStatusEnum businessStatus, Long officeId, String officeDescription,
      String taxpayerTypeId, String taxpayerTypeDescription, Boolean taxpayerTypeBusiness,
      String taxpayerClassificationId, String taxpayerClassificationDescription, String reasonId,
      String reasonDescription, String remarks, List<TaxPayerTaxType> tpTaxTypes,
      List<TaxPayerFormType> tpFormTypes) {
    super();
    this.transactionId = transactionId;
    this.taxpayerId = taxpayerId;
    this.tin = tin;
    this.branchCode = branchCode;
    this.registeredName = registeredName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.dateOfRegistration = dateOfRegistration;
    this.status = status;
    this.tinStatus = tinStatus;
    this.businessStatus = businessStatus;
    this.officeId = officeId;
    this.officeDescription = officeDescription;
    this.taxpayerTypeId = taxpayerTypeId;
    this.taxpayerTypeDescription = taxpayerTypeDescription;
    this.taxpayerTypeBusiness = taxpayerTypeBusiness;
    this.taxpayerClassificationId = taxpayerClassificationId;
    this.taxpayerClassificationDescription = taxpayerClassificationDescription;
    this.reasonId = reasonId;
    this.reasonDescription = reasonDescription;
    this.remarks = remarks;
    this.tpTaxTypes = tpTaxTypes;
    this.tpFormTypes = tpFormTypes;
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

  public Boolean getTaxpayerTypeBusiness() {
    return taxpayerTypeBusiness;
  }

  public void setTaxpayerTypeBusiness(Boolean taxpayerTypeBusiness) {
    this.taxpayerTypeBusiness = taxpayerTypeBusiness;
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

  public List<TaxPayerFormType> getTpFormTypes() {
    return tpFormTypes;
  }

  public void setTpFormTypes(List<TaxPayerFormType> tpFormTypes) {
    this.tpFormTypes = tpFormTypes;
  }


}
