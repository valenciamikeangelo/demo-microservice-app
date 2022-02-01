/*
  * Modified by: romeror
  * Last updated: Dec 1, 2018 7:43:17 PM
 */
package com.caista.birapps.etis.domain.trs.individual;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import com.caista.birapps.etis.domain.trs.entity.Address;
import com.caista.birapps.etis.domain.trs.utils.TaxpayerUtil;
import com.caista.birapps.etis.domain.trs.utils.enums.TinStatusEnum;

public class EstateTrustPotentialDuplicate {

  private Long taxpayerId;
  private String estateName;
  private String trustName;
  private String tin;
  private Date birthDate;
  private Long officeId;
  private String officeDescription;
  private Date organizationDate;
  private String branchCode;
  private Address primaryAddress;
  private String taxpayerTypeId;
  private String taxpayerTypeDescription;
  private String taxpayerClassificationId;
  private String taxpayerClassificationDescription;
  private TinStatusEnum tinStatus;
  private String formattedPrimaryAddress;
  public EstateTrustPotentialDuplicate() {
    super();
  }

  public Long getTaxpayerId() {
    return taxpayerId;
  }

  public void setTaxpayerId(Long taxpayerId) {
    this.taxpayerId = taxpayerId;
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

  public String getTin() {
    return tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }
  
  public Date getOrganizationDate() {
	return organizationDate;
  }

  public void setOrganizationDate(Date organizationDate) {
	this.organizationDate = organizationDate;
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

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
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

  public TinStatusEnum getTinStatus() {
    return tinStatus;
  }

  public void setTinStatus(TinStatusEnum tinStatus) {
    this.tinStatus = tinStatus;
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


}
