/*
 * Modified by: fuentem
 * Last updated: Oct 16, 2018 2:15:54 PM
 */
package com.caista.birapps.etis.domain.trs.tin.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

/**
 * The Class UnusedTIN.
 */
@Entity
@Table(name = "REF_TIN_UNUSED")
public class UnusedTIN {

  /** The tin series. */
  @Column(name = "TIN_SERIES", columnDefinition = "VARCHAR2(20 BYTE)", nullable = false)
  private String tinSeries;

  /** The tin. */
  @Id
  @Column(name = "UNUSED_TIN", columnDefinition = "VARCHAR2(20 BYTE)", nullable = false)
  private String tin;

  /** The created date. */
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATED_DATE", insertable = false,
      columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE", nullable = false)
  private Date createdDate;

  /** The created by. */
  @Column(name = "CREATED_BY", columnDefinition = "VARCHAR2(50 BYTE)", nullable = false)
  private String createdBy;


  /**
   * Instantiates a new unused TIN.
   *
   * @param tinSeries the tin series
   * @param tin the tin
   * @param createdBy the created by
   */
  public UnusedTIN(String tinSeries, String tin, String createdBy) {
    super();
    this.tinSeries = tinSeries;
    this.tin = tin;
    this.createdBy = createdBy;
  }

  public String getTinSeries() {
    return tinSeries;
  }

  public void setTinSeries(String tinSeries) {
    this.tinSeries = tinSeries;
  }

  public String getTin() {
    return tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  @Override
  public String toString() {
    return "UnusedTIN [tinSeries=" + tinSeries + ", tin=" + tin + ", createdDate=" + createdDate
        + ", createdBy=" + createdBy + "]";
  }

}
