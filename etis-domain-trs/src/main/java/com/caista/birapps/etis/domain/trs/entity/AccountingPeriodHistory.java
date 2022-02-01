/*
 * Last modified by: delmund
 * Last updated date: Jun 28, 2019 9:59:44 AM
 */
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class AccountingPeriodHistory.
 */
@Entity
@Table(name = "TAXPAYER_ACCOUNTING_PERIOD")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountingPeriodHistory implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "TAXPAYER_ACCOUNTING_PERIOD_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "TAXPAYER_ACCOUNTING_PERIOD_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_ACCOUNTING_PERIOD"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_ACCOUNTING_PERIOD_ID", unique = true, nullable = false)
  private Long id;

  /** The previous accounting period. */
  @Column(name = "PREV_ACCOUNTING_PERIOD_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String prevAccountingPeriodId;

  @Column(name = "PREV_ACCOUNTING_PERIOD_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String prevAccountingPeriodDescription;

  @Column(name = "PREV_ACCOUNTING_YR_START_MONTH_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String prevAccountingYearStartMonthId;

  /** The previous accounting year start month. */
  @Column(name = "PREV_ACCOUNTING_YR_START_MONTH_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String prevAccountingYearStartMonthDescription;

  /** The previous effective date. */
  @Column(name = "PREV_EFFECTIVE_DATE", columnDefinition = "DATE")
  private Date prevEffectiveDate;

  /** The new accounting period. */
  @Column(name = "NEW_ACCOUNTING_PERIOD_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String newAccountingPeriodId;

  @Column(name = "NEW_ACCOUNTING_PERIOD_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String newAccountingPeriodDescription;

  @Column(name = "NEW_ACCOUNTING_YR_START_MONTH_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String newAccountingYearStartMonthId;

  /** The new accounting year start month. */
  @Column(name = "NEW_ACCOUNTING_YR_START_MONTH_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String newAccountingYearStartMonthDescription;

  /** The new effective date. */
  @Column(name = "NEW_EFFECTIVE_DATE", columnDefinition = "DATE")
  private Date newEffectiveDate;

  /** The created by. */
  @Column(name = "CREATED_BY", nullable = false, columnDefinition = "VARCHAR2(50 BYTE)")
  private String createdBy;

  /** The created date. */
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE", nullable = false)
  private Date createdDate;

  /** The taxpayer. */
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID",
      foreignKey = @ForeignKey(name = "FK_ACCOUNTING_PERIOD_HISTORY_TAXPAYER_01"))
  private TaxPayer taxpayer;

  @Transient
  private Date endEffectivityDate;

  public AccountingPeriodHistory() {
    super();
  }

  public AccountingPeriodHistory(Long id, String prevAccountingPeriodId,
      String prevAccountingPeriodDescription, String prevAccountingYearStartMonthId,
      String prevAccountingYearStartMonthDescription, Date prevEffectiveDate,
      String newAccountingPeriodId, String newAccountingPeriodDescription,
      String newAccountingYearStartMonthId, String newAccountingYearStartMonthDescription,
      Date newEffectiveDate, String changedBy, Date changedDate, String createdBy,
      Date createdDate, TaxPayer taxpayer) {
    super();
    this.id = id;
    this.prevAccountingPeriodId = prevAccountingPeriodId;
    this.prevAccountingPeriodDescription = prevAccountingPeriodDescription;
    this.prevAccountingYearStartMonthId = prevAccountingYearStartMonthId;
    this.prevAccountingYearStartMonthDescription = prevAccountingYearStartMonthDescription;
    this.prevEffectiveDate = prevEffectiveDate;
    this.newAccountingPeriodId = newAccountingPeriodId;
    this.newAccountingPeriodDescription = newAccountingPeriodDescription;
    this.newAccountingYearStartMonthId = newAccountingYearStartMonthId;
    this.newAccountingYearStartMonthDescription = newAccountingYearStartMonthDescription;
    this.newEffectiveDate = newEffectiveDate;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.taxpayer = taxpayer;
  }

  public AccountingPeriodHistory(String newAccountingPeriodId,
      String newAccountingPeriodDescription, String newAccountingYearStartMonthId,
      String newAccountingYearStartMonthDescription, Date newEffectiveDate,
      String createdBy, Date createdDate, TaxPayer taxpayer) {
    super();
    this.newAccountingPeriodId = newAccountingPeriodId;
    this.newAccountingPeriodDescription = newAccountingPeriodDescription;
    this.newAccountingYearStartMonthId = newAccountingYearStartMonthId;
    this.newAccountingYearStartMonthDescription = newAccountingYearStartMonthDescription;
    this.newEffectiveDate = newEffectiveDate;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.taxpayer = taxpayer;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPrevAccountingPeriodId() {
    return prevAccountingPeriodId;
  }

  public void setPrevAccountingPeriodId(String prevAccountingPeriodId) {
    this.prevAccountingPeriodId = prevAccountingPeriodId;
  }

  public String getPrevAccountingPeriodDescription() {
    return prevAccountingPeriodDescription;
  }

  public void setPrevAccountingPeriodDescription(String prevAccountingPeriodDescription) {
    this.prevAccountingPeriodDescription = prevAccountingPeriodDescription;
  }

  public String getPrevAccountingYearStartMonthId() {
    return prevAccountingYearStartMonthId;
  }

  public void setPrevAccountingYearStartMonthId(String prevAccountingYearStartMonthId) {
    this.prevAccountingYearStartMonthId = prevAccountingYearStartMonthId;
  }

  public String getPrevAccountingYearStartMonthDescription() {
    return prevAccountingYearStartMonthDescription;
  }

  public void setPrevAccountingYearStartMonthDescription(
      String prevAccountingYearStartMonthDescription) {
    this.prevAccountingYearStartMonthDescription = prevAccountingYearStartMonthDescription;
  }

  public Date getPrevEffectiveDate() {
    return prevEffectiveDate;
  }

  public void setPrevEffectiveDate(Date prevEffectiveDate) {
    this.prevEffectiveDate = prevEffectiveDate;
  }

  public String getNewAccountingPeriodId() {
    return newAccountingPeriodId;
  }

  public void setNewAccountingPeriodId(String newAccountingPeriodId) {
    this.newAccountingPeriodId = newAccountingPeriodId;
  }

  public String getNewAccountingPeriodDescription() {
    return newAccountingPeriodDescription;
  }

  public void setNewAccountingPeriodDescription(String newAccountingPeriodDescription) {
    this.newAccountingPeriodDescription = newAccountingPeriodDescription;
  }

  public String getNewAccountingYearStartMonthId() {
    return newAccountingYearStartMonthId;
  }

  public void setNewAccountingYearStartMonthId(String newAccountingYearStartMonthId) {
    this.newAccountingYearStartMonthId = newAccountingYearStartMonthId;
  }

  public String getNewAccountingYearStartMonthDescription() {
    return newAccountingYearStartMonthDescription;
  }

  public void setNewAccountingYearStartMonthDescription(
      String newAccountingYearStartMonthDescription) {
    this.newAccountingYearStartMonthDescription = newAccountingYearStartMonthDescription;
  }

  public Date getNewEffectiveDate() {
    return newEffectiveDate;
  }

  public void setNewEffectiveDate(Date newEffectiveDate) {
    this.newEffectiveDate = newEffectiveDate;
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

  public TaxPayer getTaxpayer() {
    return taxpayer;
  }

  public void setTaxpayer(TaxPayer taxpayer) {
    this.taxpayer = taxpayer;
  }

  public Date getEndEffectivityDate() {
    return endEffectivityDate;
  }

  public void setEndEffectivityDate(Date endEffectivityDate) {
    this.endEffectivityDate = endEffectivityDate;
  }

  @Override
  public String toString() {
    return "AccountingPeriodHistory [id=" + id + ", prevAccountingPeriodId="
        + prevAccountingPeriodId + ", prevAccountingPeriodDescription="
        + prevAccountingPeriodDescription + ", prevAccountingYearStartMonthId="
        + prevAccountingYearStartMonthId + ", prevAccountingYearStartMonthDescription="
        + prevAccountingYearStartMonthDescription + ", prevEffectiveDate=" + prevEffectiveDate
        + ", newAccountingPeriodId=" + newAccountingPeriodId + ", newAccountingPeriodDescription="
        + newAccountingPeriodDescription + ", newAccountingYearStartMonthId="
        + newAccountingYearStartMonthId + ", newAccountingYearStartMonthDescription="
        + newAccountingYearStartMonthDescription + ", newEffectiveDate=" + newEffectiveDate
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
  }

}
