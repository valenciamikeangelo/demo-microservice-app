/*
 * Modified by: obregoj Last updated: Jul 3, 2019 4:40:39 PM
 */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

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
@Table(name = "MAIN_IDENTIFIER_TYPE",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"IDENTIFIER_TYPE_CODE"},
        name = "UC_MAINT_IDENTIFIER_TYPE_01")},
    indexes = {@Index(columnList = "IDENTIFIER_TYPE_CODE", name = "IDX_MAIN_IDENTIFIER_TYPE_01")})
@JsonInclude(Include.NON_NULL)
public class MaintIdentifierType implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "IDNTYPE", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "IDENTIFIER_TYPE_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "IDENTIFIER_TYPE_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "IDENTIFIER_TYPE_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(80)")
  private String description;

  @Column(name = "EFFECTIVE_DATE", nullable = false, columnDefinition = "DATE")
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", nullable = false, columnDefinition = "DATE")
  private Date expiryDate;

  @Column(name = "CREATED_BY", nullable = false, updatable = false,
      columnDefinition = "VARCHAR2(20)")
  private String createdBy;

  @Column(name = "CREATED_DATE", nullable = false, updatable = false)
  private Date createdDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  @Column(name = "IDENTIFIER_CODE", nullable = false, columnDefinition = "VARCHAR2(20)")
  private String identifierCode;

  @Column(name = "IDENTIFIER_NAME", nullable = false, columnDefinition = "VARCHAR2(80)")
  private String identifierName;

  @Transient
  private String status;

  public MaintIdentifierType() {
    super();
  }

  public MaintIdentifierType(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public MaintIdentifierType(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public MaintIdentifierType(String id, String code, String description, String createdBy) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.createdBy = createdBy;
  }

  public MaintIdentifierType(String id, String code, String description, Date effectiveDate,
      Date expiryDate, String createdBy, Date createdDate, String updatedBy, Date updatedDate,
      String identifierCode, String identifierName) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.identifierCode = identifierCode;
    this.identifierName = identifierName;
  }

  @Override
  public String getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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

  public String getIdentifierCode() {
    return identifierCode;
  }

  public void setIdentifierCode(String identifierCode) {
    this.identifierCode = identifierCode;
  }

  public String getIdentifierName() {
    return identifierName;
  }

  public void setIdentifierName(String identifierName) {
    this.identifierName = identifierName;
  }

  @Override
  public String toString() {
    return "MaintIdentifierType [id=" + id + ", code=" + code + ", description=" + description
        + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + ", identifierCode=" + identifierCode + ", identifierName=" + identifierName
        + "]";
  }

}
