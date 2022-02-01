/*
  * Modified by: logronj
  * Last updated: 08 5, 20 7:53:41 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.*;
import com.caista.birapps.etis.domain.sysad.entity.reference.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name = "OFFICE_TCC_SERIAL_DISTRIBUTED",
    uniqueConstraints = @UniqueConstraint(columnNames = {"OFFICE_ID", "OFFICE_DIVISION_ID",
        "TCC_SERIAL_MAIN_ID", "TCC_RANGE_FROM", "TCC_RANGE_TO"},
        name = "UC_OFFICE_TCC_SERIAL_DISTRIBUTED_01"),
    indexes = {@Index(columnList = "OFFICE_ID", name = "idx_OFFICE_TCC_SERIAL_DISTRIBUTED_01"),
        @Index(columnList = "TCC_SERIAL_MAIN_ID", name = "idx_OFFICE_TCC_SERIAL_DISTRIBUTED_02")})
@JsonInclude(Include.NON_NULL)
public class OfficeTccSerialNumberDistributed implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "OFFICE_TCC_SERIAL_DISTRIBUTED_SequenceStyleGenerator")
  @GenericGenerator(name = "OFFICE_TCC_SERIAL_DISTRIBUTED_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_OFFICE_TCC_SERIAL_DISTRIBUTED"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "SERIAL_RDO_ID", nullable = false, updatable = false)
  private Long id;

  /** The office serial number RR. */
  @ManyToOne
  @JoinColumn(name = "TCC_SERIAL_MAIN_ID", nullable = false, updatable = false,
      foreignKey = @ForeignKey(
          name = "FK_OFFICE_TCC_SERIAL_MAIN__OFFICE_TCC_SERIAL_DISTRIBUTED_01"))
  private OfficeTccSerialNumberMain tccSerialNumberMain;

  /** The ref office. */
  @ManyToOne
  @JoinColumn(name = "OFFICE_ID",
      foreignKey = @ForeignKey(name = "FK_OFFICE__OFFICE_TCC_SERIAL_DISTRIBUTED_01"))
  private Office subOffice;


  @ManyToOne
  @JoinColumn(name = "OFFICE_GROUP_ID",
      foreignKey = @ForeignKey(name = "FK_REF_OFFICE_GROUP__OFFICE_TCC_SERIAL_DISTRIBUTED_01"))
  private ReferenceOfficeGroup officeGroup;

  @ManyToOne
  @JoinColumn(name = "OFFICE_SERVICE_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_SERVICE__OFFICE_TCC_SERIAL_DISTRIBUTED_01"))
  private MaintOfficeService officeService;

  @ManyToOne
  @JoinColumn(name = "OFFICE_LTS_GROUP_ID",
      foreignKey = @ForeignKey(name = "FK_REF_OFFICE_LTS_GROUP__OFFICE_TCC_SERIAL_DISTRIBUTED_01"))
  private ReferenceOfficeLtsGroup officeLtsGroup;

  @ManyToOne
  @JoinColumn(name = "OFFICE_DIVISION_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_DIVISION__OFFICE_TCC_SERIAL_DISTRIBUTED_01"))
  private MaintOfficeDivision officeDivision;


  @ManyToOne
  @JoinColumn(name = "OFFICE_SECTION_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_SECTION__OFFICE_TCC_SERIAL_DISTRIBUTED_01"))
  private MaintOfficeSection officeSection;

  /** The range from. */
  @Column(name = "TCC_RANGE_FROM", columnDefinition = "NUMBER(8,0)", nullable = false)
  private Long rangeFrom;

  /** The range to. */
  @Column(name = "TCC_RANGE_TO", columnDefinition = "NUMBER(8,0)", nullable = false)
  private Long rangeTo;

  /** The regional director. */
  @Column(name = "OFFICER_NAME", columnDefinition = "VARCHAR(50)", nullable = false)
  private String officerName;

  /** The used tcc max. */
  @Column(name = "MAX_USED_NUMBERS", columnDefinition = "NUMBER(8,0)")
  private Long usedTccMax;

  /** The used tcc count. */
  @Column(name = "AVAILABLE_TCC_NUMBERS", columnDefinition = "NUMBER(8,0)")
  private Long availableTcc;

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

  /** The updated date. */
  @Column(name = "UPDATED_DATE", columnDefinition = "TIMESTAMP(6)")
  private Date updatedDate;

  /** The used tcc min. */
  @Column(name = "MIN_USED_NUMBERS", columnDefinition = "NUMBER(8,0)")
  private Long usedTccMin;


  public OfficeTccSerialNumberDistributed() {
    super();
  }

  public OfficeTccSerialNumberDistributed(Long id, Long rangeFrom, Long rangeTo, String officerName,
      Long usedTccMax, Long availableTcc, Long usedTccMin, OfficeTccSerialNumberMain tccSerialNumberMain,
      Office subOffice, MaintOfficeDivision officeDivision) {
    super();
    this.id = id;
    this.rangeFrom = rangeFrom;
    this.rangeTo = rangeTo;
    this.officerName = officerName;
    this.usedTccMax = usedTccMax;
    this.availableTcc = availableTcc;
    this.usedTccMin = usedTccMin;
    this.tccSerialNumberMain = new OfficeTccSerialNumberMain(tccSerialNumberMain.getId());
    if (subOffice != null)
      this.subOffice = new Office(subOffice.getId(), subOffice.getCode(),
          subOffice.getCaseOfficeFlag(), subOffice.getLargeTaxpayerOfficeFlag(),
          subOffice.getDescription(), subOffice.getRestrictRegAddFlag());
    if (officeDivision != null)
      this.officeDivision = new MaintOfficeDivision(officeDivision.getId(),
          officeDivision.getCode(), officeDivision.getDescription());
    if (officeSection != null)
      this.officeSection = new MaintOfficeSection(officeSection.getId(), officeSection.getCode(),
          officeSection.getDescription());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OfficeTccSerialNumberMain getTccSerialNumberMain() {
    return tccSerialNumberMain;
  }

  public void setTccSerialNumberMain(OfficeTccSerialNumberMain tccSerialNumberMain) {
    this.tccSerialNumberMain = tccSerialNumberMain;
  }

  public Office getSubOffice() {
    return subOffice;
  }

  public void setSubOffice(Office subOffice) {
    this.subOffice = subOffice;
  }

  public MaintOfficeDivision getOfficeDivision() {
    return officeDivision;
  }

  public void setOfficeDivision(MaintOfficeDivision officeDivision) {
    this.officeDivision = officeDivision;
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

  public Long getUsedTccMax() {
    return usedTccMax;
  }

  public void setUsedTccMax(Long usedTccMax) {
    this.usedTccMax = usedTccMax;
  }

  public Long getAvailableTcc() {
    return availableTcc;
  }

  public void setAvailableTcc(Long availableTcc) {
    this.availableTcc = availableTcc;
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

  public Long getUsedTccMin() {
    return usedTccMin;
  }

  public void setUsedTccMin(Long usedTccMin) {
    this.usedTccMin = usedTccMin;
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

  public ReferenceOfficeLtsGroup getOfficeLtsGroup() {
    return officeLtsGroup;
  }

  public void setOfficeLtsGroup(ReferenceOfficeLtsGroup officeLtsGroup) {
    this.officeLtsGroup = officeLtsGroup;
  }

  public MaintOfficeSection getOfficeSection() {
    return officeSection;
  }

  public void setOfficeSection(MaintOfficeSection officeSection) {
    this.officeSection = officeSection;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("OfficeTccSerialNumberDistributed [id=");
    builder.append(id);
    builder.append(", tccSerialNumberMain=");
    builder.append(tccSerialNumberMain);
    builder.append(", subOffice=");
    builder.append(subOffice);
    builder.append(", officeGroup=");
    builder.append(officeGroup);
    builder.append(", officeService=");
    builder.append(officeService);
    builder.append(", officeLtsGroup=");
    builder.append(officeLtsGroup);
    builder.append(", officeDivision=");
    builder.append(officeDivision);
    builder.append(", officeSection=");
    builder.append(officeSection);
    builder.append(", rangeFrom=");
    builder.append(rangeFrom);
    builder.append(", rangeTo=");
    builder.append(rangeTo);
    builder.append(", officerName=");
    builder.append(officerName);
    builder.append(", usedTccMax=");
    builder.append(usedTccMax);
    builder.append(", availableTcc=");
    builder.append(availableTcc);
    builder.append(", createdBy=");
    builder.append(createdBy);
    builder.append(", createdDate=");
    builder.append(createdDate);
    builder.append(", updatedBy=");
    builder.append(updatedBy);
    builder.append(", updatedDate=");
    builder.append(updatedDate);
    builder.append(", usedTccMin=");
    builder.append(usedTccMin);
    builder.append("]");
    return builder.toString();
  }




}

