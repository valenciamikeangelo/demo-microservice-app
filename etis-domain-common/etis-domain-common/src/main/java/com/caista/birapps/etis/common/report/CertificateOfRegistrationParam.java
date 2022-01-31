/*
 * Modified by: fuentem
 * Last updated: Nov 8, 2018 5:14:03 PM
 */
package com.caista.birapps.etis.common.report;

import java.util.Date;
import java.util.List;

public class CertificateOfRegistrationParam {

  private String ocn;
  private String tin;
  private String branchCode;
  private String rdoCode;
  private String dln;

  private String taxpayerName;
  private Date tinIssuanceDate;
  private String registrationType;
  private String taxpayerTypeDesc;
  private String lotBlockPhaseNo;
  private String street;
  private String subdivision;
  private String townDistrict;
  private String building;
  private String unitRoomFloorNo;
  private String barangayDesc;
  private String municipalityDesc;
  private String provinceDesc;
  private String countryDesc;
  private String zipCode;
  private String rr;
  private String officeCode;
  private String rdo;
  private String signatory;
  private String position;
  private List<CertificateOfRegistrationTaxTypeParam> corTaxType;
  private List<CertificateOfRegistrationTradeNameParam> corTradeName;

  public CertificateOfRegistrationParam() {
    super();
  }

  public CertificateOfRegistrationParam(String ocn, String tin, String branchCode, String rdoCode,
      String dln) {
    super();
    this.ocn = ocn;
    this.tin = tin;
    this.branchCode = branchCode;
    this.rdoCode = rdoCode;
    this.dln = dln;
  }

  public CertificateOfRegistrationParam(String tin, String branchCode, String rdoCode, String dln) {
    super();
    this.tin = tin;
    this.branchCode = branchCode;
    this.rdoCode = rdoCode;
    this.dln = dln;
  }


  public String getOcn() {
    return ocn;
  }

  public void setOcn(String ocn) {
    this.ocn = ocn;
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

  public String getRdoCode() {
    return rdoCode;
  }

  public void setRdoCode(String rdoCode) {
    this.rdoCode = rdoCode;
  }

  public String getDln() {
    return dln;
  }

  public void setDln(String dln) {
    this.dln = dln;
  }

  public String getTaxpayerName() {
    return taxpayerName;
  }

  public void setTaxpayerName(String taxpayerName) {
    this.taxpayerName = taxpayerName;
  }

  public Date getTinIssuanceDate() {
    return tinIssuanceDate;
  }

  public void setTinIssuanceDate(Date tinIssuanceDate) {
    this.tinIssuanceDate = tinIssuanceDate;
  }

  public String getRegistrationType() {
    return registrationType;
  }

  public void setRegistrationType(String registrationType) {
    this.registrationType = registrationType;
  }

  public String getTaxpayerTypeDesc() {
    return taxpayerTypeDesc;
  }

  public void setTaxpayerTypeDesc(String taxpayerTypeDesc) {
    this.taxpayerTypeDesc = taxpayerTypeDesc;
  }

  public String getLotBlockPhaseNo() {
    return lotBlockPhaseNo;
  }

  public void setLotBlockPhaseNo(String lotBlockPhaseNo) {
    this.lotBlockPhaseNo = lotBlockPhaseNo;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getSubdivision() {
    return subdivision;
  }

  public void setSubdivision(String subdivision) {
    this.subdivision = subdivision;
  }

  public String getTownDistrict() {
    return townDistrict;
  }

  public void setTownDistrict(String townDistrict) {
    this.townDistrict = townDistrict;
  }

  public String getBuilding() {
    return building;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public String getUnitRoomFloorNo() {
    return unitRoomFloorNo;
  }

  public void setUnitRoomFloorNo(String unitRoomFloorNo) {
    this.unitRoomFloorNo = unitRoomFloorNo;
  }

  public String getBarangayDesc() {
    return barangayDesc;
  }

  public void setBarangayDesc(String barangayDesc) {
    this.barangayDesc = barangayDesc;
  }

  public String getMunicipalityDesc() {
    return municipalityDesc;
  }

  public void setMunicipalityDesc(String municipalityDesc) {
    this.municipalityDesc = municipalityDesc;
  }

  public String getProvinceDesc() {
    return provinceDesc;
  }

  public void setProvinceDesc(String provinceDesc) {
    this.provinceDesc = provinceDesc;
  }

  public String getCountryDesc() {
    return countryDesc;
  }

  public void setCountryDesc(String countryDesc) {
    this.countryDesc = countryDesc;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getRr() {
    return rr;
  }

  public void setRr(String rr) {
    this.rr = rr;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getRdo() {
    return rdo;
  }

  public void setRdo(String rdo) {
    this.rdo = rdo;
  }

  public String getSignatory() {
    return signatory;
  }

  public void setSignatory(String signatory) {
    this.signatory = signatory;
  }

  public List<CertificateOfRegistrationTaxTypeParam> getCorTaxType() {
    return corTaxType;
  }

  public void setCorTaxType(List<CertificateOfRegistrationTaxTypeParam> corTaxType) {
    this.corTaxType = corTaxType;
  }

  public List<CertificateOfRegistrationTradeNameParam> getCorTradeName() {
    return corTradeName;
  }

  public void setCorTradeName(List<CertificateOfRegistrationTradeNameParam> corTradeName) {
    this.corTradeName = corTradeName;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }
  
}
