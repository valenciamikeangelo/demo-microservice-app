/*
  * Modified by: obregoj
  * Last updated: Dec 5, 2019 8:43:01 AM
  */
package com.caista.birapps.etis.domain.sysad.entity.maintenance;

/*
 * Last modified by: feliped Last updated date: Oct 23, 2019 3:01:37 PM
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.entity.Office;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceOfficeType;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_OFFICE_SECTION",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"OFFICE_SECTION_CODE"},
        name = "UC_MAIN_OFFICE_SECTION_01")},
    indexes = {@Index(columnList = "OFFICE_SECTION_CODE", name = "IDX_MAIN_OFFICE_SECTION_01")})
@JsonInclude(Include.NON_NULL)
public class MaintOfficeSection implements Serializable, Auditable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "OFSC", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "OFFICE_SECTION_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "OFFICE_SECTION_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "OFFICE_SECTION_DESCRIPTION", columnDefinition = "VARCHAR2(80)", nullable = false)
  private String description;

  @ManyToOne
  @JoinColumn(name = "OFFICE_TYPE_ID",
      foreignKey = @ForeignKey(name = "FK_REF_OFFICE_TYPE__MAIN_OFFICE_SECTION_01"))
  private ReferenceOfficeType parentOfficeType;

  @ManyToOne
  @JoinColumn(name = "OFFICE_DIVISION_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_DIVISION__MAIN_OFFICE_SECTION_01"))
  private MaintOfficeDivision officeDivision;

  @ManyToOne
  @JoinColumn(name = "OFFICE_ID",
      foreignKey = @ForeignKey(name = "FK_OFFICE__MAIN_OFFICE_SECTION_01"))
  private Office office;


  @Column(name = "LARGE_TAXPAYER_OFFICE", columnDefinition = "NUMBER(1,0) default '0'",
      nullable = false)
  private boolean isLargeTaxpayerOffice;

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

  public MaintOfficeSection() {
    super();
  }

  public MaintOfficeSection(String code) {
    super();
    this.code = code;
  }

  public MaintOfficeSection(String code, String description) {
    super();
    this.code = code;
    this.description = description;
  }



  public MaintOfficeSection(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public MaintOfficeSection(String id, String code, String description,
      boolean isLargeTaxpayerOffice) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.isLargeTaxpayerOffice = isLargeTaxpayerOffice;
  }

  public MaintOfficeSection(String id, String code, String description, String createdBy) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.createdBy = createdBy;
  }



  public MaintOfficeSection(String id, String code, String description,
      ReferenceOfficeType parentOfficeType, boolean isLargeTaxpayerOffice) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.parentOfficeType = new ReferenceOfficeType(parentOfficeType.getId(),
        parentOfficeType.getCode(), parentOfficeType.getDescription());
    this.isLargeTaxpayerOffice = isLargeTaxpayerOffice;
  }

  @Override
  public String getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public ReferenceOfficeType getParentOfficeType() {
    return parentOfficeType;
  }

  public void setParentOfficeType(ReferenceOfficeType parentOfficeType) {
    this.parentOfficeType = parentOfficeType;
  }

  public void setId(String id) {
    this.id = id;
  }

  public boolean getIsLargeTaxpayerOffice() {
    return isLargeTaxpayerOffice;
  }

  public void setLargeTaxpayerOffice(boolean isLargeTaxpayerOffice) {
    this.isLargeTaxpayerOffice = isLargeTaxpayerOffice;
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

  @Override
  public String toString() {
    return "MaintOfficeSection [id=" + id + ", code=" + code + ", description=" + description
        + ", parentOfficeType=" + parentOfficeType + ", isLargeTaxpayerOffice="
        + isLargeTaxpayerOffice + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
        + ", updatedDate=" + updatedDate + "]";
  }

}
