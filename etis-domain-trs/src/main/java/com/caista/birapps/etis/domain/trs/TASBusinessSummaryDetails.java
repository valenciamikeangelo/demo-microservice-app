/*
 * Modified by: pastolc
 * Last updated: May 14, 2019 1:41:34 PM
 */
package com.caista.birapps.etis.domain.trs;

import java.util.*;
import org.apache.commons.lang3.StringUtils;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.caista.birapps.etis.domain.trs.utils.TaxpayerUtil;

public class TASBusinessSummaryDetails {

	private Long taxpayerId;
	private String tin;
	private String branchCode;
	private String tinStatus;
	private String firstName;
	private String lastName;
	private String middleName;
	private String registeredName;
    private String estateName;
    private String trustName;
	private String birthDate;
	private String suffixId;
	private String suffixDescription;
	private String taxpayerClassificationId;
	private String taxpayerClassificationDescription;
	private String taxpayerClassificationCode;
	private Address primaryAddress;
	private List<BusinessSummaryDetail> businessSummaryDetail;
	private List<ContactInformation> contactInformation;
	private String formattedPrimaryAddress;
	private String tinAndBranchCode;
	private String fullName;
	private String accountingPeriodId;
	private String accountingPeriodDescription;
	private String accountingYearStartMonthId;
	private String accountingYearStartMonthDescription;
	private String accountingEffectiveDate;
	private Date tinIssuanceDate;
	private List<TaxPayerTaxType> tpTaxTypes;
	private String taxpayerTypeId;
	private String taxpayerTypeDescription;
	private Boolean taxpayerTypeIsBusiness;
	private String officeId;
	private String officeDescription;
	private String documentLocatorNumber;
	private Date submissionDate;
	private String taxpayerRegistrationType;

	public TASBusinessSummaryDetails() {
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

	public String getRegisteredName() {
		return registeredName;
	}

	public void setRegisteredName(String registeredName) {
		this.registeredName = registeredName;
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

    public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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

	public Address getPrimaryAddress() {
		return primaryAddress;
	}

	public void setPrimaryAddress(Address primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	public List<BusinessSummaryDetail> getBusinessSummaryDetail() {
		return businessSummaryDetail;
	}

	public void setBusinessSummaryDetail(List<BusinessSummaryDetail> businessSummaryDetail) {
		this.businessSummaryDetail = businessSummaryDetail;
	}

	public List<ContactInformation> getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(List<ContactInformation> contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String getFormattedPrimaryAddress() {
		if (getPrimaryAddress() != null) {
			this.formattedPrimaryAddress = TaxpayerUtil.concatAddress(getPrimaryAddress());
		}

		return formattedPrimaryAddress;
	}

	public void setFormattedPrimaryAddress(String formattedPrimaryAddress) {
		this.formattedPrimaryAddress = formattedPrimaryAddress;
	}

	public String getTinAndBranchCode() {
		return tinAndBranchCode;
	}

	public void setTinAndBranchCode(String tinAndBranchCode) {
		this.tinAndBranchCode = tinAndBranchCode;
	}

	public String getFullName() {
		if (StringUtils.isNotBlank(getFirstName()) && StringUtils.isNotBlank(getMiddleName())
				&& StringUtils.isNotBlank(getLastName()) && StringUtils.isNotBlank(getSuffixId())) {

			this.fullName = getFirstName() + " " + getMiddleName() + " " + getLastName() + " " + getSuffixDescription();

		} else if (StringUtils.isNotBlank(getFirstName()) && StringUtils.isBlank(getMiddleName())
				&& StringUtils.isNotBlank(getLastName()) && StringUtils.isBlank(getSuffixId())) {

			this.fullName = getFirstName() + " " + getLastName();

		} else if (StringUtils.isNotBlank(getFirstName()) && StringUtils.isNotBlank(getMiddleName())
				&& StringUtils.isNotBlank(getLastName()) && StringUtils.isBlank(getSuffixId())) {

			this.fullName = getFirstName() + " " + getMiddleName() + " " + getLastName();

		} else if (StringUtils.isNotBlank(getFirstName()) && StringUtils.isBlank(getMiddleName())
				&& StringUtils.isNotBlank(getLastName()) && StringUtils.isNotBlank(getSuffixId())) {

			this.fullName = getFirstName() + " " + getLastName() + " " + getSuffixDescription();

		} else {
			this.fullName = "N/A";
		}
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getAccountingEffectiveDate() {
		return accountingEffectiveDate;
	}

	public void setAccountingEffectiveDate(String accountingEffectiveDate) {
		this.accountingEffectiveDate = accountingEffectiveDate;
	}

	public Date getTinIssuanceDate() {
		return tinIssuanceDate;
	}

	public void setTinIssuanceDate(Date tinIssuanceDate) {
		this.tinIssuanceDate = tinIssuanceDate;
	}

	public List<TaxPayerTaxType> getTpTaxTypes() {
		return tpTaxTypes;
	}

	public void setTpTaxTypes(List<TaxPayerTaxType> tpTaxTypes) {
		this.tpTaxTypes = tpTaxTypes;
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

	public Boolean getTaxpayerTypeIsBusiness() {
		return taxpayerTypeIsBusiness;
	}

	public void setTaxpayerTypeIsBusiness(Boolean taxpayerTypeIsBusiness) {
		this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
	}

	public String getDocumentLocatorNumber() {
		return documentLocatorNumber;
	}

	public void setDocumentLocatorNumber(String documentLocatorNumber) {
		this.documentLocatorNumber = documentLocatorNumber;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

    public String getTaxpayerRegistrationType() {
      return taxpayerRegistrationType;
    }
  
    public void setTaxpayerRegistrationType(String taxpayerRegistrationType) {
      this.taxpayerRegistrationType = taxpayerRegistrationType;
    }

}
