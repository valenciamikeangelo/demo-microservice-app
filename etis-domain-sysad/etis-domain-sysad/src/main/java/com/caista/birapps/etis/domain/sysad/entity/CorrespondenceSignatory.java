/*
  * Modified by: obregoj
  * Last updated: Oct 30, 2019 5:02:17 PM
  */

package com.caista.birapps.etis.domain.sysad.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceCorrespondence;
import com.caista.birapps.etis.domain.sysad.util.SysadAudit;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "CORRESPONDENCE_SIGNATORY",
    uniqueConstraints = @UniqueConstraint(columnNames = {"CORRESPONDENCE_ID", "OFFICE_ID"},
        name = "UC_CORRESPONDENCE_SIGNATORY_01"))
@JsonInclude(Include.NON_NULL)
public class CorrespondenceSignatory implements Serializable, SysadAudit {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "CORRESPONDENCE_SIGNATORY_SequenceStyleGenerator")
  @GenericGenerator(name = "CORRESPONDENCE_SIGNATORY_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_CORRESPONDENCE_SIGNATORY"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "CORRESPONDENCE_SIGNATORY_ID", unique = true, nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "CORRESPONDENCE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_REF_CORRESPONDENCE__CORRESPONDENCE_SIGNATORY_01"))
  private ReferenceCorrespondence correspondenceType;

  @ManyToOne
  @JoinColumn(name = "STAFF_PRIMARY_OFFICE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_STAFF_PRIMARY_OFFICE__CORRESPONDENCE_SIGNATORY_02"))
  private StaffPrimaryOffice staffPrimaryOffice;

  @ManyToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__CORRESPONDENCE_SIGNATORY_03"))
  private Office officeId;

  @Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE", nullable = false)
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE", nullable = false)
  private Date expiryDate;

  @Column(name = "CREATED_BY", updatable = false, columnDefinition = "VARCHAR2(20)",
      nullable = false)
  private String createdBy;

  @Column(name = "CREATED_DATE", updatable = false, nullable = false)
  private Date createdDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  @Column(name = "POSITION", nullable = false, columnDefinition = "VARCHAR2(90)")
  private String position;

  @Transient
  private String office;

  public CorrespondenceSignatory() {
    super();
  }

  public CorrespondenceSignatory(Long id, ReferenceCorrespondence correspondenceType,
      StaffPrimaryOffice staffPrimaryOffice, Date effectiveDate, Date expiryDate, String createdBy,
      Date createdDate, String updatedBy, Date updatedDate) {
    super();
    this.id = id;
    this.correspondenceType = correspondenceType;
    this.staffPrimaryOffice = staffPrimaryOffice;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
  }


  public CorrespondenceSignatory(Long id, ReferenceCorrespondence correspondenceType,
      StaffPrimaryOffice staffPrimaryOffice, Office officeId, Date effectiveDate, Date expiryDate,
      String createdBy, Date createdDate, String updatedBy, Date updatedDate, String office) {
    super();
    this.id = id;
    this.correspondenceType = correspondenceType;
    this.staffPrimaryOffice = staffPrimaryOffice;
    this.officeId = officeId;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.office = office;
  }

  public CorrespondenceSignatory(Long id, ReferenceCorrespondence correspondenceType,
      StaffPrimaryOffice staffPrimaryOffice, String updatedBy, Date updatedDate) {
    super();
    this.id = id;
    this.correspondenceType = correspondenceType;
    this.staffPrimaryOffice = staffPrimaryOffice;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
  }

  @Override
  public Long getId() {
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

  public ReferenceCorrespondence getCorrespondenceType() {
    return correspondenceType;
  }

  public void setCorrespondenceType(ReferenceCorrespondence correspondenceType) {
    this.correspondenceType = correspondenceType;
  }

  public StaffPrimaryOffice getStaffPrimaryOffice() {
    return staffPrimaryOffice;
  }

  public void setStaffPrimaryOffice(StaffPrimaryOffice staffPrimaryOffice) {
    this.staffPrimaryOffice = staffPrimaryOffice;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }


  public Office getOfficeId() {
    return officeId;
  }

  public void setOfficeId(Office officeId) {
    this.officeId = officeId;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  @Override
  public String toString() {
    return "CorrespondenceSignatory [id=" + id + ", correspondenceType=" + correspondenceType
        + ", staffPrimaryOffice=" + staffPrimaryOffice + ", officeId=" + officeId
        + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + ", position=" + position + ", office=" + office + "]";
  }
}
