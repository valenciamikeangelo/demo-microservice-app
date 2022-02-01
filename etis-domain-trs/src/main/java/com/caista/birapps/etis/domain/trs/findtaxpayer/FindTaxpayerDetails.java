/*
 * Modified by: romerov
 * Last updated: Feb 25, 2019 3:13:21 PM
 */
package com.caista.birapps.etis.domain.trs.findtaxpayer;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import com.caista.birapps.etis.domain.trs.entity.Address;
import com.caista.birapps.etis.domain.trs.entity.BusinessSummaryDetail;
import com.caista.birapps.etis.domain.trs.entity.ContactInformation;
import com.caista.birapps.etis.domain.trs.entity.TaxPayerFormType;
import com.caista.birapps.etis.domain.trs.entity.TaxPayerTaxType;
import com.caista.birapps.etis.domain.trs.utils.TaxpayerUtil;

public class FindTaxpayerDetails {

  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private String tinStatus;
  private String businessStatus;
  private String firstName;
  private String lastName;
  private String middleName;
  private String trustName;
  private String estateName;
  private Date birthDate;
  private Date organizationDate;
  private Date incorporationDate;
  private Long officeId;
  private String officeDescription;
  private String officeCode;
  private String taxpayerTypeId;
  private String taxpayerTypeDescription;
  private Boolean taxpayerTypeIsBusiness;
  private String taxpayerClassificationId;
  private String taxpayerClassificationDescription;
  private String registrationType;
  private String registeredName;
  private List<ContactInformation> contact;
  private List<BusinessSummaryDetail> businessSummaryDetails;
  private List<TaxPayerTaxType> tpTaxTypes;
  private List<TaxPayerFormType> tpFormTypes;
  private Address primaryAddress;
  private Date tinIssuanceDate;

  private String commaSeparatedTradeName;
  private String formattedLegalName;
  private String formattedPrimaryAddress;

  private String suffixId;
  private String suffixDescription;

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

  public String getBusinessStatus() {
    return businessStatus;
  }

  public void setBusinessStatus(String businessStatus) {
    this.businessStatus = businessStatus;
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

  public List<BusinessSummaryDetail> getBusinessSummaryDetails() {
    return businessSummaryDetails;
  }

  public void setBusinessSummaryDetails(List<BusinessSummaryDetail> businessSummaryDetails) {
    this.businessSummaryDetails = businessSummaryDetails;
  }

  public Address getPrimaryAddress() {
    return primaryAddress;
  }

  public void setPrimaryAddress(Address primaryAddress) {
    this.primaryAddress = primaryAddress;
  }

  public String getCommaSeparatedTradeName() {
    if (!CollectionUtils.isEmpty(getBusinessSummaryDetails())) {
      this.commaSeparatedTradeName = TaxpayerUtil.listAggTradeName(getBusinessSummaryDetails());
    }
    return commaSeparatedTradeName;
  }

  public String getFormattedLegalName() {
    this.formattedLegalName = TaxpayerUtil.getTaxPayerName(getRegisteredName(), getEstateName(),
        getTrustName(), getFirstName(), getMiddleName(), getLastName(), getSuffixDescription());
    return formattedLegalName;
  }

  public String getFormattedPrimaryAddress() {
    if (!StringUtils.isNotBlank(formattedPrimaryAddress) && getPrimaryAddress() != null) {
      this.formattedPrimaryAddress = TaxpayerUtil.concatAddress(getPrimaryAddress());
    }
    return formattedPrimaryAddress;
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

  public void setCommaSeparatedTradeName(String commaSeparatedTradeName) {
    this.commaSeparatedTradeName = commaSeparatedTradeName;
  }

  public void setFormattedLegalName(String formattedLegalName) {
    this.formattedLegalName = formattedLegalName;
  }

  public void setFormattedPrimaryAddress(String formattedPrimaryAddress) {
    this.formattedPrimaryAddress = formattedPrimaryAddress;
  }

  public Boolean getTaxpayerTypeIsBusiness() {
    return taxpayerTypeIsBusiness;
  }

  public void setTaxpayerTypeIsBusiness(Boolean taxpayerTypeIsBusiness) {
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public List<ContactInformation> getContact() {
    return contact;
  }

  public void setContact(List<ContactInformation> contact) {
    this.contact = contact;
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
    return (this.suffixDescription == null)
        ? ""
        : this.suffixDescription;
  }

  public void setSuffixDescription(String suffixDescription) {
    this.suffixDescription = suffixDescription;
  }

  public String getRegistrationType() {
    return registrationType;
  }

  public void setRegistrationType(String registrationType) {
    this.registrationType = registrationType;
  }

}
