/*
  * Modified by: decinam
  * Last updated: May 16, 2019 12:01:33 PM
  */
package com.caista.birapps.etis.domain.trs.reactivate;

import java.util.Date;
import java.util.List;

import com.caista.birapps.etis.domain.trs.entity.ContactInformation;
import com.caista.birapps.etis.domain.trs.entity.TaxPayerTaxType;
import com.caista.birapps.etis.domain.trs.utils.enums.BusinessStatusEnum;
import com.caista.birapps.etis.domain.trs.utils.enums.TinStatusEnum;

/**
 * The Class ReactivateDeregisteredTPDetails.
 */
public class ReactivateDeregisteredTPDetails {

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

	/** The estate name. */
	private String estateName;

	/** The trust name. */
	private String trustName;

	private String suffixId;

	private String suffixDescription;

	private Date tinIssuanceDate;

	/** The registration date. */
	private Date submissionDate;

	/** The deregistration date. */
	private Date deregistrationDate;

	/** The status. */
	private String status;

	/** The tin status. */
	private TinStatusEnum tinStatus;

	/** The business status. */
	private BusinessStatusEnum businessStatus;

	private Long officeId;

	private String officeDescription;

	private String officeCode;

	private String taxpayerTypeId;

	private String taxpayerTypeDescription;

	private Boolean taxpayerTypeIsBusiness;

	private String taxpayerClassificationId;

	private String taxpayerClassificationDescription;

	private String reasonId;

	private String reasonDescription;

	private String documentLocatorNumber;

	/** The remarks. */
	private String remarks;

	/** The type. */
	private String type;

	/** The tp tax types. */
	private List<TaxPayerTaxType> tpTaxTypes;

	private String transactionNumber;

	private String taskNumber;

	/** The contact information. */
	private List<ContactInformation> contactInformations;

	private String accountingPeriodId;

	private Date registrationDate;

	/**
	 * Instantiates a new reactivate deregistered TP details.
	 */
	public ReactivateDeregisteredTPDetails() {
		super();
	}

	public ReactivateDeregisteredTPDetails(Long taxpayerId, String tin, String branchCode, String registeredName,
			String firstName, String lastName, String middleName, String estateName, String trustName, String suffixId,
			String suffixDescription, Date tinIssuanceDate, Date submissionDate, Date deregistrationDate, String status,
			TinStatusEnum tinStatus, BusinessStatusEnum businessStatus, Long officeId, String officeDescription,
			String officeCode, String taxpayerTypeId, String taxpayerTypeDescription, Boolean taxpayerTypeIsBusiness,
			String taxpayerClassificationId, String taxpayerClassificationDescription, String reasonId,
			String reasonDescription, String documentLocatorNumber, String remarks, String type,
			List<TaxPayerTaxType> tpTaxTypes, String transactionNumber, String taskNumber,
			List<ContactInformation> contactInformations, String accountingPeriodId, Date registrationDate) {
		super();
		this.taxpayerId = taxpayerId;
		this.tin = tin;
		this.branchCode = branchCode;
		this.registeredName = registeredName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.estateName = estateName;
		this.trustName = trustName;
		this.suffixId = suffixId;
		this.suffixDescription = suffixDescription;
		this.tinIssuanceDate = tinIssuanceDate;
		this.submissionDate = submissionDate;
		this.deregistrationDate = deregistrationDate;
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
		this.reasonId = reasonId;
		this.reasonDescription = reasonDescription;
		this.documentLocatorNumber = documentLocatorNumber;
		this.remarks = remarks;
		this.type = type;
		this.tpTaxTypes = tpTaxTypes;
		this.transactionNumber = transactionNumber;
		this.taskNumber = taskNumber;
		this.contactInformations = contactInformations;
		this.accountingPeriodId = accountingPeriodId;
		this.registrationDate = registrationDate;
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

	public Date getTinIssuanceDate() {
		return tinIssuanceDate;
	}

	public void setTinIssuanceDate(Date tinIssuanceDate) {
		this.tinIssuanceDate = tinIssuanceDate;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Date getDeregistrationDate() {
		return deregistrationDate;
	}

	public void setDeregistrationDate(Date deregistrationDate) {
		this.deregistrationDate = deregistrationDate;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<TaxPayerTaxType> getTpTaxTypes() {
		return tpTaxTypes;
	}

	public void setTpTaxTypes(List<TaxPayerTaxType> tpTaxTypes) {
		this.tpTaxTypes = tpTaxTypes;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public Boolean getTaxpayerTypeIsBusiness() {
		return taxpayerTypeIsBusiness;
	}

	public void setTaxpayerTypeIsBusiness(Boolean taxpayerTypeIsBusiness) {
		this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public String getDocumentLocatorNumber() {
		return documentLocatorNumber;
	}

	public void setDocumentLocatorNumber(String documentLocatorNumber) {
		this.documentLocatorNumber = documentLocatorNumber;
	}

	public List<ContactInformation> getContactInformations() {
		return contactInformations;
	}

	public void setContactInformations(List<ContactInformation> contactInformations) {
		this.contactInformations = contactInformations;
	}

	public String getAccountingPeriodId() {
		return accountingPeriodId;
	}

	public void setAccountingPeriodId(String accountingPeriodId) {
		this.accountingPeriodId = accountingPeriodId;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "ReactivateDeregisteredTPDetails [taxpayerId=" + taxpayerId + ", tin=" + tin + ", branchCode="
				+ branchCode + ", registeredName=" + registeredName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", middleName=" + middleName + ", estateName=" + estateName + ", trustName=" + trustName
				+ ", suffixId=" + suffixId + ", suffixDescription=" + suffixDescription + ", tinIssuanceDate="
				+ tinIssuanceDate + ", submissionDate=" + submissionDate + ", deregistrationDate=" + deregistrationDate
				+ ", status=" + status + ", tinStatus=" + tinStatus + ", businessStatus=" + businessStatus
				+ ", officeId=" + officeId + ", officeDescription=" + officeDescription + ", officeCode=" + officeCode
				+ ", taxpayerTypeId=" + taxpayerTypeId + ", taxpayerTypeDescription=" + taxpayerTypeDescription
				+ ", taxpayerTypeIsBusiness=" + taxpayerTypeIsBusiness + ", taxpayerClassificationId="
				+ taxpayerClassificationId + ", taxpayerClassificationDescription=" + taxpayerClassificationDescription
				+ ", reasonId=" + reasonId + ", reasonDescription=" + reasonDescription + ", documentLocatorNumber="
				+ documentLocatorNumber + ", remarks=" + remarks + ", type=" + type + ", tpTaxTypes=" + tpTaxTypes
				+ ", transactionNumber=" + transactionNumber + ", taskNumber=" + taskNumber + ", contactInformations="
				+ contactInformations + ", accountingPeriodId=" + accountingPeriodId + "]";
	}

}
