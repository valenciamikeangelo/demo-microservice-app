/*
  * Modified by: obregoj
  * Last updated: 04 14, 20 1:48:09 PM
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

// TODO: Auto-generated Javadoc
/**
 * The Class OfficeSerialNumberRDO.
 */
@Entity
@Table(name = "OFFICE_LA_SERIAL_DISTRIBUTED",
    uniqueConstraints = @UniqueConstraint(columnNames = {"OFFICE_ID", "OFFICE_DIVISION_ID",
        "SERIAL_MAIN_ID", "LA_RANGE_FROM", "LA_RANGE_TO"},
        name = "UC_OFFICE_LA_SERIAL_DISTRIBUTED_01"),
    indexes = {@Index(columnList = "OFFICE_ID", name = "idx_OFFICE_LA_SERIAL_DISTRIBUTED_01"),
        @Index(columnList = "SERIAL_MAIN_ID", name = "idx_OFFICE_LA_SERIAL_DISTRIBUTED_02")})
@JsonInclude(Include.NON_NULL)
public class OfficeSerialNumberDistributed implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "OFFICE_LA_SERIAL_DISTRIBUTED_SequenceStyleGenerator")
  @GenericGenerator(name = "OFFICE_LA_SERIAL_DISTRIBUTED_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_OFFICE_LA_SERIAL_DISTRIBUTED"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "SERIAL_RDO_ID", nullable = false, updatable = false)
  private Long id;

  /** The office serial number RR. */
  @ManyToOne
  @JoinColumn(name = "SERIAL_MAIN_ID", nullable = false, updatable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE_LA_SERIAL_MAIN__OFFICE_LA_SERIAL_DISTRIBUTED_01"))
  private OfficeSerialNumberMain officeSerialNumberMain;

  /** The ref office. */
  @ManyToOne
  @JoinColumn(name = "OFFICE_ID",
      foreignKey = @ForeignKey(name = "FK_OFFICE__OFFICE_LA_SERIAL_DISTRIBUTED_01"),
      nullable = false)
  private Office subOffice;

  @ManyToOne
  @JoinColumn(name = "OFFICE_GROUP_ID",
      foreignKey = @ForeignKey(name = "FK_REF_OFFICE_GROUP__OFFICE_LA_SERIAL_DISTRIBUTED_01"))
  private ReferenceOfficeGroup officeGroup;

  @ManyToOne
  @JoinColumn(name = "OFFICE_SERVICE_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_SERVICE__OFFICE_LA_SERIAL_DISTRIBUTED_01"))
  private MaintOfficeService officeService;

  @ManyToOne
  @JoinColumn(name = "OFFICE_LTS_GROUP_ID",
      foreignKey = @ForeignKey(name = "FK_REF_OFFICE_LTS_GROUP__OFFICE_LA_SERIAL_DISTRIBUTED_01"))
  private ReferenceOfficeLtsGroup officeLtsGroup;

  @ManyToOne
  @JoinColumn(name = "OFFICE_DIVISION_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_DIVISION__OFFICE_LA_SERIAL_DISTRIBUTED_01"))
  private MaintOfficeDivision officeDivision;

  @ManyToOne
  @JoinColumn(name = "OFFICE_SECTION_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_OFFICE_SECTION__OFFICE_LA_SERIAL_DISTRIBUTED_01"))
  private MaintOfficeSection officeSection;

  /** The range from. */
  @Column(name = "LA_RANGE_FROM", columnDefinition = "NUMBER(8,0)", nullable = false)
  private Long rangeFrom;

  /** The range to. */
  @Column(name = "LA_RANGE_TO", columnDefinition = "NUMBER(8,0)", nullable = false)
  private Long rangeTo;

  /** The regional director. */
  @Column(name = "OFFICER_NAME", columnDefinition = "VARCHAR(50)", nullable = false)
  private String officerName;

  /** The used la max. */
  @Column(name = "MAX_USED_NUMBERS", columnDefinition = "NUMBER(8,0)")
  private Long usedLaMax;

  /** The used la count. */
  @Column(name = "AVAILABLE_LA_NUMBERS", columnDefinition = "NUMBER(8,0)")
  private Long availableLa;

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

  /** The used la min. */
  @Column(name = "MIN_USED_NUMBERS", columnDefinition = "NUMBER(8,0)")
  private Long usedLaMin;



  /**
   * Instantiates a new office serial number RDO.
   */
  public OfficeSerialNumberDistributed() {
    super();
  }


  /**
   * Instantiates a new office serial number RDO.
   *
   * @param id the serial number rdo id
   * @param rangeFrom the range from
   * @param rangeTo the range to
   * @param usedLaMax the used la max
   * @param usedLaCount the used la count
   * @param usedLaMin the used la min
   * @param officeSerialNumberRR the office serial number RR
   * @param subOffice the sub office
   */
  public OfficeSerialNumberDistributed(Long id, Long rangeFrom, Long rangeTo, String officerName,
      Long usedLaMax, Long availableLa, Long usedLaMin, OfficeSerialNumberMain officeSerialNumberRR,
      Office subOffice, MaintOfficeDivision officeDivision, MaintOfficeSection officeSection) {
    super();
    this.id = id;
    this.rangeFrom = rangeFrom;
    this.rangeTo = rangeTo;
    this.officerName = officerName;
    this.usedLaMax = usedLaMax;
    this.availableLa = availableLa;
    this.usedLaMin = usedLaMin;
    this.officeSerialNumberMain = new OfficeSerialNumberMain(officeSerialNumberRR.getId());
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


  public Long getAvailableLa() {
    return availableLa;
  }


  public void setAvailableLa(Long availableLa) {
    this.availableLa = availableLa;
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


  public OfficeSerialNumberMain getOfficeSerialNumberMain() {
    return officeSerialNumberMain;
  }


  public void setOfficeSerialNumberMain(OfficeSerialNumberMain officeSerialNumberMain) {
    this.officeSerialNumberMain = officeSerialNumberMain;
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


  public MaintOfficeSection getOfficeSection() {
    return officeSection;
  }


  public void setOfficeSection(MaintOfficeSection officeSection) {
    this.officeSection = officeSection;
  }


  public ReferenceOfficeLtsGroup getOfficeLtsGroup() {
    return officeLtsGroup;
  }


  public void setOfficeLtsGroup(ReferenceOfficeLtsGroup officeLtsGroup) {
    this.officeLtsGroup = officeLtsGroup;
  }


  @Override
  public String toString() {
    return "OfficeSerialNumberDistributed [id=" + id + ", officeSerialNumberMain="
        + officeSerialNumberMain + ", subOffice=" + subOffice + ", officeGroup=" + officeGroup
        + ", officeService=" + officeService + ", officeLtsGroup=" + officeLtsGroup
        + ", officeDivision=" + officeDivision + ", officeSection=" + officeSection + ", rangeFrom="
        + rangeFrom + ", rangeTo=" + rangeTo + ", officerName=" + officerName + ", usedLaMax="
        + usedLaMax + ", availableLa=" + availableLa + ", createdBy=" + createdBy + ", createdDate="
        + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", usedLaMin="
        + usedLaMin + "]";
  }

}
