/*
  * Modified by: bongalr
  * Last updated: Feb 12, 2020 5:39:05 PM
  */
package com.caista.birapps.etis.domain.trs;

import java.util.*;
import org.apache.commons.lang3.StringUtils;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.caista.birapps.etis.domain.trs.utils.TaxpayerUtil;

public class TaxpayerDetails {

  private Long id;

  private String taxpayerTypeDescription;

  private Long officeId;

  private String officeDescription;

  private String officeCode;

  private Boolean officeLargeTaxpayerOfficeFlag;

  private Date updatedDate;

  private String firstName;

  private String middleName;

  private String lastName;

  private String suffixDescription;

  private String registeredName;

  private String trustName;

  private String estateName;

  private Address primaryAddress;

  private String preferredContactNumber;

  private String preferredEmail;

  private Date tinIssuanceDate;

  private Boolean taxpayerTypeIsBusiness;

  private List<ContactInformation> contactInformation;
  
  //Fix for Bug #39651
  private Boolean isTamp; 

  public TaxpayerDetails() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTaxpayerTypeDescription() {
    return taxpayerTypeDescription;
  }

  public void setTaxpayerTypeDescription(String taxpayerTypeDescription) {
    this.taxpayerTypeDescription = taxpayerTypeDescription;
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

  public Boolean getOfficeLargeTaxpayerOfficeFlag() {
    return officeLargeTaxpayerOfficeFlag;
  }

  public void setOfficeLargeTaxpayerOfficeFlag(Boolean officeLargeTaxpayerOfficeFlag) {
    this.officeLargeTaxpayerOfficeFlag = officeLargeTaxpayerOfficeFlag;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
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


  public void setSuffixDescription(String suffixDescription) {
    this.suffixDescription = suffixDescription;
  }

  public String getRegisteredName() {
    return registeredName;
  }

  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
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

  public Boolean getIsTamp() {
    return isTamp;
  }

  public void setIsTamp(Boolean isTamp) {
    this.isTamp = isTamp;
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

  public String getFormattedPrimaryAddress() {
    String formattedPrimaryAddress;
    if (getPrimaryAddress() != null) {
      formattedPrimaryAddress = TaxpayerUtil.concatAddress(getPrimaryAddress());
    } else {
      formattedPrimaryAddress = "N/A";
    }
    return formattedPrimaryAddress;
  }

  public String getPrinterName() {
    String printerName = "";
    if (StringUtils.isNotBlank(getRegisteredName())) {
      printerName = getRegisteredName();
    } else if (StringUtils.isNotBlank(getTrustName())) {
      printerName = getTrustName();
    } else if (StringUtils.isNotBlank(getEstateName())) {
      printerName = getEstateName();
    } else {
      printerName = getFirstName() + " " + (StringUtils.isNotBlank(getMiddleName())
          ? getMiddleName() + " "
          : "") + getLastName()
          + (StringUtils.isNotBlank(getSuffixDescription())
              ? " " + getSuffixDescription()
              : "");
    }
    return printerName;
  }

  public String getPreferredEmail() {
    preferredEmail = "";
    if (!getContactInformation().isEmpty()) {
      getContactInformation().forEach(contactInfo -> {
        if (contactInfo.getContactTypeId().equals("EMAIL") && contactInfo.getIsPreferred()) {
          preferredEmail = contactInfo.getContactDetails();
        }
      });
    }
    return preferredEmail;
  }

  public String getPreferredContactNumber() {
    preferredContactNumber = "";
    if (!getContactInformation().isEmpty()) {
      getContactInformation().forEach(contactInfo -> {
        if (!contactInfo.getContactTypeId().equals("EMAIL") && contactInfo.getIsPreferred()) {
          preferredContactNumber = contactInfo.getContactDetails();
        }
      });
    }
    return preferredContactNumber;
  }

  @Override
  public String toString() {
    return "TaxpayerDetails [id=" + id + ", taxpayerTypeDescription=" + taxpayerTypeDescription
        + ", officeId=" + officeId + ", officeDescription=" + officeDescription + ", officeCode="
        + officeCode + ", officeLargeTaxpayerOfficeFlag=" + officeLargeTaxpayerOfficeFlag
        + ", updatedDate=" + updatedDate + ", firstName=" + firstName + ", middleName=" + middleName
        + ", lastName=" + lastName + ", suffixDescription=" + suffixDescription
        + ", registeredName=" + registeredName + ", trustName=" + trustName + ", estateName="
        + estateName + ", primaryAddress=" + primaryAddress + ", preferredContactNumber="
        + preferredContactNumber + ", preferredEmail=" + preferredEmail + ", tinIssuanceDate="
        + tinIssuanceDate + "]";
  }

}
