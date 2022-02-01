/*
  * Modified by: obregoj
  * Last updated: 04 2, 20 1:42:03 PM
  */
package com.caista.birapps.etis.domain.sysad.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;
import org.springframework.util.ObjectUtils;
import com.caista.birapps.etis.domain.object.User;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.*;
import com.caista.birapps.etis.domain.sysad.entity.reference.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class StaffAssignment.
 */
@Entity
@Table(name = "STAFF_CASE_ACCESS",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"USERNAME", "OFFICE_ID"},
        name = "UC_STAFF_CASE_ACCESS_01")},
    indexes = {@Index(columnList = "OFFICE_ID", name = "idx_STAFF_CASE_ACCESS_01")})
@JsonInclude(Include.NON_NULL)
public class StaffCaseAccess implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The staff assignment id. */
  @Id
  @GeneratedValue(generator = "STAFF_CASE_ACCESS_SequenceStyleGenerator")
  @GenericGenerator(name = "STAFF_CASE_ACCESS_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_STAFF_CASE_ACCESS"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "STAFF_CASE_ACCESS_ID", nullable = false, updatable = false)
  private Long id;

  @OneToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false, updatable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__STAFF_CASE_ACCESS_01"))
  private Office office;

  @OneToOne
  @JoinColumn(name = "OFFICE_GROUP_ID",
      foreignKey = @ForeignKey(name = "FK_REF_OFFICE_GROUP__STAFF_CASE_ACCESS_01"))
  private ReferenceOfficeGroup officeGroup;

  @OneToOne
  @JoinColumn(name = "OFFICE_LTS_GROUP_ID",
      foreignKey = @ForeignKey(name = "FK_REF_OFFICE_LTS_GROUP__STAFF_CASE_ACCESS_01"))
  private ReferenceOfficeLtsGroup officeLtsGroup;

  @OneToOne
  @JoinColumn(name = "OFFICE_SERVICE_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_SERVICE__STAFF_CASE_ACCESS_01"))
  private MaintOfficeService officeService;

  @OneToOne
  @JoinColumn(name = "OFFICE_DIVISION_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_DIVISION__STAFF_CASE_ACCESS_01"))
  private MaintOfficeDivision officeDivision;

  @OneToOne
  @JoinColumn(name = "OFFICE_SECTION_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_SECTION__STAFF_CASE_ACCESS_01"))
  private MaintOfficeSection officeSection;

  @OneToOne
  @JoinColumn(name = "OFFICE_DESIGNATION_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_DESIGNATION__STAFF_CASE_ACCESS_01"))
  private MaintOfficeDesignation officeDesignation;

  @Column(name = "IS_OFFICE_SECTION_HEAD", nullable = false,
      columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private boolean officeSectionHead;

  @Column(name = "IS_CASE_OFFICER", nullable = false, columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private boolean caseOfficer;

  @Column(name = "GROUP_SUPERVISOR_USERNAME", columnDefinition = "VARCHAR(20)")
  private String groupSupervisor;

  @Cascade(CascadeType.SAVE_UPDATE)
  @ManyToMany
  @JoinTable(name = "STAFF_CASE_TYPE_ASSIGNMENT",
      joinColumns = @JoinColumn(name = "STAFF_CASE_ACCESS_ID",
          referencedColumnName = "STAFF_CASE_ACCESS_ID",
          foreignKey = @ForeignKey(name = "FK_STAFF_CASE_ACCESS__STAFF_CASE_TYPE_ASSIGNMENT_01")),
      inverseJoinColumns = @JoinColumn(name = "CASE_TYPE_ID", referencedColumnName = "CASE_TYPE_ID",
          foreignKey = @ForeignKey(name = "FK_MAIN_CASE_TYPE__STAFF_CASE_TYPE_ASSIGNMENT_01")),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"STAFF_CASE_ACCESS_ID", "CASE_TYPE_ID"},
          name = "UC_STAFF_CASE_TYPE_ASSIGNMENT_01")})
  private List<MaintCaseType> caseTypes;

  @Column(name = "USERNAME", updatable = false, nullable = false, columnDefinition = "VARCHAR(20)")
  private String username;

  @Column(name = "CREATED_BY", updatable = false, nullable = false,
      columnDefinition = "VARCHAR(20)")
  private String createdBy;

  @Column(name = "CREATED_DATE", updatable = false, nullable = false,
      columnDefinition = "TIMESTAMP(6)")
  private Date createdDate;

  @Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE")
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
  private Date expiryDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE", columnDefinition = "TIMESTAMP(6)")
  private Date updatedDate;

  @Transient
  private User userDetails;

  public StaffCaseAccess() {
    super();
  }

  public StaffCaseAccess(Long id, Office office, MaintOfficeDesignation officeDesignation,
      boolean officeSectionHead, boolean caseOfficer, String groupSupervisor,
      List<MaintCaseType> caseTypes, String username) {
    super();
    this.id = id;
    this.office = new Office(office.getId(), office.getCode(), office.getCaseOfficeFlag(),
        office.getLargeTaxpayerOfficeFlag(), office.getDescription(),
        office.getRestrictRegAddFlag(), office.getOfficeType());
    this.officeDesignation = officeDesignation;
    this.officeSectionHead = officeSectionHead;
    this.caseOfficer = caseOfficer;
    this.groupSupervisor = groupSupervisor;
    this.caseTypes = caseTypes;
    this.username = username;
  }

  public StaffCaseAccess(Long id, String username, MaintOfficeDesignation officeDesignation,
      boolean officeSectionHead, boolean caseOfficer) {
    super();
    this.id = id;
    this.officeDesignation = officeDesignation;
    this.username = username;
    this.officeSectionHead = officeSectionHead;
    this.caseOfficer = caseOfficer;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public List<MaintCaseType> getCaseTypes() {
    if (!ObjectUtils.isEmpty(caseTypes)) {

      Iterator<MaintCaseType> caseTypeIT = caseTypes.iterator();
      List<MaintCaseType> reducedProps = new ArrayList<>();
      while (caseTypeIT.hasNext()) {
        MaintCaseType originalCaseType = caseTypeIT.next();
        MaintCaseType caseType = new MaintCaseType(originalCaseType.getId(),
            originalCaseType.getCode(), originalCaseType.getDescription(),
            originalCaseType.getCategory(), originalCaseType.getNoticeType());
        reducedProps.add(caseType);
      }
      return reducedProps;

    } else {
      return null;
    }
  }

  public void setCaseTypes(List<MaintCaseType> caseTypes) {
    this.caseTypes = caseTypes;
  }

  public Office getOffice() {
    if (!ObjectUtils.isEmpty(office)) {
      return new Office(office.getId(), office.getCode(), office.getCaseOfficeFlag(),
          office.getLargeTaxpayerOfficeFlag(), office.getDescription(),
          office.getRestrictRegAddFlag(), office.getOfficeType());
    } else {
      return null;
    }
  }

  public void setOffice(Office office) {
    this.office = office;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public MaintOfficeDesignation getOfficeDesignation() {
    return officeDesignation;
  }

  public void setOfficeDesignation(MaintOfficeDesignation officeDesignation) {
    this.officeDesignation = officeDesignation;
  }

  public boolean isOfficeSectionHead() {
    return officeSectionHead;
  }

  public void setOfficeSectionHead(boolean officeSectionHead) {
    this.officeSectionHead = officeSectionHead;
  }

  public boolean isCaseOfficer() {
    return caseOfficer;
  }

  public void setCaseOfficer(boolean caseOfficer) {
    this.caseOfficer = caseOfficer;
  }

  public String getGroupSupervisor() {
    return groupSupervisor;
  }

  public void setGroupSupervisor(String groupSupervisor) {
    this.groupSupervisor = groupSupervisor;
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

  public ReferenceOfficeLtsGroup getOfficeLtsGroup() {
    if (ObjectUtils.isEmpty(officeLtsGroup)) {
      return officeLtsGroup;
    }
    return new ReferenceOfficeLtsGroup(officeLtsGroup.getId(), officeLtsGroup.getCode(),
        officeLtsGroup.getDescription());
  }

  public void setOfficeLtsGroup(ReferenceOfficeLtsGroup officeLtsGroup) {
    this.officeLtsGroup = officeLtsGroup;
  }

  public MaintOfficeService getOfficeService() {
    if (ObjectUtils.isEmpty(officeService)) {
      return officeService;
    }
    return new MaintOfficeService(officeService.getId(), officeService.getCode(),
        officeService.getDescription());
  }

  public void setOfficeService(MaintOfficeService officeService) {
    this.officeService = officeService;
  }

  public MaintOfficeDivision getOfficeDivision() {
    if (ObjectUtils.isEmpty(officeDivision)) {
      return officeDivision;
    }
    return new MaintOfficeDivision(officeDivision.getId(), officeDivision.getCode(),
        officeDivision.getDescription(), officeDivision.getIsLargeTaxpayerOffice());
  }

  public void setOfficeDivision(MaintOfficeDivision officeDivision) {
    this.officeDivision = officeDivision;
  }

  public MaintOfficeSection getOfficeSection() {
    if (ObjectUtils.isEmpty(officeSection)) {
      return officeSection;
    }
    return new MaintOfficeSection(officeSection.getId(), officeSection.getCode(),
        officeSection.getDescription(), officeSection.getIsLargeTaxpayerOffice());
  }

  public void setOfficeSection(MaintOfficeSection officeSection) {
    this.officeSection = officeSection;
  }

  public User getUserDetails() {
    return userDetails;
  }

  public void setUserDetails(User userDetails) {
    this.userDetails = userDetails;
  }

  @Override
  public String toString() {
    return "StaffCaseAccess [id=" + id + ", office=" + office + ", officeGroup=" + officeGroup
        + ", officeLtsGroup=" + officeLtsGroup + ", officeService=" + officeService
        + ", officeDivision=" + officeDivision + ", officeSection=" + officeSection
        + ", officeDesignation=" + officeDesignation + ", officeSectionHead=" + officeSectionHead
        + ", caseOfficer=" + caseOfficer + ", groupSupervisor=" + groupSupervisor + ", caseTypes="
        + caseTypes + ", username=" + username + ", createdBy=" + createdBy + ", createdDate="
        + createdDate + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate
        + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
  }

}
