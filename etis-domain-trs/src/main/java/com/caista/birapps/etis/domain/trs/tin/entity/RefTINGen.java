/*
 * Last modified by: delmund
 * Last updated date: Jun 1, 2018 3:04:37 PM
 */
package com.caista.birapps.etis.domain.trs.tin.entity;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Check;

/**
 * The Class RefTINGen.
 */
@Entity
@Table(name = "ref_tin_gen")
@Check(constraints = "part_id IN (1, 2, 3, 4, 5)")
public class RefTINGen implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The tin series. */
  @Id
  @Column(name = "tin_series", columnDefinition = "VARCHAR2(20 BYTE)", nullable = false)
  private String tinSeries;
  
  /** The part id. */
  @Id
  @Column(name = "part_id", length = 1, nullable = false)
  private Integer partId;
  
  /** The part type. */
  @Column(name = "part_type", columnDefinition = "VARCHAR2(20 BYTE)", nullable = false)
  private String partType;
  
  /** The position from. */
  @Column(name = "pos_fr", length = 2, nullable = false)
  private Integer positionFrom;
  
  /** The position to. */
  @Column(name = "pos_to", length = 2, nullable = false)
  private Integer positionTo;
  
  /** The part value. */
  @Column(name = "part_value", columnDefinition = "VARCHAR2(100 BYTE)", nullable = true)
  private String partValue;
  
  /** The status. */
  @Column(name = "status", columnDefinition = "VARCHAR2(15 BYTE)", nullable = true)
  private String status;

  public String getTinSeries() {
    return tinSeries;
  }

  public void setTinSeries(String tinSeries) {
    this.tinSeries = tinSeries;
  }

  public Integer getPartId() {
    return partId;
  }

  public void setPartId(Integer partId) {
    this.partId = partId;
  }

  public String getPartType() {
    return partType;
  }

  public void setPartType(String partType) {
    this.partType = partType;
  }

  public Integer getPositionFrom() {
    return positionFrom;
  }

  public void setPositionFrom(Integer positionFrom) {
    this.positionFrom = positionFrom;
  }

  public Integer getPositionTo() {
    return positionTo;
  }

  public void setPositionTo(Integer positionTo) {
    this.positionTo = positionTo;
  }

  public String getPartValue() {
    return partValue;
  }

  public void setPartValue(String partValue) {
    this.partValue = partValue;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
