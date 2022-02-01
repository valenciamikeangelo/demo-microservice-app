/*
  * Modified by: obregoj
  * Last updated: Dec 4, 2019 10:27:01 AM
  */
package com.caista.birapps.etis.domain.sysad.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.util.ObjectUtils;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintOfficeClassType;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceOfficeType;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * The Class Office.
 */
@Entity
@Table(name = "OFFICE",
    uniqueConstraints = @UniqueConstraint(columnNames = {"OFFICE_CODE"}, name = "UC_OFFICE_01"),
    indexes = {@Index(columnList = "OFFICE_CODE", name = "idx_OFFICE_01")})
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Office implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The office id. */
  @Id
  @GeneratedValue(generator = "OFFICE_SequenceStyleGenerator")
  @GenericGenerator(name = "OFFICE_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_OFFICE"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "OFFICE_ID", nullable = false, updatable = false)
  private Long id;

  /** The office code. */
  @Column(name = "OFFICE_CODE", columnDefinition = "VARCHAR2(20)", nullable = false,
      updatable = false)
  private String code;


  /** The office type. */
  @OneToOne
  @JoinColumn(name = "OFFICE_TYPE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_REF_OFFICE_TYPE__OFFICE_01"))
  private ReferenceOfficeType officeType;

  @OneToOne
  @JoinColumn(name = "OFFICE_CLASS_TYPE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_CLASS_TYPE__OFFICE_01"))
  private MaintOfficeClassType officeClassType;


  /** The case office flag. */
  @Column(name = "CASE_OFFICE", nullable = false, columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean caseOfficeFlag;

  @Column(name = "LARGE_TAXPAYER_OFFICE")
  private Boolean largeTaxpayerOfficeFlag;

  /** The created by. */
  @Column(name = "CREATED_BY", updatable = false, nullable = false,
      columnDefinition = "VARCHAR2(20)")
  private String createdBy;

  /** The created date. */
  @Column(name = "CREATED_DATE", updatable = false, nullable = false,
      columnDefinition = "TIMESTAMP(6)")
  private Date createdDate;

  /** The description. */
  @Column(name = "OFFICE_DESCRIPTION", columnDefinition = "VARCHAR2(80)", nullable = false)
  private String description;

  /** The effective date. */
  @Column(name = "EFFECTIVE_DATE", nullable = false, columnDefinition = "DATE")
  private Date effectiveDate;

  /** The expiry date. */
  @Column(name = "EXPIRY_DATE", nullable = false, columnDefinition = "DATE")
  private Date expiryDate;

  /** The restrict reg add flag. */
  @Column(name = "RESTRICT_VIEW_ADDRESS")
  private Boolean restrictRegAddFlag;

  /** The updated by. */
  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  /** The updated date. */
  @Column(name = "UPDATED_DATE", columnDefinition = "TIMESTAMP(6)")
  private Date updatedDate;
  
  /** The is iris officer flag. */
  @Column(name = "IS_IRIS_OFFICE", nullable = false, columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean isIrisOfficeFlag;

  public Office() {
    super();
  }

  public Office(Long id) {
    super();
    this.id = id;
  }

  public Office(String code) {
    super();
    this.code = code;
  }



  public Office(Long id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }



  public Office(Long id, String code, Boolean caseOfficeFlag, Boolean largeTaxpayerOfficeFlag,
      String description, Boolean restrictRegAddFlag) {
    super();
    this.id = id;
    this.code = code;
    this.caseOfficeFlag = caseOfficeFlag;
    this.largeTaxpayerOfficeFlag = largeTaxpayerOfficeFlag;
    this.description = description;
    this.restrictRegAddFlag = restrictRegAddFlag;
  }

  public Office(Long id, String code, Boolean caseOfficeFlag, Boolean largeTaxpayerOfficeFlag,
      String description, Boolean restrictRegAddFlag, ReferenceOfficeType officeType) {
    super();
    this.id = id;
    this.code = code;
    this.caseOfficeFlag = caseOfficeFlag;
    this.largeTaxpayerOfficeFlag = largeTaxpayerOfficeFlag;
    this.description = description;
    this.restrictRegAddFlag = restrictRegAddFlag;
    this.officeType = officeType;
    if (!ObjectUtils.isEmpty(officeType)) {
      this.officeType = new ReferenceOfficeType(officeType.getId(), officeType.getCode(),
          officeType.getDescription());
    }
  }



  public Office(Long id, String code, String description, Date createdDate) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.createdDate = createdDate;
  }



  public Office(Long id, String code, String description, ReferenceOfficeType officeType) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.officeType = new ReferenceOfficeType(officeType.getId(), officeType.getCode(),
        officeType.getDescription());
  }



  public Office(Boolean isIrisOfficeFlag, Long id, String code, String description, ReferenceOfficeType officeType) {
    super();
    this.isIrisOfficeFlag = isIrisOfficeFlag;
    this.id = id;
    this.code = code;
    this.description = description;
    this.officeType = new ReferenceOfficeType(officeType.getId(), officeType.getCode(),
        officeType.getDescription());
  }

  public Office(Long id, String code, String description, ReferenceOfficeType officeType,
      Boolean largeTaxpayerOfficeFlag) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.officeType = new ReferenceOfficeType(officeType.getId(), officeType.getCode(),
        officeType.getDescription());
    this.largeTaxpayerOfficeFlag = largeTaxpayerOfficeFlag;
  }



  public Office(Long id, String code, Boolean largeTaxpayerOfficeFlag, String description,
      ReferenceOfficeType officeType) {
    super();
    this.id = id;
    this.code = code;
    this.largeTaxpayerOfficeFlag = largeTaxpayerOfficeFlag;
    this.description = description;
    this.officeType = new ReferenceOfficeType(officeType.getId(), officeType.getCode(),
        officeType.getDescription());
  }



  public Office(Long id, String code, Boolean largeTaxpayerOfficeFlag, String description) {
    super();
    this.id = id;
    this.code = code;
    this.largeTaxpayerOfficeFlag = largeTaxpayerOfficeFlag;
    this.description = description;
  }



  public Office(Long id, String code, Boolean caseOfficeFlag, String description,
      Date effectiveDate, Date expiryDate, ReferenceOfficeType officeType,
      Boolean restrictRegAddFlag, Boolean largeTaxpayerOfficeFlag,
      MaintOfficeClassType officeClassType) {
    super();
    this.id = id;
    this.code = code;
    this.caseOfficeFlag = caseOfficeFlag;
    this.description = description;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.officeType = new ReferenceOfficeType(officeType.getId(), officeType.getCode(),
        officeType.getDescription());
    this.restrictRegAddFlag = restrictRegAddFlag;
    this.largeTaxpayerOfficeFlag = largeTaxpayerOfficeFlag;
    this.officeClassType = new MaintOfficeClassType(officeClassType.getId(),
        officeClassType.getCode(), officeClassType.getDescription());
  }



  public Office(Long id, String code, Boolean caseOfficeFlag, String createdBy, Date createdDate,
      String description, Date effectiveDate, Date expiryDate, Boolean restrictRegAddFlag,
      String updatedBy, Date updatedDate, ReferenceOfficeType officeType,
      Boolean largeTaxpayerOfficeFlag) {
    super();
    this.id = id;
    this.code = code;
    this.caseOfficeFlag = caseOfficeFlag;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.description = description;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.restrictRegAddFlag = restrictRegAddFlag;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.officeType = new ReferenceOfficeType(officeType.getId(), officeType.getCode(),
        officeType.getDescription());
    this.largeTaxpayerOfficeFlag = largeTaxpayerOfficeFlag;
  }



  public Office(Long id, String code, ReferenceOfficeType officeType, Boolean caseOfficeFlag,
      Boolean largeTaxpayerOfficeFlag, String description, Boolean restrictRegAddFlag) {
    super();
    this.id = id;
    this.code = code;
    this.officeType = new ReferenceOfficeType(officeType.getId(), officeType.getCode(),
        officeType.getDescription());
    this.caseOfficeFlag = caseOfficeFlag;
    this.largeTaxpayerOfficeFlag = largeTaxpayerOfficeFlag;
    this.description = description;
    this.restrictRegAddFlag = restrictRegAddFlag;
  }

  public Office(Long id, String code, Boolean caseOfficeFlag, String description,
      Date effectiveDate, Date expiryDate, ReferenceOfficeType officeType,
      Boolean restrictRegAddFlag, Boolean largeTaxpayerOfficeFlag,
      MaintOfficeClassType officeClassType, Boolean isIrisOfficeFlag) {
    super();
    this.id = id;
    this.code = code;
    this.caseOfficeFlag = caseOfficeFlag;
    this.description = description;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.officeType = new ReferenceOfficeType(officeType.getId(), officeType.getCode(),
        officeType.getDescription());
    this.restrictRegAddFlag = restrictRegAddFlag;
    this.largeTaxpayerOfficeFlag = largeTaxpayerOfficeFlag;
    this.officeClassType = new MaintOfficeClassType(officeClassType.getId(),
        officeClassType.getCode(), officeClassType.getDescription());
    this.isIrisOfficeFlag = isIrisOfficeFlag;
  }

  public Long getId() {
    return id;
  }



  public void setId(Long id) {
    this.id = id;
  }



  public String getCode() {
    return code;
  }



  public void setCode(String code) {
    this.code = code;
  }



  public Boolean getCaseOfficeFlag() {
    if (caseOfficeFlag == null) {
      caseOfficeFlag = false;
    }
    return caseOfficeFlag;
  }



  public void setCaseOfficeFlag(Boolean caseOfficeFlag) {
    this.caseOfficeFlag = caseOfficeFlag;
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



  public Boolean getRestrictRegAddFlag() {
    if (restrictRegAddFlag == null) {
      restrictRegAddFlag = false;
    }
    return restrictRegAddFlag;
  }



  public void setRestrictRegAddFlag(Boolean restrictRegAddFlag) {
    this.restrictRegAddFlag = restrictRegAddFlag;
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



  public ReferenceOfficeType getOfficeType() {
    return officeType;
  }



  public void setOfficeType(ReferenceOfficeType officeType) {
    this.officeType = officeType;
  }



  public Boolean getLargeTaxpayerOfficeFlag() {
    if (largeTaxpayerOfficeFlag == null) {
      largeTaxpayerOfficeFlag = false;
    }
    return largeTaxpayerOfficeFlag;
  }



  public void setLargeTaxpayerOfficeFlag(Boolean largeTaxpayerOfficeFlag) {
    this.largeTaxpayerOfficeFlag = largeTaxpayerOfficeFlag;
  }



  public static long getSerialversionuid() {
    return serialVersionUID;
  }



  public MaintOfficeClassType getOfficeClassType() {
    return officeClassType;
  }



  public void setOfficeClassType(MaintOfficeClassType officeClassType) {
    this.officeClassType = officeClassType;
  }



  public Boolean getIsIrisOfficeFlag() {
	    if (isIrisOfficeFlag == null) {
	    	isIrisOfficeFlag = false;
	      }
    return isIrisOfficeFlag;
  }

  public void setIsIrisOfficeFlag(Boolean isIrisOfficeFlag) {
    this.isIrisOfficeFlag = isIrisOfficeFlag;
  }

  @Override
  public String toString() {
    return "Office [id=" + id + ", code=" + code + ", officeType=" + officeType
        + ", officeClassType=" + officeClassType + ", caseOfficeFlag=" + caseOfficeFlag
        + ", largeTaxpayerOfficeFlag=" + largeTaxpayerOfficeFlag + ", createdBy=" + createdBy
        + ", createdDate=" + createdDate + ", description=" + description + ", effectiveDate="
        + effectiveDate + ", expiryDate=" + expiryDate + ", restrictRegAddFlag="
        + restrictRegAddFlag + ", isIrisOfficeFlag=" + isIrisOfficeFlag 
        + "updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
  }


}
