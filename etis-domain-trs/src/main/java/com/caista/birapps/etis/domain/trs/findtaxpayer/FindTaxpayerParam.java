/*
 * Modified by: romerov
 * Last updated: Apr 26, 2019 4:12:03 PM
 */
package com.caista.birapps.etis.domain.trs.findtaxpayer;

import java.util.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class FindTaxpayerParam {

  private String taxPayerClassificationTypeId;
  private String taxPayerClassificationTypeCode;
  private String taxPayerClassificationTypeDescription;
  private String tin;
  private String branchCode;
  private Long officeId;
  private String officeCode;
  private List<String> subOffices;

  private String taxPayerTypeId;
  private String lastName;
  private String firstName;
  private String middleName;
  private String mothersMaidenName;
  private String businessTradeName;
  private Date dateOfBirth;
  private Date organizationDate;
  private Date incorporationDate;
  private String registeredName;
  private String tinStatus;
  private String estateName;
  private String trustName;
  private Date birthDate;

  private String registeredFilerName;
  private String rdoRegTin;

  private Long id;
  private String ocn;

  private Date dateOfBirthOrganizationDate;
  private String lastNameTrustName;

  private boolean includePreGen;
  
  private boolean taxpayerTypeIsBusiness;

  public String getTaxPayerClassificationTypeId() {
    return taxPayerClassificationTypeId;
  }

  public void setTaxPayerClassificationTypeId(String taxPayerClassificationTypeId) {
    this.taxPayerClassificationTypeId = taxPayerClassificationTypeId;
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

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public List<String> getSubOffices() {
    return subOffices;
  }

  public void setSubOffices(List<String> subOffices) {
    this.subOffices = subOffices;
  }

  public String getTaxPayerTypeId() {
    return taxPayerTypeId;
  }

  public void setTaxPayerTypeId(String taxPayerTypeId) {
    this.taxPayerTypeId = taxPayerTypeId;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
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
  public String getMothersMaidenName() {
    return mothersMaidenName;
  }
  public void setMothersMaidenName(String mothersMaidenName) {
    this.mothersMaidenName = mothersMaidenName;
  }
  public String getBusinessTradeName() {
    return businessTradeName;
  }
  public void setBusinessTradeName(String businessTradeName) {
    this.businessTradeName = businessTradeName;
  }
  public Date getDateOfBirth() {
    return dateOfBirth;
  }
  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
  public String getRegisteredName() {
    return registeredName;
  }
  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
  }
  public String getTinStatus() {
    return tinStatus;
  }
  public void setTinStatus(String tinStatus) {
    this.tinStatus = tinStatus;
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

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getRegisteredFilerName() {
    return registeredFilerName;
  }
  public void setRegisteredFilerName(String registeredFilerName) {
    this.registeredFilerName = registeredFilerName;
  }
  public String getRdoRegTin() {
    return rdoRegTin;
  }
  public void setRdoRegTin(String rdoRegTin) {
    this.rdoRegTin = rdoRegTin;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOcn() {
    return ocn;
  }

  public void setOcn(String ocn) {
    this.ocn = ocn;
  }
  public Date getDateOfBirthOrganizationDate() {
    return dateOfBirthOrganizationDate;
  }
  public void setDateOfBirthOrganizationDate(Date dateOfBirthOrganizationDate) {
    this.dateOfBirthOrganizationDate = dateOfBirthOrganizationDate;
  }
  public String getLastNameTrustName() {
    return lastNameTrustName;
  }
  public void setLastNameTrustName(String lastNameTrustName) {
    this.lastNameTrustName = lastNameTrustName;
  }

  public String getTaxPayerClassificationTypeCode() {
    return taxPayerClassificationTypeCode;
  }

  public void setTaxPayerClassificationTypeCode(String taxPayerClassificationTypeCode) {
    this.taxPayerClassificationTypeCode = taxPayerClassificationTypeCode;
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

  public boolean isIncludePreGen() {
    return includePreGen;
  }

  public void setIncludePreGen(boolean includePreGen) {
    this.includePreGen = includePreGen;
  }

  public String getTaxPayerClassificationTypeDescription() {
    return taxPayerClassificationTypeDescription;
  }

  public void setTaxPayerClassificationTypeDescription(
      String taxPayerClassificationTypeDescription) {
    this.taxPayerClassificationTypeDescription = taxPayerClassificationTypeDescription;
  }

  public boolean isTaxpayerTypeIsBusiness() {
    return taxpayerTypeIsBusiness;
  }
	
  public void setTaxpayerTypeIsBusiness(boolean taxpayerTypeIsBusiness) {
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
  }

}
