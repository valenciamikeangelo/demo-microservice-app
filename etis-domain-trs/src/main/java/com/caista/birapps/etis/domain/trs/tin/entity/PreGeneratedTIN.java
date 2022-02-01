/*
 * Modified by: fuentem
 * Last updated: Nov 18, 2018 6:11:28 PM
 */
package com.caista.birapps.etis.domain.trs.tin.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class RegTIN.
 */
@Entity
@Table(name = "PRE_GENERATED_TIN")
public class PreGeneratedTIN {

  /** The tin. */
  @Id
  @Column(name = "TIN", columnDefinition = "VARCHAR2(9 BYTE)")
  private String tin;

  /** The registered filer name. */
  @Column(name = "FILER_REGISTERED_NAME", columnDefinition = "VARCHAR2(150 BYTE)")
  private String registeredFilerName;

  /** The rdo code. */
  @Column(name = "RDO_CODE", columnDefinition = "VARCHAR2(3 BYTE)")
  private String rdoCode;

  /** The tp classification. */
  @Column(name = "TAXPAYER_CLASSIFICATION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String tpClassification;

  /** The birth incorp date. */
  @Column(name = "BIRTH_INCORP_DATE", columnDefinition = "DATE")
  private Date birthIncorpDate;

  /** The town/district. */
  @Column(name = "town_district", columnDefinition = "VARCHAR2(100 BYTE)")
  private String townDistrict;

  /** The subdivision/village. */
  @Column(name = "subdivision_village", columnDefinition = "VARCHAR2(100 BYTE)")
  private String subdivisionVillage;

  /** The street. */
  @Column(name = "street", columnDefinition = "VARCHAR2(100 BYTE)")
  private String street;

  /** The lot/block/phase no. */
  @Column(name = "lot_block_phase_no", columnDefinition = "VARCHAR2(100 BYTE)")
  private String lotBlockPhaseNo;

  /** The building/tower. */
  @Column(name = "building_tower", columnDefinition = "VARCHAR2(100 BYTE)")
  private String buildingTower;

  /** The unit room/floor no. */
  @Column(name = "unit_room_floor_no", columnDefinition = "VARCHAR2(100 BYTE)")
  private String unitRoomFloorNo;

  @Column(name = "PROVINCE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String provinceId;

  @Column(name = "PROVINCE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String provinceDescription;

  @Column(name = "BARANGAY_ID", columnDefinition = "VARCHAR2(100 BYTE)")
  private String barangayId;

  @Column(name = "BARANGAY_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String barangayDescription;

  @Column(name = "ZIP_CODE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String zipCodeId;

  @Column(name = "ZIP_CODE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String zipCodeDescription;

  @Column(name = "CITY_MUNICIPALITY_ID", columnDefinition = "VARCHAR2(100 BYTE)")
  private String municipalityId;

  @Column(name = "CITY_MUNICIPALITY_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String municipalityDescription;

  @Column(name = "COUNTRY_ID", columnDefinition = "VARCHAR2(100 BYTE)")
  private String countryid;

  @Column(name = "COUNTRY_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String countryDescription;

  @Column(name = "ADDRESS_TYPE_ID", columnDefinition = "VARCHAR2(100 BYTE)")
  private String addressTypeId;

  @Column(name = "ADDRESS_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String addressTypeDescription;

  @Column(name = "ADDRESS_TYPE_CODE", columnDefinition = "VARCHAR2(50 BYTE)")
  private String addressTypeCode;

  /** The business code. */
  @Column(name = "BUSINESS_CODE", columnDefinition = "VARCHAR2(1 BYTE)")
  private String businessCode;

  /** The i TS flag. */
  @Column(name = "ITS_FLAG_YN", columnDefinition = "VARCHAR2(1 BYTE)")
  private String iTSFlag;

  /** The status flag. */
  @Column(name = "STATUS_FLAG", columnDefinition = "VARCHAR2(1 BYTE)")
  private String statusFlag;

  /** The db source. */
  @Column(name = "DB_SOURCE", columnDefinition = "VARCHAR2(4 BYTE)")
  private String dbSource;

  /** The name soundex. */
  @Column(name = "NAME_SOUNDEX", columnDefinition = "VARCHAR2(5 BYTE)")
  private String nameSoundex;

  /** The created by. */
  @Column(name = "CREATED_BY", nullable = false, columnDefinition = "VARCHAR2(50 BYTE)")
  private String createdBy;

  /** The created by. */
  @Column(name = "UPLOADED_BY", nullable = false, columnDefinition = "VARCHAR2(50 BYTE)")
  private String uploadedBy;

  /** The birth incorp date. */
  @Column(name = "DATE_MIGRATED")
  private Date dateMigrated;

  public String getTin() {
    return tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public String getRegisteredFilerName() {
    return registeredFilerName;
  }

  public void setRegisteredFilerName(String registeredFilerName) {
    this.registeredFilerName = registeredFilerName;
  }

  public String getRdoCode() {
    return rdoCode;
  }

  public void setRdoCode(String rdoCode) {
    this.rdoCode = rdoCode;
  }

  public String getTpClassification() {
    return tpClassification;
  }

  public void setTpClassification(String tpClassification) {
    this.tpClassification = tpClassification;
  }

  public Date getBirthIncorpDate() {
    return birthIncorpDate;
  }

  public void setBirthIncorpDate(Date birthIncorpDate) {
    this.birthIncorpDate = birthIncorpDate;
  }

  public String getTownDistrict() {
    return townDistrict;
  }

  public void setTownDistrict(String townDistrict) {
    this.townDistrict = townDistrict;
  }

  public String getSubdivisionVillage() {
    return subdivisionVillage;
  }

  public void setSubdivisionVillage(String subdivisionVillage) {
    this.subdivisionVillage = subdivisionVillage;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getLotBlockPhaseNo() {
    return lotBlockPhaseNo;
  }

  public void setLotBlockPhaseNo(String lotBlockPhaseNo) {
    this.lotBlockPhaseNo = lotBlockPhaseNo;
  }

  public String getBuildingTower() {
    return buildingTower;
  }

  public void setBuildingTower(String buildingTower) {
    this.buildingTower = buildingTower;
  }

  public String getUnitRoomFloorNo() {
    return unitRoomFloorNo;
  }

  public void setUnitRoomFloorNo(String unitRoomFloorNo) {
    this.unitRoomFloorNo = unitRoomFloorNo;
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

  public String getZipCodeId() {
    return zipCodeId;
  }

  public void setZipCodeId(String zipCodeId) {
    this.zipCodeId = zipCodeId;
  }

  public String getZipCodeDescription() {
    return zipCodeDescription;
  }

  public void setZipCodeDescription(String zipCodeDescription) {
    this.zipCodeDescription = zipCodeDescription;
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

  public String getCountryid() {
    return countryid;
  }

  public void setCountryid(String countryid) {
    this.countryid = countryid;
  }

  public String getCountryDescription() {
    return countryDescription;
  }

  public void setCountryDescription(String countryDescription) {
    this.countryDescription = countryDescription;
  }

  public String getAddressTypeId() {
    return addressTypeId;
  }

  public void setAddressTypeId(String addressTypeId) {
    this.addressTypeId = addressTypeId;
  }

  public String getAddressTypeDescription() {
    return addressTypeDescription;
  }

  public void setAddressTypeDescription(String addressTypeDescription) {
    this.addressTypeDescription = addressTypeDescription;
  }

  public String getAddressTypeCode() {
    return addressTypeCode;
  }

  public void setAddressTypeCode(String addressTypeCode) {
    this.addressTypeCode = addressTypeCode;
  }

  public String getBusinessCode() {
    return businessCode;
  }

  public void setBusinessCode(String businessCode) {
    this.businessCode = businessCode;
  }

  public String getiTSFlag() {
    return iTSFlag;
  }

  public void setiTSFlag(String iTSFlag) {
    this.iTSFlag = iTSFlag;
  }

  public String getStatusFlag() {
    return statusFlag;
  }

  public void setStatusFlag(String statusFlag) {
    this.statusFlag = statusFlag;
  }

  public String getDbSource() {
    return dbSource;
  }

  public void setDbSource(String dbSource) {
    this.dbSource = dbSource;
  }

  public String getNameSoundex() {
    return nameSoundex;
  }

  public void setNameSoundex(String nameSoundex) {
    this.nameSoundex = nameSoundex;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getUploadedBy() {
    return uploadedBy;
  }

  public void setUploadedBy(String uploadedBy) {
    this.uploadedBy = uploadedBy;
  }

  public Date getDateMigrated() {
    return dateMigrated;
  }

  public void setDateMigrated(Date dateMigrated) {
    this.dateMigrated = dateMigrated;
  }

}
