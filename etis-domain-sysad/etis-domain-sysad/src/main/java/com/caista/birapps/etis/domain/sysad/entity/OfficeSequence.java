/*
  * Modified by: sarmier
  * Last updated: Nov 23, 2018 4:43:02 PM
  */
package com.caista.birapps.etis.domain.sysad.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name = "OFFICE_SEQUENCE", uniqueConstraints = @UniqueConstraint(
    columnNames = {"OFFICE_ID", "MODULE_CODE", "YEAR", "TYPE"}, name = "UC_OFFICE_SEQUENCE_01"))
@Check(constraints = "TYPE IN ('OCN', 'CN')")
@JsonInclude(Include.NON_NULL)
public class OfficeSequence implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "OFFICE_SEQUENCE_SequenceStyleGenerator")
  @GenericGenerator(name = "OFFICE_SEQUENCE_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_OFFICE_SEQUENCE"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "SEQUENCE_ID", nullable = false)
  private Long id;



  /** The ref office. */
  @ManyToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__OFFICE_SEQUENCE_01"), updatable = false)
  private Office office;

  /** The correspondence code or module code. */
  @Column(name = "MODULE_CODE")
  private String code;

  /** The minimum sequence value. */
  @Column(name = "MIN_VALUE", columnDefinition = "NUMBER(10,0)", nullable = false)
  private Integer minimumValue;

  /** The maximum sequence value. */
  @Column(name = "MAX_VALUE", columnDefinition = "NUMBER(10,0)", nullable = false)
  private Long maximumValue;

  /** The current sequence. */
  @Column(name = "CURRENT_VALUE", columnDefinition = "NUMBER(10,0)", nullable = false)
  private Long currentValue;

  /** The year. */
  @Column(name = "YEAR", columnDefinition = "NUMBER(4,0)", nullable = false)
  private Integer year;

  /** The sequence type. */
  @Column(name = "TYPE", nullable = false)
  private String type;

  @Column(name = "ORDER_SEQ", nullable = false)
  private String orderSeq;



  public OfficeSequence() {
    super();
  }



  public OfficeSequence(Long id, String code, Integer minimumValue, Long maximumValue,
      Long currentValue, Integer year, String type, String orderSeq, Office office) {
    super();
    this.id = id;
    this.code = code;
    this.minimumValue = minimumValue;
    this.maximumValue = maximumValue;
    this.currentValue = currentValue;
    this.year = year;
    this.type = type;
    this.orderSeq = orderSeq;
    this.office = office;
  }



  public Long getId() {
    return id;
  }



  public void setId(Long id) {
    this.id = id;
  }



  public String getCode() {
    return code;
  }



  public void setCode(String code) {
    this.code = code;
  }



  public Integer getMinimumValue() {
    return minimumValue;
  }



  public void setMinimumValue(Integer minimumValue) {
    this.minimumValue = minimumValue;
  }



  public Long getMaximumValue() {
    return maximumValue;
  }



  public void setMaximumValue(Long maximumValue) {
    this.maximumValue = maximumValue;
  }



  public Long getCurrentValue() {
    return currentValue;
  }



  public void setCurrentValue(Long currentValue) {
    this.currentValue = currentValue;
  }



  public Integer getYear() {
    return year;
  }



  public void setYear(Integer year) {
    this.year = year;
  }



  public String getType() {
    return type;
  }



  public void setType(String type) {
    this.type = type;
  }



  public String getOrderSeq() {
    return orderSeq;
  }



  public void setOrderSeq(String orderSeq) {
    this.orderSeq = orderSeq;
  }



  public Office getOffice() {
    return office;
  }



  public void setOffice(Office office) {
    this.office = office;
  }



  public static long getSerialversionuid() {
    return serialVersionUID;
  }



  @Override
  public String toString() {
    return "OfficeSequence [id=" + id + ", code=" + code + ", minimumValue=" + minimumValue
        + ", maximumValue=" + maximumValue + ", currentValue=" + currentValue + ", year=" + year
        + ", type=" + type + ", orderSeq=" + orderSeq + ", office=" + office + "]";
  }


}
