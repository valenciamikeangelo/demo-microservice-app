/*
  * Modified by: obregoj
  * Last updated: 12 12, 19 10:04:14 AM
  */
package com.caista.birapps.etis.domain.sysad.entity.maintenance;

/*
 * Last modified by: feliped Last updated date: Oct 23, 2019 3:01:37 PM
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.ObjectUtils;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceOfficeGroup;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_OFFICE_SERVICE",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"OFFICE_SERVICE_CODE"},
        name = "UC_MAIN_OFFICE_SERVICE_01")},
    indexes = {@Index(columnList = "OFFICE_SERVICE_CODE", name = "IDX_MAIN_OFFICE_SERVICE_01")})
@JsonInclude(Include.NON_NULL)
public class MaintOfficeService implements Serializable, Auditable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "OFSR", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "OFFICE_SERVICE_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "OFFICE_SERVICE_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "OFFICE_SERVICE_DESCRIPTION", columnDefinition = "VARCHAR2(80)", nullable = false)
  private String description;

  @ManyToOne
  @JoinColumn(name = "OFFICE_GROUP_ID",
      foreignKey = @ForeignKey(name = "FK_REF_OFFICE_GROUP__MAIN_OFFICE_SERVICE_01"))
  private ReferenceOfficeGroup officeGroup;

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

  public MaintOfficeService() {
    super();
  }

  public MaintOfficeService(String code) {
    super();
    this.code = code;
  }

  public MaintOfficeService(String code, String description) {
    super();
    this.code = code;
    this.description = description;
  }



  public MaintOfficeService(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public MaintOfficeService(String id, String code, String description, String createdBy) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.createdBy = createdBy;
  }


  public MaintOfficeService(String id, String code, String description,
      ReferenceOfficeGroup officeGroup) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.officeGroup = officeGroup;
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

  public ReferenceOfficeGroup getOfficeGroup() {
    if (ObjectUtils.isEmpty(officeGroup)) {
      return officeGroup;
    }
    return new ReferenceOfficeGroup(officeGroup.getId(), officeGroup.getCode(),
        officeGroup.getDescription());
  }

  public void setOfficeGroup(ReferenceOfficeGroup officeGroup) {
    this.officeGroup = officeGroup;
  }

  @Override
  public String toString() {
    return "MaintOfficeService [id=" + id + ", code=" + code + ", description=" + description
        + ", officeGroup=" + officeGroup + ", effectiveDate=" + effectiveDate + ", expiryDate="
        + expiryDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
        + updatedBy + ", updatedDate=" + updatedDate + "]";
  }

}
