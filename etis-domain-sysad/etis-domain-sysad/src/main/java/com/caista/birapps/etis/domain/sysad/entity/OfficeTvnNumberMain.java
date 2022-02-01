/*
  * Modified by: obregoj
  * Last updated: 01 9, 20 9:29:52 AM
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
@Table(name = "OFFICE_TVN_NUMBER_MAIN",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"OFFICE_ID", "YEAR", "RANGE_FROM", "RANGE_TO"},
        name = "UC_OFFICE_TVN_NUMBER_MAIN_01"),
    indexes = {@Index(columnList = "OFFICE_ID", name = "idx_OFFICE_TVN_NUMBER_MAIN_01")})

@JsonInclude(Include.NON_NULL)
public class OfficeTvnNumberMain implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "OFFICE_TVN_NUMBER_MAIN_SequenceStyleGenerator")
  @GenericGenerator(name = "OFFICE_TVN_NUMBER_MAIN_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_OFFICE_TVN_NUMBER_MAIN"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TVN_NUMBER_ID", nullable = false, updatable = false)
  private Long id;

  /** The ref office. */
  @ManyToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false, updatable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__OFFICE_TVN_NUMBER_MAIN_01"))
  private Office office;

  /** The year. */
  @Column(name = "YEAR", columnDefinition = "NUMBER(4,0)", nullable = false)
  private Integer year;

  /** The range from. */
  @Column(name = "RANGE_FROM", columnDefinition = "NUMBER(8,0)", nullable = false)
  private Long rangeFrom;

  /** The range to. */
  @Column(name = "RANGE_TO", columnDefinition = "NUMBER(8,0)", nullable = false)
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

  /** The used la count. */
  @Column(name = "USED_TVN_COUNT", columnDefinition = "NUMBER(8,0)")
  private Long usedTvnCount;

  /** The used la min. */
  @Column(name = "USED_TVN_MIN", columnDefinition = "NUMBER(8,0)")
  private Long usedTvnMin;

  /** The used la max. */
  @Column(name = "USED_TVN_MAX", columnDefinition = "NUMBER(8,0)")
  private Long usedTvnMax;

  @Transient
  private Long tempId;

  /**
   * Instantiates a new office serial number RR.
   */
  public OfficeTvnNumberMain() {
    super();
  }


  /**
   * Instantiates a new office serial number RR.
   *
   * @param id the id
   */
  public OfficeTvnNumberMain(Long id) {
    super();
    this.id = id;
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


  public Long getUsedTvnCount() {
    return usedTvnCount;
  }


  public void setUsedTvnCount(Long usedTvnCount) {
    this.usedTvnCount = usedTvnCount;
  }


  public Long getUsedTvnMin() {
    return usedTvnMin;
  }


  public void setUsedTvnMin(Long usedTvnMin) {
    this.usedTvnMin = usedTvnMin;
  }


  public Long getUsedTvnMax() {
    return usedTvnMax;
  }


  public void setUsedTvnMax(Long usedTvnMax) {
    this.usedTvnMax = usedTvnMax;
  }


  public static long getSerialversionuid() {
    return serialVersionUID;
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


  @Override
  public String toString() {
    return "OfficeTvnNumberMain [id=" + id + ", office=" + office + ", year=" + year
        + ", rangeFrom=" + rangeFrom + ", rangeTo=" + rangeTo + ", dateReceived=" + dateReceived
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
        + ", updatedDate=" + updatedDate + ", usedTvnCount=" + usedTvnCount + ", usedTvnMin="
        + usedTvnMin + ", usedTvnMax=" + usedTvnMax + ", tempId=" + tempId + "]";
  }


}
