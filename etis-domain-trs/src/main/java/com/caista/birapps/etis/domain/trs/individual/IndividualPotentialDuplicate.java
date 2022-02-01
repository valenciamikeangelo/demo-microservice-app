/*
  * Modified by: romeror
  * Last updated: Nov 29, 2018 12:00:36 PM
 */
package com.caista.birapps.etis.domain.trs.individual;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import com.caista.birapps.etis.domain.trs.entity.Address;
import com.caista.birapps.etis.domain.trs.utils.TaxpayerUtil;
import com.caista.birapps.etis.domain.trs.utils.enums.TinStatusEnum;

public class IndividualPotentialDuplicate {

  private String taxpayerTypeId;
  private String taxpayerTypeDescription;
	private Boolean taxpayerTypeIsBusiness;
  private String firstName;
  private String middleName;
  private String lastName;
  private String estateName;
  private String trustName;
  private Date birthDate;
  private TinStatusEnum tinStatus;
  private String placeOfBirth;
  private String genderId;
  private String genderDescription;
  private String motherMaidenName;
  private String tin;
  private String branchCode;
  private Long taxpayerId;
  private Address addresses;
  private Address primaryAddress;
  private String alternateAddress;
  private String officeId;
  private String officeDescription;

  private String formattedPrimaryAddress;

  public IndividualPotentialDuplicate() {
    super();
    // TODO Auto-generated constructor stub
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

	public Boolean getTaxpayerTypeIsBusiness() {
		return taxpayerTypeIsBusiness;
	}

	public void setTaxpayerTypeIsBusiness(Boolean taxpayerTypeIsBusiness) {
		this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
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

  public TinStatusEnum getTinStatus() {
    return tinStatus;
  }

  public void setTinStatus(TinStatusEnum tinStatus) {
    this.tinStatus = tinStatus;
  }

  public String getPlaceOfBirth() {
    return placeOfBirth;
  }

  public void setPlaceOfBirth(String placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
  }

  public String getGenderId() {
    return genderId;
  }

  public void setGenderId(String genderId) {
    this.genderId = genderId;
  }

  public String getGenderDescription() {
    return genderDescription;
  }

  public void setGenderDescription(String genderDescription) {
    this.genderDescription = genderDescription;
  }

  public String getMotherMaidenName() {
    return motherMaidenName;
  }

  public void setMotherMaidenName(String motherMaidenName) {
    this.motherMaidenName = motherMaidenName;
  }

  public String getTin() {
    return tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public Long getTaxpayerId() {
    return taxpayerId;
  }

  public void setTaxpayerId(Long taxpayerId) {
    this.taxpayerId = taxpayerId;
  }

  public Address getAddresses() {
    return addresses;
  }

  public void setAddresses(Address addresses) {
    this.addresses = addresses;
  }

  public String getAlternateAddress() {
    return alternateAddress;
  }

  public void setAlternateAddress(String alternateAddress) {
    this.alternateAddress = alternateAddress;
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

  public String getFormattedPrimaryAddress() {
    if (!StringUtils.isNotBlank(formattedPrimaryAddress) && getPrimaryAddress() != null) {
      this.formattedPrimaryAddress = TaxpayerUtil.concatAddress(getPrimaryAddress());
    }
    return formattedPrimaryAddress;
  }

  public void setFormattedPrimaryAddress(String formattedPrimaryAddress) {
    this.formattedPrimaryAddress = formattedPrimaryAddress;
  }

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public void setPrimaryAddress(Address primaryAddress) {
    this.primaryAddress = primaryAddress;
  }

  public Address getPrimaryAddress() {
    return primaryAddress;
  }

}
