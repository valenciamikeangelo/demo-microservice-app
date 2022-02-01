/*
  * Modified by: obregoj
  * Last updated: Nov 20, 2019 9:15:30 AM
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
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceCoverageType;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "OFFICE_COVERAGE",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"OFFICE_ID", "CITY_MUNICIPALITY_ID", "BARANGAY_ID"},
        name = "UC_OFFICE_COVERAGE_01"))
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficeCoverage implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "OFFICE_COVERAGE_SequenceStyleGenerator")
  @GenericGenerator(name = "OFFICE_COVERAGE_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_OFFICE_COVERAGE"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "OFFICE_COVERAGE_ID", nullable = false, updatable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "COVERAGE_TYPE_ID", nullable = false, updatable = false,
      foreignKey = @ForeignKey(name = "FK_REF_COVERAGE_TYPE__OFFICE_COVERAGE_01"))
  private ReferenceCoverageType coverageType;

  @ManyToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false, updatable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__OFFICE_COVERAGE_01"))
  private Office office;

  @ManyToOne
  @JoinColumn(name = "CITY_MUNICIPALITY_ID", nullable = true, updatable = false,
      foreignKey = @ForeignKey(name = "FK_MAIN_CITY_MUNICIPALITY__OFFICE_COVERAGE_01"))
  private MaintCityMunicipality cityMunicipality;

  @ManyToOne
  @JoinColumn(name = "BARANGAY_ID", nullable = true, updatable = false,
      foreignKey = @ForeignKey(name = "FK_MAIN_BARANGAY__OFFICE_COVERAGE_01"))
  private MaintBarangay barangay;

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

  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
  private Date expiryDate;

  @Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE")
  private Date effectiveDate;

  public OfficeCoverage() {
    super();
  }

  public OfficeCoverage(Long id, ReferenceCoverageType coverageType,
      MaintCityMunicipality cityMunicipality, MaintBarangay barangay) {
    super();
    this.id = id;
    this.coverageType = new ReferenceCoverageType(coverageType.getId(), coverageType.getCode(),
        coverageType.getDescription());
    if (cityMunicipality != null)
      this.cityMunicipality = new MaintCityMunicipality(cityMunicipality.getId(),
          cityMunicipality.getCode(), cityMunicipality.getDescription());
    if (barangay != null)
      this.barangay = new MaintBarangay(barangay.getId(), barangay.getCode(),
          barangay.getDescription());
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

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public Date getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public ReferenceCoverageType getCoverageType() {
    return coverageType;
  }

  public void setCoverageType(ReferenceCoverageType coverageType) {
    this.coverageType = coverageType;
  }

  public Office getOffice() {
    return office;
  }

  public void setOffice(Office office) {
    this.office = office;
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

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "OfficeCoverage [id=" + id + ", createdBy=" + createdBy + ", createdDate=" + createdDate
        + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", expiryDate=" + expiryDate
        + ", effectiveDate=" + effectiveDate + ", coverageType=" + coverageType + ", office="
        + office + ", cityMunicipality=" + cityMunicipality + ", barangay=" + barangay + "]";
  }

}
