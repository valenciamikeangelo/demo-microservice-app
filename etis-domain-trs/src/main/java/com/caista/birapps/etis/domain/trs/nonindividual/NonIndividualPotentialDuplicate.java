/*
  * Modified by: bongalr
  * Last updated: Oct 25, 2018 10:29:08 AM
  */
package com.caista.birapps.etis.domain.trs.nonindividual;

import java.util.*;
import com.caista.birapps.etis.domain.trs.entity.Address;

public class NonIndividualPotentialDuplicate {

  private Long taxpayerId;
  private String tin;
  private String tinStatus;
  private String businessStatus;
  private String branchCode;
  private String registeredName;
  private Date incorporationDate;
  private List<Address> addresses;
  private Long officeId;
  private String officeDescription;
  private String formattedTradeName;
  private String primaryAddress;
  private String alternateAddress;
  private String dataSourceCreated;
  private String dataSourceUpdated;
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
  public Date getIncorporationDate() {
    return incorporationDate;
  }
  public void setIncorporationDate(Date incorporationDate) {
    this.incorporationDate = incorporationDate;
  }
  public List<Address> getAddresses() {
    return addresses;
  }
  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
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
  public String getFormattedTradeName() {
    return formattedTradeName;
  }
  public void setFormattedTradeName(String formattedTradeName) {
    this.formattedTradeName = formattedTradeName;
  }
  public String getPrimaryAddress() {
    return primaryAddress;
  }
  public void setPrimaryAddress(String primaryAddress) {
    this.primaryAddress = primaryAddress;
  }
  public String getAlternateAddress() {
    return alternateAddress;
  }
  public void setAlternateAddress(String alternateAddress) {
    this.alternateAddress = alternateAddress;
  }
  public String getDataSourceCreated() {
    return dataSourceCreated;
  }
  public void setDataSourceCreated(String dataSourceCreated) {
    this.dataSourceCreated = dataSourceCreated;
  }
  public String getDataSourceUpdated() {
    return dataSourceUpdated;
  }
  public void setDataSourceUpdated(String dataSourceUpdated) {
    this.dataSourceUpdated = dataSourceUpdated;
  }


}
