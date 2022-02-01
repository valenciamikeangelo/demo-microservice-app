/*
  * Modified by: decinam
  * Last updated: Jan 10, 2019 7:25:54 PM
  */
package com.caista.birapps.etis.domain.trs;

import java.util.*;
import org.apache.commons.lang3.StringUtils;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.caista.birapps.etis.domain.trs.utils.TaxpayerUtil;
import com.caista.birapps.etis.domain.trs.utils.enums.*;


public class FindRelationshipDetails {

  public FindRelationshipDetails() {
    super();
    // TODO Auto-generated constructor stub
  }

  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private String firstName;
  private String lastName;
  private String middleName;
  private String registeredName;
  private Address primaryAddress;
  private String taxpayerTypeId;
  private String taxpayerTypeDescription;
  private String taxpayerClassificationId;
  private String taxpayerClassificationDescription;
  private String taxpayerClassificationCode;
  private List<ContactInformation> contact;
  private Boolean taxpayerTypeIsBusiness;
  private String estateName;
  private String trustName;
  private String suffixId;
  private String suffixDescription;
  private String formattedLegalName;
  private String formattedPrimaryAddress;
  private Date relationshipStartDate;
  private String relationshipTypeId;
  private String relationshipTypeDescription;
  private Date relationshipEndDate;
  private Date cancellationDate;
  private String status;

  public String getSuffixDescription() {
    return suffixDescription;
  }

  public void setSuffixDescription(String suffixDescription) {
    this.suffixDescription = suffixDescription;
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

  public Address getPrimaryAddress() {
    return primaryAddress;
  }

  public void setPrimaryAddress(Address primaryAddress) {
    this.primaryAddress = primaryAddress;
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

  public String getTaxpayerClassificationCode() {
    return taxpayerClassificationCode;
  }

  public void setTaxpayerClassificationCode(String taxpayerClassificationCode) {
    this.taxpayerClassificationCode = taxpayerClassificationCode;
  }

  public List<ContactInformation> getContact() {
    return contact;
  }

  public void setContact(List<ContactInformation> contact) {
    this.contact = contact;
  }

  public Boolean getTaxpayerTypeIsBusiness() {
    return taxpayerTypeIsBusiness;
  }

  public void setTaxpayerTypeIsBusiness(Boolean taxpayerTypeIsBusiness) {
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
  }

  public String getRelationshipTypeId() {
    return relationshipTypeId;
  }

  public void setRelationshipTypeId(String relationshipTypeId) {
    this.relationshipTypeId = relationshipTypeId;
  }

  public String getRelationshipTypeDescription() {
    return relationshipTypeDescription;
  }

  public void setRelationshipTypeDescription(String relationshipTypeDescription) {
    this.relationshipTypeDescription = relationshipTypeDescription;
  }

  public String getSuffixId() {
    return suffixId;
  }

  public void setSuffixId(String suffixId) {
    this.suffixId = suffixId;
  }

  public String getFormattedLegalName() {
    if (taxpayerTypeId != null && StringUtils.equals(taxpayerClassificationCode,
        ReferenceCodesEnum.TAXPAYERCLASSIFICATION_NON_INDIVIDUAL.getId())) {
      this.formattedLegalName = getRegisteredName();
    } else {
      this.formattedLegalName = TaxpayerUtil.getTaxPayerName(this.registeredName, this.estateName,
          this.trustName, this.firstName, this.middleName, this.lastName, this.suffixDescription);
    }
    return formattedLegalName;
  }

  public String getFormattedPrimaryAddress() {
    if (!StringUtils.isNotBlank(formattedPrimaryAddress) && getPrimaryAddress() != null) {
      this.formattedPrimaryAddress = TaxpayerUtil.concatAddress(getPrimaryAddress());
    }
    return formattedPrimaryAddress;
  }

  public Date getRelationshipStartDate() {
    return relationshipStartDate;
  }

  public void setRelationshipStartDate(Date relationshipStartDate) {
    this.relationshipStartDate = relationshipStartDate;
  }

  public Date getRelationshipEndDate() {
    return relationshipEndDate;
  }

  public void setRelationshipEndDate(Date relationshipEndDate) {
    this.relationshipEndDate = relationshipEndDate;
  }

  public Date getCancellationDate() {
    return cancellationDate;
  }

  public void setCancellationDate(Date cancellationDate) {
    this.cancellationDate = cancellationDate;
  }

  public String getStatus() {
    if (null == getCancellationDate() && null == getRelationshipStartDate()
        && null == getRelationshipEndDate()) {
      this.status = StringUtils.EMPTY;
    } else if (getCancellationDate() != null) {
      this.status = String.valueOf(RelationshipStatusEnum.CANCELLED);
    } else if (getRelationshipEndDate() == null) {
      if (getRelationshipTypeId().equalsIgnoreCase("Employer")) {
        this.status = String.valueOf(RelationshipStatusEnum.CURRENT);
      } else {
        this.status = String.valueOf(RelationshipStatusEnum.ACTIVE);
      }
    } else if ((getRelationshipStartDate() != null)
        && (new Date().getTime() <= getRelationshipEndDate().getTime()
            && new Date().getTime() >= getRelationshipStartDate().getTime())) {
      this.status = String.valueOf(RelationshipStatusEnum.CURRENT);
    } else if (new Date().getTime() > getRelationshipEndDate().getTime()) {
      this.status = String.valueOf(RelationshipStatusEnum.INACTIVE);
    }
    return status;
  }

}
