/*
  * Modified by: decinam
  * Last updated: Feb 25, 2019 2:07:17 PM
  */
package com.caista.birapps.etis.domain.trs.reactivate;

import java.util.*;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.caista.birapps.etis.domain.trs.utils.enums.TinStatusEnum;

/**
 * The Class ReactivateTaxDetails.
 */
public class ReactivateTaxDetails {

  /** The taxpayer id. */
  private Long taxpayerId;

  /** The tin. */
  private String tin;

  /** The branch code. */
  private String branchCode;

  private Long officeId;

  private String officeDescription;

  private String officeCode;

  private String taxpayerClassificationId;

  private String taxpayerClassificationDescription;

  /** The contact information. */
  private List<ContactInformation> contactInformation;

  /** The registered name. */
  private String registeredName;

  /** The first name. */
  private String estateName;

  /** The first name. */
  private String trustName;

  /** The first name. */
  private String firstName;

  /** The middle name. */
  private String middleName;

  /** The last name. */
  private String lastName;

  /** The tp tax types. */
  private List<TaxPayerTaxType> tpTaxTypes;

  /** The registration date. */
  private Date submissionDate;

  /** The tin status. */
  private TinStatusEnum tinStatus;

	private Date tinIssuanceDate;

  private String taxpayerTypeId;

  private String taxpayerTypeDescription;

  private String suffixId;

  private String suffixDescription;
  /**
   * Instantiates a new reactivate tax details.
   */

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

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
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

	public Date getTinIssuanceDate() {
		return tinIssuanceDate;
	}

	public void setTinIssuanceDate(Date tinIssuanceDate) {
		this.tinIssuanceDate = tinIssuanceDate;
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

  @Override
  public String toString() {
    return "ReactivateTaxDetails [taxpayerId=" + taxpayerId + ", tin=" + tin + ", branchCode="
        + branchCode + ", officeId=" + officeId + ", officeDescription=" + officeDescription
        + ", officeCode=" + officeCode + ", taxpayerClassificationId=" + taxpayerClassificationId
        + ", taxpayerClassificationDescription=" + taxpayerClassificationDescription
        + ", contactInformation=" + contactInformation + ", registeredName=" + registeredName
        + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
        + ", tpTaxTypes=" + tpTaxTypes + ", submissionDate=" + submissionDate + ", tinStatus="
        + tinStatus + ", taxpayerTypeId=" + taxpayerTypeId + ", taxpayerTypeDescription="
        + taxpayerTypeDescription + ", suffixId=" + suffixId + ", suffixDescription="
        + suffixDescription + "]";
  }

}
