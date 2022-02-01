/*
  * Modified by: pastolc
  * Last updated: 08 9, 20 4:34:33 PM
  */
package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "MAIN_NO_FREQ_FORM_DUE_DATE")
@JsonInclude(Include.NON_NULL)
public class MaintNoFreqFormDueDate implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "FORM_TYPE_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "START_DAY_BASIS", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String startDayBasis;

  @Column(name = "NO_OF_DAYS", nullable = true, columnDefinition = "NUMBER(2,0)")
  private Integer noOfDays;

  @Column(name = "NO_OF_YEARS", nullable = true, columnDefinition = "NUMBER(1,0)")
  private Integer noOfYears;

  @Column(name = "ADD_MONTH", nullable = true, columnDefinition = "NUMBER(2,0)")
  private Integer addMonth;

  @Column(name = "ACTUAL_DAY", nullable = true, columnDefinition = "NUMBER(2,0)")
  private Integer actualDay;

  @Column(name = "EFFECTIVE_DATE", nullable = false, columnDefinition = "DATE")
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", nullable = false, columnDefinition = "DATE")
  private Date expiryDate;

  public MaintNoFreqFormDueDate() {
    super();
  }

  public MaintNoFreqFormDueDate(String id, String startDayBasis, Integer noOfDays,
      Integer noOfYears, Integer addMonth, Integer actualDay, Date effectiveDate, Date expiryDate) {
    super();
    this.id = id;
    this.startDayBasis = startDayBasis;
    this.noOfDays = noOfDays;
    this.noOfYears = noOfYears;
    this.addMonth = addMonth;
    this.actualDay = actualDay;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
  }

  public String getId() {
    return id;
  }

  public void setFormTypeId(String id) {
    this.id = id;
  }

  public String getStartDayBasis() {
    return startDayBasis;
  }

  public void setStartDayBasis(String startDayBasis) {
    this.startDayBasis = startDayBasis;
  }

  public Integer getNoOfDays() {
    return noOfDays;
  }

  public void setNoOfDays(Integer noOfDays) {
    this.noOfDays = noOfDays;
  }

  public Integer getNoOfYears() {
    return noOfYears;
  }

  public void setNoOfYears(Integer noOfYears) {
    this.noOfYears = noOfYears;
  }

  public Integer getAddMonth() {
    return addMonth;
  }

  public void setAddMonth(Integer addMonth) {
    this.addMonth = addMonth;
  }

  public Integer getActualDay() {
    return actualDay;
  }

  public void setActualDay(Integer actualDay) {
    this.actualDay = actualDay;
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

  @Override
  public String toString() {
    return "MaintNoFreqFormDueDate [id=" + id + ", startDayBasis=" + startDayBasis
        + ", noOfDays=" + noOfDays + ", noOfYears=" + noOfYears + ", addMonth=" + addMonth
        + ", actualDay=" + actualDay + ", effectiveDate=" + effectiveDate + ", expiryDate="
        + expiryDate + "]";
  }

}
