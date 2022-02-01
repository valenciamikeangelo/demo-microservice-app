/*
 * Modified by: romerov
 * Last updated: Jan 21, 2019 11:52:33 AM
 */
package com.caista.birapps.etis.domain.trs.individual;

import org.apache.commons.lang3.StringUtils;
import com.caista.birapps.etis.domain.trs.entity.RelationshipDetail;
import com.caista.birapps.etis.domain.trs.utils.enums.ReferenceCodesEnum;
import com.caista.birapps.etis.domain.trs.utils.enums.TaxpayerTypeEnum;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class SpouseEmployer {

  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private String firstName;
  private String lastName;
  private String middleName;
  private String trustName;
  private String estateName;
  private String registeredName;
  private String suffixDescription;
  private String formattedLegalName;
  private String taxpayerTypeId;
  private Boolean taxpayerTypeIsBusiness;
  private String taxpayerClassificationId;
  private RelationshipDetail relationshipDetails;

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

  public RelationshipDetail getRelationshipDetails() {
    return relationshipDetails;
  }

  public void setRelationshipDetails(RelationshipDetail relationshipDetails) {
    this.relationshipDetails = relationshipDetails;
  }

  public Boolean getTaxpayerTypeIsBusiness() {
    return taxpayerTypeIsBusiness;
  }

  public void setTaxpayerTypeIsBusiness(Boolean taxpayerTypeIsBusiness) {
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
  }

  public String getTaxpayerTypeId() {
    return taxpayerTypeId;
  }

  public void setTaxpayerTypeId(String taxpayerTypeId) {
    this.taxpayerTypeId = taxpayerTypeId;
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

  public String getRegisteredName() {
    return registeredName;
  }

  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
  }

  public String getSuffixDescription() {
    return (this.suffixDescription == null)
        ? ""
        : this.suffixDescription;
  }

  public void setSuffixDescription(String suffixDescription) {
    this.suffixDescription = suffixDescription;
  }

  public String getFormattedLegalName() {
    if (taxpayerTypeId != null && StringUtils.equals(taxpayerClassificationId,
        ReferenceCodesEnum.TAXPAYERCLASSIFICATION_INDIVIDUAL.getId())) {
      if (StringUtils.equals(getTaxpayerTypeId(), TaxpayerTypeEnum.TRUSTFC.toString())
          || StringUtils.equals(getTaxpayerTypeId(), TaxpayerTypeEnum.TRUSTFN.toString())) {
        this.formattedLegalName = getTrustName();
      } else if (StringUtils.equals(getTaxpayerTypeId(), TaxpayerTypeEnum.ESTAFC.toString())
          || StringUtils.equals(getTaxpayerTypeId(), TaxpayerTypeEnum.ESTAFN.toString())) {
        this.formattedLegalName = getEstateName();
      } else {
        this.formattedLegalName = getFirstName() + " " + getMiddleName() + " " + getLastName() + " "
            + getSuffixDescription();
      }
    } else {
      this.formattedLegalName = getRegisteredName();
    }
    return formattedLegalName;
  }

  public void setFormattedLegalName(String formattedLegalName) {
    this.formattedLegalName = formattedLegalName;
  }

  /**
   * @return the taxpayerClassificationId
   */
  public String getTaxpayerClassificationId() {
    return taxpayerClassificationId;
  }

  /**
   * @param taxpayerClassificationId the taxpayerClassificationId to set
   */
  public void setTaxpayerClassificationId(String taxpayerClassificationId) {
    this.taxpayerClassificationId = taxpayerClassificationId;
  }



}
