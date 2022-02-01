/*
  * Modified by: obregoj
  * Last updated: 01 3, 20 12:25:13 PM
  */
package com.caista.birapps.etis.domain.sysad.entity;

import com.caista.birapps.etis.domain.sysad.entity.maintenance.*;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceOfficeGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "OFFICE_TVN_NUMBER_DISTRIBUTED",
    uniqueConstraints = @UniqueConstraint(columnNames = {"OFFICE_ID", "OFFICE_DIVISION_ID",
        "TVN_NUMBER_ID", "RANGE_FROM", "RANGE_TO"}, name = "UC_OFFICE_TVN_NUMBER_DISTRIBUTED_01"),
    indexes = {@Index(columnList = "OFFICE_ID", name = "idx_OFFICE_TVN_NUMBER_DISTRIBUTED_01"),
        @Index(columnList = "TVN_NUMBER_ID", name = "idx_OFFICE_TVN_NUMBER_DISTRIBUTED_02")})
@JsonInclude(Include.NON_NULL)
public class OfficeTvnNumberDistributed implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "OFFICE_TVN_NUMBER_DISTRIBUTED_SequenceStyleGenerator")
  @GenericGenerator(name = "OFFICE_TVN_NUMBER_DISTRIBUTED_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_OFFICE_TVN_NUMBER_DISTRIBUTED"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TVN_NUMBER_DISTRIBUTED_ID", nullable = false, updatable = false)
  private Long id;

  /** The office serial number RR. */
  @ManyToOne
  @JoinColumn(name = "TVN_NUMBER_ID", nullable = false, updatable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE_LA_SERIAL_MAIN__OFFICE_TVN_NUMBER_DISTRIBUTED_01"))
  private OfficeTvnNumberMain officeTvnNumberMain;

  /** The ref office. */
  @ManyToOne
  @JoinColumn(name = "OFFICE_ID",
      foreignKey = @ForeignKey(name = "FK_OFFICE__OFFICE_TVN_NUMBER_DISTRIBUTED_01"),
      nullable = false)
  private Office subOffice;

  @ManyToOne
  @JoinColumn(name = "OFFICE_GROUP_ID",
      foreignKey = @ForeignKey(name = "FK_REF_OFFICE_GROUP__OFFICE_TVN_NUMBER_DISTRIBUTED_01"))
  private ReferenceOfficeGroup officeGroup;

  @ManyToOne
  @JoinColumn(name = "OFFICE_SERVICE_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_SERVICE__OFFICE_TVN_NUMBER_DISTRIBUTED_01"))
  private MaintOfficeService officeService;

  @ManyToOne
  @JoinColumn(name = "OFFICE_DIVISION_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_DIVISION__OFFICE_TVN_NUMBER_DISTRIBUTED_01"))
  private MaintOfficeDivision officeDivision;

  /** The range from. */
  @Column(name = "RANGE_FROM", columnDefinition = "NUMBER(8,0)", nullable = false)
  private Long rangeFrom;

  /** The range to. */
  @Column(name = "RANGE_TO", columnDefinition = "NUMBER(8,0)", nullable = false)
  private Long rangeTo;

  /** The regional director. */
  @Column(name = "OFFICER_NAME", columnDefinition = "VARCHAR(50)", nullable = false)
  private String officerName;

  /** The used la max. */
  @Column(name = "MAX_USED_NUMBERS", columnDefinition = "NUMBER(8,0)")
  private Long usedLaMax;

  /** The used la count. */
  @Column(name = "AVAILABLE_NUMBERS", columnDefinition = "NUMBER(8,0)")
  private Long availableNumbers;

  /** The created by. */
  @Column(name = "CREATED_BY", updatable = false, nullable = false,
      columnDefinition = "VARCHAR(20)")
  private String createdBy;

  /** The created date. */
  @Column(name = "CREATED_DATE", updatable = false, nullable = false,
      columnDefinition = "TIMESTAMP(6)")
  private Date createdDate;

  /** The updated by. */
  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE", columnDefinition = "TIMESTAMP(6)")
  private Date updatedDate;

  @Column(name = "MIN_USED_NUMBERS", columnDefinition = "NUMBER(8,0)")
  private Long usedLaMin;


  public OfficeTvnNumberDistributed() {
    super();
  }

  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public Long getRangeFrom() {
    return rangeFrom;
  }


  public void setRangeFrom(Long rangeFrom) {
    this.rangeFrom = rangeFrom;
  }


  public Long getRangeTo() {
    return rangeTo;
  }


  public void setRangeTo(Long rangeTo) {
    this.rangeTo = rangeTo;
  }

  public String getOfficerName() {
    return officerName;
  }


  public void setOfficerName(String officerName) {
    this.officerName = officerName;
  }

  public Long getUsedLaMax() {
    return usedLaMax;
  }


  public void setUsedLaMax(Long usedLaMax) {
    this.usedLaMax = usedLaMax;
  }


  public Long getAvailableNumbers() {
    return availableNumbers;
  }

  public void setAvailableNumbers(Long availableNumbers) {
    this.availableNumbers = availableNumbers;
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



  public Long getUsedLaMin() {
    return usedLaMin;
  }


  public void setUsedLaMin(Long usedLaMin) {
    this.usedLaMin = usedLaMin;
  }


  public Office getSubOffice() {
    return subOffice;
  }


  public void setSubOffice(Office subOffice) {
    this.subOffice = subOffice;
  }


  public OfficeTvnNumberMain getOfficeTvnNumberMain() {
    return officeTvnNumberMain;
  }


  public void setOfficeTvnNumberMain(OfficeTvnNumberMain officeTvnNumberMain) {
    this.officeTvnNumberMain = officeTvnNumberMain;
  }


  public MaintOfficeDivision getOfficeDivision() {
    return officeDivision;
  }


  public void setOfficeDivision(MaintOfficeDivision officeDivision) {
    this.officeDivision = officeDivision;
  }


  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public ReferenceOfficeGroup getOfficeGroup() {
    return officeGroup;
  }


  public void setOfficeGroup(ReferenceOfficeGroup officeGroup) {
    this.officeGroup = officeGroup;
  }


  public MaintOfficeService getOfficeService() {
    return officeService;
  }


  public void setOfficeService(MaintOfficeService officeService) {
    this.officeService = officeService;
  }


  @Override
  public String toString() {
    return "OfficeTvnNumberDistributed [id=" + id + ", officeTvnNumberMain=" + officeTvnNumberMain
        + ", subOffice=" + subOffice + ", officeGroup=" + officeGroup + ", officeService="
        + officeService + ", officeDivision=" + officeDivision + ", rangeFrom=" + rangeFrom
        + ", rangeTo=" + rangeTo + ", officerName=" + officerName + ", usedLaMax=" + usedLaMax
        + ", availableNumbers=" + availableNumbers + ", createdBy=" + createdBy + ", createdDate="
        + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", usedLaMin="
        + usedLaMin + "]";
  }

}
