/*
  * Modified by: obregoj
  * Last updated: Nov 20, 2019 9:15:15 AM
  */
package com.caista.birapps.etis.domain.sysad.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "OFFICE_ADDRESS", uniqueConstraints = @UniqueConstraint(columnNames = {"OFFICE_ID"},
    name = "UC_OFFICE_ADDRESS_01"))
@JsonInclude(Include.NON_NULL)
public class OfficeAddress implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "OFFICE_ADDRESS_SequenceStyleGenerator")
  @GenericGenerator(name = "OFFICE_ADDRESS_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_OFFICE_ADDRESS"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "OFFICE_ADDRESS_ID", nullable = false, updatable = false)
  private Long id;


  @OneToOne
  @JoinColumn(name = "PROVINCE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_MAIN_PROVINCE__OFFICE_ADDRESS_01"))
  private MaintProvince province;

  @OneToOne
  @JoinColumn(name = "CITY_MUNICIPALITY_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_MAIN_CITY_MUNICIPALITY__OFFICE_ADDRESS_01"))
  private MaintCityMunicipality cityMunicipality;

  @OneToOne
  @JoinColumn(name = "BARANGAY_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_MAIN_BARANGAY__OFFICE_ADDRESS_01"))
  private MaintBarangay barangay;

  @OneToOne
  @JoinColumn(name = "ZIP_CODE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_MAIN_ZIP_CODE__OFFICE_ADDRESS_01"))
  private MaintZipCode zipCode;

  @OneToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false, updatable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__OFFICE_ADDRESS_01"))
  private Office office;

  @Column(name = "CREATED_BY", updatable = false, nullable = false,
      columnDefinition = "VARCHAR(20)")
  private String createdBy;

  @Column(name = "CREATED_DATE", updatable = false, nullable = false,
      columnDefinition = "TIMESTAMP(6)")
  private Date createdDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE", columnDefinition = "TIMESTAMP(6)")
  private Date updatedDate;

  @Column(name = "UNIT_NUMBER", columnDefinition = "VARCHAR(20)")
  private String unitNumber;

  @Column(name = "BUILDING", columnDefinition = "VARCHAR(20)")
  private String building;

  @Column(name = "BLOCK_LOT_PHASE", columnDefinition = "VARCHAR(20)")
  private String blockLotPhase;

  @Column(name = "STREET", columnDefinition = "VARCHAR(20)")
  private String street;

  @Column(name = "SUBDIVISION_VILLAGE", columnDefinition = "VARCHAR(20)")
  private String subdivisionVillage;

  @Column(name = "TOWN_DISTRICT", columnDefinition = "VARCHAR(20)")
  private String townDistrict;


  public OfficeAddress() {
    super();
  }


  public OfficeAddress(Long id, MaintProvince province, MaintCityMunicipality cityMunicipality,
      MaintBarangay barangay, MaintZipCode zipCode, String unitNumber, String building,
      String blockLotPhase, String street, String subdivisionVillage, String townDistrict) {
    super();
    this.id = id;
    this.province = new MaintProvince(province.getId(), province.getCode(),
        province.getDescription());
    this.cityMunicipality = new MaintCityMunicipality(cityMunicipality.getId(),
        cityMunicipality.getCode(), cityMunicipality.getDescription());
    this.barangay = new MaintBarangay(barangay.getId(), barangay.getCode(),
        barangay.getDescription());
    this.zipCode = new MaintZipCode(zipCode.getId(), zipCode.getCode());
    this.unitNumber = unitNumber;
    this.building = building;
    this.blockLotPhase = blockLotPhase;
    this.street = street;
    this.subdivisionVillage = subdivisionVillage;
    this.townDistrict = townDistrict;
  }


  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public String getCreatedBy() {
    return createdBy;
  }


  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }


  public Date getCreatedDate() {
    return createdDate;
  }


  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }


  public String getUpdatedBy() {
    return updatedBy;
  }


  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }


  public Date getUpdatedDate() {
    return updatedDate;
  }


  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }


  public String getUnitNumber() {
    return unitNumber;
  }


  public void setUnitNumber(String unitNumber) {
    this.unitNumber = unitNumber;
  }


  public String getBuilding() {
    return building;
  }


  public void setBuilding(String building) {
    this.building = building;
  }


  public String getBlockLotPhase() {
    return blockLotPhase;
  }


  public void setBlockLotPhase(String blockLotPhase) {
    this.blockLotPhase = blockLotPhase;
  }


  public String getStreet() {
    return street;
  }


  public void setStreet(String street) {
    this.street = street;
  }


  public String getSubdivisionVillage() {
    return subdivisionVillage;
  }


  public void setSubdivisionVillage(String subdivisionVillage) {
    this.subdivisionVillage = subdivisionVillage;
  }


  public String getTownDistrict() {
    return townDistrict;
  }


  public void setTownDistrict(String townDistrict) {
    this.townDistrict = townDistrict;
  }


  public MaintProvince getProvince() {
    return province;
  }


  public void setProvince(MaintProvince province) {
    this.province = province;
  }


  public MaintCityMunicipality getCityMunicipality() {
    return cityMunicipality;
  }


  public void setCityMunicipality(MaintCityMunicipality cityMunicipality) {
    this.cityMunicipality = cityMunicipality;
  }


  public MaintBarangay getBarangay() {
    return barangay;
  }


  public void setBarangay(MaintBarangay barangay) {
    this.barangay = barangay;
  }


  public MaintZipCode getZipCode() {
    return zipCode;
  }


  public void setZipCode(MaintZipCode zipCode) {
    this.zipCode = zipCode;
  }


  public Office getOffice() {
    return office;
  }


  public void setOffice(Office office) {
    this.office = office;
  }


  public static long getSerialversionuid() {
    return serialVersionUID;
  }


  @Override
  public String toString() {
    return "OfficeAddress [id=" + id + ", createdBy=" + createdBy + ", createdDate=" + createdDate
        + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", unitNumber=" + unitNumber
        + ", building=" + building + ", blockLotPhase=" + blockLotPhase + ", street=" + street
        + ", subdivisionVillage=" + subdivisionVillage + ", townDistrict=" + townDistrict
        + ", province=" + province + ", cityMunicipality=" + cityMunicipality + ", barangay="
        + barangay + ", zipCode=" + zipCode + ", office=" + office + "]";
  }


}
