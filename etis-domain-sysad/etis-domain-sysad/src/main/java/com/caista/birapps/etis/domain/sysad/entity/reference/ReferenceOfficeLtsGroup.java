/*
  * Modified by: obregoj
  * Last updated: Nov 27, 2019 4:38:12 PM
  */
package com.caista.birapps.etis.domain.sysad.entity.reference;

/*
 * Last modified by: feliped Last updated date: Oct 23, 2019 3:01:37 PM
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "REF_OFFICE_LTS_GROUP",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"OFFICE_LTS_GROUP_CODE"},
        name = "UC_REF_OFFICE_LTS_GROUP_01")},
    indexes = {@Index(columnList = "OFFICE_LTS_GROUP_CODE", name = "IDX_REF_OFFICE_LTS_GROUP_01")})
@JsonInclude(Include.NON_NULL)
public class ReferenceOfficeLtsGroup implements Serializable, Auditable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "OFLGR", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "OFFICE_LTS_GROUP_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "OFFICE_LTS_GROUP_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "OFFICE_LTS_GROUP_DESCRIPTION", columnDefinition = "VARCHAR2(80)",
      nullable = false)
  private String description;

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

  public ReferenceOfficeLtsGroup() {
    super();
  }

  public ReferenceOfficeLtsGroup(String code) {
    super();
    this.code = code;
  }

  public ReferenceOfficeLtsGroup(String code, String description) {
    super();
    this.code = code;
    this.description = description;
  }



  public ReferenceOfficeLtsGroup(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public ReferenceOfficeLtsGroup(String id, String code, String description, String createdBy) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.createdBy = createdBy;
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

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "ReferenceOfficeLtsGroup [id=" + id + ", code=" + code + ", description=" + description
        + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + "]";
  }

}
