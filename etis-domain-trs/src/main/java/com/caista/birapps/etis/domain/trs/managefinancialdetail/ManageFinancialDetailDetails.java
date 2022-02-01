/*
 * Modified by: romerov
 * Last updated: Oct 21, 2019 11:46:50 AM
 */
package com.caista.birapps.etis.domain.trs.managefinancialdetail;

import java.util.*;
import org.springframework.util.CollectionUtils;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.caista.birapps.etis.domain.trs.utils.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ManageFinancialDetailDetails {

  private String documentLocatorNumber;
  private String transactionId;
  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private String registeredName;
  private Date registrationDate;
  private TinStatusEnum tinStatus;
  private BusinessStatusEnum businessStatus;
  private Long officeId;
  private String officeDescription;
  private String officeCode;
  private String accountingPeriodId;
  private String accountingPeriodDescription;
  private String accountingYearStartMonthId;
  private String accountingYearStartMonthDescription;
  private String taxpayerTypeId;
  private String taxpayerTypeDescription;
  private Date accountingEffectiveDate;
  private String taxpayerClassificationId;
  private String taxpayerClassificationDescription;
  private Date submissionDate;
  private Date tinIssuanceDate;
  private Boolean taxpayerTypeIsBusiness;
  private Date transactionDate;
  private List<ContactInformation> contactInformation;
  private List<AccountingPeriodHistory> accountingPeriodHistory;
  @JsonIgnore
  private Date lastChangeDate;

  public ManageFinancialDetailDetails() {
    super();
  }

  public ManageFinancialDetailDetails(String transactionId, Long taxpayerId, String tin,
      String branchCode, String registeredName, Date registrationDate, TinStatusEnum tinStatus,
      BusinessStatusEnum businessStatus, Long officeId, String officeDescription,
      String accountingPeriodId, String accountingPeriodDescription,
      String accountingYearStartMonthId, String accountingYearStartMonthDescription,
      String taxpayerTypeId, String taxpayerTypeDescription, Date accountingEffectiveDate,
      String taxpayerClassificationId, String taxpayerClassificationDescription,
      Date submissionDate, Date tinIssuanceDate, Boolean taxpayerTypeIsBusiness,
      Date transactionDate, List<ContactInformation> contactInformation,
      List<AccountingPeriodHistory> accountingPeriodHistory, Date lastChangeDate) {
    super();
    this.transactionId = transactionId;
    this.taxpayerId = taxpayerId;
    this.tin = tin;
    this.branchCode = branchCode;
    this.registeredName = registeredName;
    this.registrationDate = registrationDate;
    this.tinStatus = tinStatus;
    this.businessStatus = businessStatus;
    this.officeId = officeId;
    this.officeDescription = officeDescription;
    this.accountingPeriodId = accountingPeriodId;
    this.accountingPeriodDescription = accountingPeriodDescription;
    this.accountingYearStartMonthId = accountingYearStartMonthId;
    this.accountingYearStartMonthDescription = accountingYearStartMonthDescription;
    this.taxpayerTypeId = taxpayerTypeId;
    this.taxpayerTypeDescription = taxpayerTypeDescription;
    this.accountingEffectiveDate = accountingEffectiveDate;
    this.taxpayerClassificationId = taxpayerClassificationId;
    this.taxpayerClassificationDescription = taxpayerClassificationDescription;
    this.submissionDate = submissionDate;
    this.tinIssuanceDate = tinIssuanceDate;
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
    this.transactionDate = transactionDate;
    this.contactInformation = contactInformation;
    this.accountingPeriodHistory = accountingPeriodHistory;
    this.lastChangeDate = lastChangeDate;
  }

  public ManageFinancialDetailDetails(ManageFinancialDetailDetails manageFinancialDtl) {
    super();
    this.documentLocatorNumber = manageFinancialDtl.getDocumentLocatorNumber();
    this.transactionId = manageFinancialDtl.getTransactionId();
    this.taxpayerId = manageFinancialDtl.getTaxpayerId();
    this.tin = manageFinancialDtl.getTin();
    this.branchCode = manageFinancialDtl.getBranchCode();
    this.registeredName = manageFinancialDtl.getRegisteredName();
    this.registrationDate = manageFinancialDtl.getRegistrationDate();
    this.tinStatus = manageFinancialDtl.getTinStatus();
    this.businessStatus = manageFinancialDtl.getBusinessStatus();
    this.officeId = manageFinancialDtl.getOfficeId();
    this.officeDescription = manageFinancialDtl.getOfficeDescription();
    this.officeCode = manageFinancialDtl.getOfficeCode();
    this.accountingPeriodId = manageFinancialDtl.getAccountingPeriodId();
    this.accountingPeriodDescription = manageFinancialDtl.getAccountingPeriodDescription();
    this.accountingYearStartMonthId = manageFinancialDtl.getAccountingYearStartMonthId();
    this.accountingYearStartMonthDescription = manageFinancialDtl
        .getAccountingYearStartMonthDescription();
    this.taxpayerTypeId = manageFinancialDtl.getTaxpayerTypeId();
    this.taxpayerTypeDescription = manageFinancialDtl.getTaxpayerTypeDescription();
    this.accountingEffectiveDate = manageFinancialDtl.getAccountingEffectiveDate();
    this.taxpayerClassificationId = manageFinancialDtl.getTaxpayerClassificationId();
    this.taxpayerClassificationDescription = manageFinancialDtl
        .getTaxpayerClassificationDescription();
    this.submissionDate = manageFinancialDtl.getSubmissionDate();
    this.tinIssuanceDate = manageFinancialDtl.getTinIssuanceDate();
    this.taxpayerTypeIsBusiness = manageFinancialDtl.getTaxpayerTypeIsBusiness();
    this.transactionDate = manageFinancialDtl.getTransactionDate();
    this.lastChangeDate = manageFinancialDtl.getLastChangeDate();
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

  public Date getAccountingEffectiveDate() {
    return accountingEffectiveDate;
  }

  public void setAccountingEffectiveDate(Date accountingEffectiveDate) {
    this.accountingEffectiveDate = accountingEffectiveDate;
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

  public Date getTinIssuanceDate() {
    return tinIssuanceDate;
  }

  public void setTinIssuanceDate(Date tinIssuanceDate) {
    this.tinIssuanceDate = tinIssuanceDate;
  }

  public Boolean getTaxpayerTypeIsBusiness() {
    return taxpayerTypeIsBusiness;
  }

  public void setTaxpayerTypeIsBusiness(Boolean taxpayerTypeIsBusiness) {
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
  }

  public Date getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }

  public List<ContactInformation> getContactInformation() {
    return contactInformation;
  }

  public void setContactInformation(List<ContactInformation> contactInformation) {
    this.contactInformation = contactInformation;
  }


  public List<AccountingPeriodHistory> getAccountingPeriodHistory() {
    return accountingPeriodHistory;
  }


  public void setAccountingPeriodHistory(List<AccountingPeriodHistory> accountingPeriodHistory) {
    this.accountingPeriodHistory = accountingPeriodHistory;
  }

  public Date getLastChangeDate() {
    if (!CollectionUtils.isEmpty(accountingPeriodHistory)) {
      Optional<Date> lDate = accountingPeriodHistory.stream().map(m -> m.getCreatedDate())
          .max(Date::compareTo);
      if (lDate.isPresent()) {
        this.lastChangeDate = lDate.get();
      }
    }
    return this.lastChangeDate;
  }

  public void setLastChangeDate(Date lastChangeDate) {
    this.lastChangeDate = lastChangeDate;
  }

  public String getDocumentLocatorNumber() {
    return documentLocatorNumber;
  }

  public void setDocumentLocatorNumber(String documentLocatorNumber) {
    this.documentLocatorNumber = documentLocatorNumber;
  }

}
