/*
  * Modified by: bacosal
  * Last updated: Oct 11, 2018 5:30:59 PM
  */
package com.caista.birapps.etis.domain.trs.branch;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class BranchPotentialDuplicateParam {

  private String tin;
  private String provinceId;
  private String provinceDescription;
  private String provinceCode;
  private String cityMunicipalityId;
  private String cityMunicipalityDescription;
  private String cityMunicipalityCode;
  private String barangayId;
  private String barangayDescription;
  private String barangayCode;


  public String getTin() {
    return tin;
  }
  public void setTin(String tin) {
    this.tin = tin;
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

  public String getCityMunicipalityId() {
    return cityMunicipalityId;
  }

  public void setCityMunicipalityId(String cityMunicipalityId) {
    this.cityMunicipalityId = cityMunicipalityId;
  }

  public String getCityMunicipalityDescription() {
    return cityMunicipalityDescription;
  }

  public void setCityMunicipalityDescription(String cityMunicipalityDescription) {
    this.cityMunicipalityDescription = cityMunicipalityDescription;
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

  public String getProvinceCode() {
    return provinceCode;
  }

  public void setProvinceCode(String provinceCode) {
    this.provinceCode = provinceCode;
  }

  public String getCityMunicipalityCode() {
    return cityMunicipalityCode;
  }

  public void setCityMunicipalityCode(String cityMunicipalityCode) {
    this.cityMunicipalityCode = cityMunicipalityCode;
  }

  public String getBarangayCode() {
    return barangayCode;
  }

  public void setBarangayCode(String barangayCode) {
    this.barangayCode = barangayCode;
  }

}
