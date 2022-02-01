/*
 * Last modified by: delmund
 * Last updated date: Oct 11, 2018 3:49:35 PM
 */
package com.caista.birapps.etis.domain.trs.branch;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class BranchFindPotentialDuplicateParams {

  private String tin;
  private String tradeName;
  private String provinceId;
  private String provinceDescription;
  private String municipalityId;
  private String municipalityDescription;
  private String barangayId;
  private String barangayDescription;
  public String getTin() {
    return tin;
  }
  public void setTin(String tin) {
    this.tin = tin;
  }
  public String getTradeName() {
    return tradeName;
  }
  public void setTradeName(String tradeName) {
    this.tradeName = tradeName;
  }

  public String getProvinceId() {
    return provinceId;
  }

  public void setProvinceId(String provinceId) {
    this.provinceId = provinceId;
  }

  public String getProvinceDescription() {
    return provinceDescription;
  }

  public void setProvinceDescription(String provinceDescription) {
    this.provinceDescription = provinceDescription;
  }

  public String getMunicipalityId() {
    return municipalityId;
  }

  public void setMunicipalityId(String municipalityId) {
    this.municipalityId = municipalityId;
  }

  public String getMunicipalityDescription() {
    return municipalityDescription;
  }

  public void setMunicipalityDescription(String municipalityDescription) {
    this.municipalityDescription = municipalityDescription;
  }

  public String getBarangayId() {
    return barangayId;
  }

  public void setBarangayId(String barangayId) {
    this.barangayId = barangayId;
  }

  public String getBarangayDescription() {
    return barangayDescription;
  }

  public void setBarangayDescription(String barangayDescription) {
    this.barangayDescription = barangayDescription;
  }

  @Override
  public String toString() {
    return "BranchFindPotentialDuplicateParams [tin=" + tin + ", tradeName=" + tradeName
        + ", provinceId=" + provinceId + ", provinceDescription=" + provinceDescription
        + ", municipalityId=" + municipalityId + ", municipalityDescription="
        + municipalityDescription + ", barangayId=" + barangayId + ", barangayDescription="
        + barangayDescription + "]";
  }

}
