/*
  * Modified by: logronj
  * Last updated: 08 6, 20 4:39:09 PM
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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name = "OFFICE_TCC_SERIAL_MAIN",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"OFFICE_ID", "TCC_YEAR", "TCC_RANGE_FROM", "TCC_RANGE_TO"},
        name = "UC_OFFICE_TCC_SERIAL_MAIN_01"),
    indexes = {@Index(columnList = "OFFICE_ID", name = "idx_OFFICE_TCC_SERIAL_MAIN_01")})

@JsonInclude(Include.NON_NULL)
public class OfficeTccSerialNumberMain implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "OFFICE_TCC_SERIAL_MAIN_SequenceStyleGenerator")
  @GenericGenerator(name = "OFFICE_TCC_SERIAL_MAIN_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_OFFICE_TCC_SERIAL_MAIN"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TCC_SERIAL_MAIN_ID", nullable = false, updatable = false)
  private Long id;

  /** The ref office. */
  @ManyToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false, updatable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__OFFICE_TCC_SERIAL_MAIN_01"))
  private Office office;

  /** The year. */
  @Column(name = "TCC_YEAR", columnDefinition = "NUMBER(4,0)", nullable = false)
  private Integer year;

  /** The range from. */
  @Column(name = "TCC_RANGE_FROM", columnDefinition = "NUMBER(8,0)", nullable = false)
  private Long rangeFrom;

  /** The range to. */
  @Column(name = "TCC_RANGE_TO", columnDefinition = "NUMBER(8,0)", nullable = false)
  private Long rangeTo;

  /** The date received. */
  @Column(name = "DATE_RECEIVED", columnDefinition = "DATE", nullable = false)
  private Date dateReceived;

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

  /** The used tcc count. */
  @Column(name = "USED_TCC_COUNT", columnDefinition = "NUMBER(8,0)")
  private Long usedTccCount;

  /** The used tcc min. */
  @Column(name = "USED_TCC_MIN", columnDefinition = "NUMBER(8,0)")
  private Long usedTccMin;

  /** The used tcc max. */
  @Column(name = "USED_TCC_MAX", columnDefinition = "NUMBER(8,0)")
  private Long usedTccMax;

  @Transient
  private Long tempId;

  public OfficeTccSerialNumberMain() {
    super();
  }

  public OfficeTccSerialNumberMain(Long id) {
    super();
    this.id = id;
  }

  public OfficeTccSerialNumberMain(Long id, Integer year, Long rangeFrom, Long rangeTo,
      Date dateReceived) {
    super();
    this.id = id;
    this.year = year;
    this.rangeFrom = rangeFrom;
    this.rangeTo = rangeTo;
    this.dateReceived = dateReceived;
  }


  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }

  public Office getOffice() {
    return new Office(office.getId(), office.getCode(), office.getOfficeType(),
        office.getCaseOfficeFlag(), office.getLargeTaxpayerOfficeFlag(), office.getDescription(),
        office.getRestrictRegAddFlag());
  }

  public void setOffice(Office office) {
    this.office = office;
  }


  public Integer getYear() {
    return year;
  }


  public void setYear(Integer year) {
    this.year = year;
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


  public Date getDateReceived() {
    return dateReceived;
  }


  public void setDateReceived(Date dateReceived) {
    this.dateReceived = dateReceived;
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


  public Long getUsedTccCount() {
    return usedTccCount;
  }


  public void setUsedTccCount(Long usedTccCount) {
    this.usedTccCount = usedTccCount;
  }


  public Long getUsedTccMin() {
    return usedTccMin;
  }


  public void setUsedTccMin(Long usedTccMin) {
    this.usedTccMin = usedTccMin;
  }


  public Long getUsedTccMax() {
    return usedTccMax;
  }


  public void setUsedTccMax(Long usedTccMax) {
    this.usedTccMax = usedTccMax;
  }


  public Long getTempId() {
    if (ObjectUtils.isEmpty(tempId)) {
      return id;
    }
    return tempId;
  }

  public void setTempId(Long tempId) {
    this.tempId = tempId;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "OfficeTccSerialNumberMain [id=" + id + ", office=" + office + ", year=" + year
        + ", rangeFrom=" + rangeFrom + ", rangeTo=" + rangeTo + ", dateReceived=" + dateReceived
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
        + ", updatedDate=" + updatedDate + ", usedTccCount=" + usedTccCount + ", usedTccMin="
        + usedTccMin + ", usedTccMax=" + usedTccMax + ", tempId=" + tempId + "]";
  }



}
