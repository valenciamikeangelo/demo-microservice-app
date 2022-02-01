/*
  * Modified by: logronj
  * Last updated: 08 6, 20 4:21:13 PM
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
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceOfficeGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "OFFICE_TDM_NUMBER_DISTRIBUTED",
    uniqueConstraints = @UniqueConstraint(columnNames = {"OFFICE_ID", "OFFICE_DIVISION_ID",
        "TDM_NUMBER_ID", "RANGE_FROM", "RANGE_TO"}, name = "UC_OFFICE_TDM_NUMBER_DISTRIBUTED_01"),
    indexes = {@Index(columnList = "OFFICE_ID", name = "idx_OFFICE_TDM_NUMBER_DISTRIBUTED_01"),
        @Index(columnList = "TDM_NUMBER_ID", name = "idx_OFFICE_TDM_NUMBER_DISTRIBUTED_02")})
@JsonInclude(Include.NON_NULL)
public class OfficeTdmSerialNumberDistributed implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "OFFICE_TDM_NUMBER_DISTRIBUTED_SequenceStyleGenerator")
  @GenericGenerator(name = "OFFICE_TDM_NUMBER_DISTRIBUTED_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_OFFICE_TDM_NUMBER_DISTRIBUTED"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TDM_NUMBER_DISTRIBUTED_ID", nullable = false, updatable = false)
  private Long id;

  /** The office serial number RR. */
  @ManyToOne
  @JoinColumn(name = "TDM_NUMBER_ID", nullable = false, updatable = false, foreignKey = @ForeignKey(
      name = "FK_OFFICE_TDM_SERIAL_MAIN__OFFICE_TDM_NUMBER_DISTRIBUTED_01"))
  private OfficeTdmSerialNumberMain tdmSerialNumberMain;

  /** The ref office. */
  @ManyToOne
  @JoinColumn(name = "OFFICE_ID",
      foreignKey = @ForeignKey(name = "FK_OFFICE__OFFICE_TDM_NUMBER_DISTRIBUTED_01"))
  private Office subOffice;

  @ManyToOne
  @JoinColumn(name = "OFFICE_GROUP_ID",
      foreignKey = @ForeignKey(name = "FK_REF_OFFICE_GROUP__OFFICE_TDM_NUMBER_DISTRIBUTED_01"))
  private ReferenceOfficeGroup officeGroup;

  @ManyToOne
  @JoinColumn(name = "OFFICE_SERVICE_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_SERVICE__OFFICE_TDM_NUMBER_DISTRIBUTED_01"))
  private MaintOfficeService officeService;

  @ManyToOne
  @JoinColumn(name = "OFFICE_DIVISION_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_DIVISION__OFFICE_TDM_NUMBER_DISTRIBUTED_01"))
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
  private Long usedTdmMax;

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
  private Long usedTdmMin;


  public OfficeTdmSerialNumberDistributed() {
    super();
  }

  public OfficeTdmSerialNumberDistributed(Long id, Long rangeFrom, Long rangeTo, String officerName,
      Long usedTdmMax, Long availableNumbers, Long usedTdmMin,
      OfficeTdmSerialNumberMain tdmSerialNumberMain, Office subOffice,
      MaintOfficeDivision officeDivision) {
    super();
    this.id = id;
    this.rangeFrom = rangeFrom;
    this.rangeTo = rangeTo;
    this.officerName = officerName;
    this.usedTdmMax = usedTdmMax;
    this.availableNumbers = availableNumbers;
    this.usedTdmMin = usedTdmMin;
    this.tdmSerialNumberMain = new OfficeTdmSerialNumberMain(tdmSerialNumberMain.getId());
    if (subOffice != null)
      this.subOffice = new Office(subOffice.getId(), subOffice.getCode(),
          subOffice.getCaseOfficeFlag(), subOffice.getLargeTaxpayerOfficeFlag(),
          subOffice.getDescription(), subOffice.getRestrictRegAddFlag());
    if (officeDivision != null)
      this.officeDivision = new MaintOfficeDivision(officeDivision.getId(),
          officeDivision.getCode(), officeDivision.getDescription());
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



  public Office getSubOffice() {
    return subOffice;
  }


  public void setSubOffice(Office subOffice) {
    this.subOffice = subOffice;
  }


  public OfficeTdmSerialNumberMain getTdmSerialNumberMain() {
    return tdmSerialNumberMain;
  }

  public void setTdmSerialNumberMain(OfficeTdmSerialNumberMain tdmSerialNumberMain) {
    this.tdmSerialNumberMain = tdmSerialNumberMain;
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

  public Long getUsedTdmMax() {
    return usedTdmMax;
  }

  public void setUsedTdmMax(Long usedTdmMax) {
    this.usedTdmMax = usedTdmMax;
  }

  public Long getUsedTdmMin() {
    return usedTdmMin;
  }

  public void setUsedTdmMin(Long usedTdmMin) {
    this.usedTdmMin = usedTdmMin;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("OfficeTdmSerialNumberDistributed [id=");
    builder.append(id);
    builder.append(", tdmSerialNumberMain=");
    builder.append(tdmSerialNumberMain);
    builder.append(", subOffice=");
    builder.append(subOffice);
    builder.append(", officeGroup=");
    builder.append(officeGroup);
    builder.append(", officeService=");
    builder.append(officeService);
    builder.append(", officeDivision=");
    builder.append(officeDivision);
    builder.append(", rangeFrom=");
    builder.append(rangeFrom);
    builder.append(", rangeTo=");
    builder.append(rangeTo);
    builder.append(", officerName=");
    builder.append(officerName);
    builder.append(", usedTdmMax=");
    builder.append(usedTdmMax);
    builder.append(", availableNumbers=");
    builder.append(availableNumbers);
    builder.append(", createdBy=");
    builder.append(createdBy);
    builder.append(", createdDate=");
    builder.append(createdDate);
    builder.append(", updatedBy=");
    builder.append(updatedBy);
    builder.append(", updatedDate=");
    builder.append(updatedDate);
    builder.append(", usedTdmMin=");
    builder.append(usedTdmMin);
    builder.append("]");
    return builder.toString();
  }



}
