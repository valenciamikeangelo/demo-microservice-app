/*
  * Modified by: obregoj
  * Last updated: Dec 2, 2019 1:50:34 PM
  */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.entity.Office;
import com.caista.birapps.etis.domain.sysad.entity.reference.*;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_OFFICE_DESIGNATION",
    uniqueConstraints = {@UniqueConstraint(
        columnNames = {"DESIGNATION_ID", "OFFICE_TYPE_ID", "OFFICE_GROUP_ID", "OFFICE_SERVICE_ID",
            "OFFICE_LTS_GROUP_ID", "OFFICE_DIVISION_ID", "OFFICE_ID", "OFFICE_SECTION_ID"},
        name = "UC_MAIN_OFFICE_DESIGNATION_01")})
@JsonInclude(Include.NON_NULL)
public class MaintOfficeDesignation implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "OFDS", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "OFFICE_DESIGNATION_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @ManyToOne
  @JoinColumn(name = "DESIGNATION_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_REF_DESIGNATION__MAIN_OFFICE_DESIGNATION_01"))
  private ReferenceDesignation designation;

  @ManyToOne
  @JoinColumn(name = "OFFICE_TYPE_ID",
      foreignKey = @ForeignKey(name = "FK_REF_OFFICE_TYPE__MAIN_OFFICE_DESIGNATION_01"))
  private ReferenceOfficeType officeType;

  @ManyToOne
  @JoinColumn(name = "OFFICE_GROUP_ID",
      foreignKey = @ForeignKey(name = "FK_REF_OFFICE_GROUP__MAIN_OFFICE_DESIGNATION_01"))
  private ReferenceOfficeGroup officeGroup;

  @ManyToOne
  @JoinColumn(name = "OFFICE_SERVICE_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_SERVICE__MAIN_OFFICE_DESIGNATION_01"))
  private MaintOfficeService officeService;

  @ManyToOne
  @JoinColumn(name = "OFFICE_LTS_GROUP_ID",
      foreignKey = @ForeignKey(name = "FK_REF_OFFICE_LTS_GROUP__MAIN_OFFICE_DESIGNATION_01"))
  private ReferenceOfficeLtsGroup officeLtsGroup;

  @ManyToOne
  @JoinColumn(name = "OFFICE_DIVISION_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_DIVISION__MAIN_OFFICE_DESIGNATION_01"))
  private MaintOfficeDivision officeDivision;

  @ManyToOne
  @JoinColumn(name = "OFFICE_ID",
      foreignKey = @ForeignKey(name = "FK_OFFICE__MAIN_OFFICE_DESIGNATION_01"))
  private Office office;

  @ManyToOne
  @JoinColumn(name = "OFFICE_SECTION_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_SECTION__MAIN_OFFICE_DESIGNATION_01"))
  private MaintOfficeSection officeSection;

  @Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE", nullable = false)
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE", nullable = false)
  private Date expiryDate;

  @Column(name = "CREATED_BY", updatable = false, columnDefinition = "VARCHAR2(20)",
      nullable = false)
  private String createdBy;

  @Column(name = "CREATED_DATE", updatable = false, columnDefinition = "TIMESTAMP(6)",
      nullable = false)
  private Date createdDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE", columnDefinition = "TIMESTAMP(6)")
  private Date updatedDate;

  public MaintOfficeDesignation() {
    super();
  }

  @Override
  public String getId() {
    return id;
  }

  public Date getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  @Override
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

  @Override
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

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public void setId(String id) {
    this.id = id;
  }

  public MaintOfficeService getOfficeService() {
    return officeService;
  }

  public void setOfficeService(MaintOfficeService officeService) {
    this.officeService = officeService;
  }

  public ReferenceOfficeLtsGroup getOfficeLtsGroup() {
    return officeLtsGroup;
  }

  public void setOfficeLtsGroup(ReferenceOfficeLtsGroup officeLtsGroup) {
    this.officeLtsGroup = officeLtsGroup;
  }

  public ReferenceDesignation getDesignation() {
    return designation;
  }

  public void setDesignation(ReferenceDesignation designation) {
    this.designation = designation;
  }

  public ReferenceOfficeGroup getOfficeGroup() {
    return officeGroup;
  }

  public void setOfficeGroup(ReferenceOfficeGroup officeGroup) {
    this.officeGroup = officeGroup;
  }

  public MaintOfficeDivision getOfficeDivision() {
    return officeDivision;
  }

  public void setOfficeDivision(MaintOfficeDivision officeDivision) {
    this.officeDivision = officeDivision;
  }

  public Office getOffice() {
    return office;
  }

  public void setOffice(Office office) {
    this.office = office;
  }

  public MaintOfficeSection getOfficeSection() {
    return officeSection;
  }

  public void setOfficeSection(MaintOfficeSection officeSection) {
    this.officeSection = officeSection;
  }

  public ReferenceOfficeType getOfficeType() {
    return officeType;
  }

  public void setOfficeType(ReferenceOfficeType officeType) {
    this.officeType = officeType;
  }

  @Override
  public String toString() {
    return "MaintOfficeDesignation [id=" + id + ", designation=" + designation + ", officeType="
        + officeType + ", officeGroup=" + officeGroup + ", officeService=" + officeService
        + ", officeLtsGroup=" + officeLtsGroup + ", officeDivision=" + officeDivision + ", office="
        + office + ", officeSection=" + officeSection + ", effectiveDate=" + effectiveDate
        + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate
        + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
  }

}
