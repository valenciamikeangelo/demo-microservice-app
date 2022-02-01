/*
  * Modified by: romerov
  * Last updated: 03 13, 20 7:23:30 PM
*/
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.trs.utils.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class IncentiveDetail.
 */

@Entity
@Table(name = "TAXPAYER_INCENTIVE_DETAIL")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncentiveDetail implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "TAXPAYER_INCENTIVE_DETAIL_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "TAXPAYER_INCENTIVE_DETAIL_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_INCENTIVE_DETAIL"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_INCENTIVE_DETAIL_ID", unique = true, nullable = false)
  private Long id;

  @Column(name = "INCENTIVE_CLASSIFICATION_ID", columnDefinition = "VARCHAR2(100 BYTE)")
  private String investmentPromotionAgencyId;

  @Column(name = "INCENTIVE_CLASSIFICATION_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String investmentPromotionAgencyDescription;

  @Column(name = "INCENTIVE_LEGAL_BASIS_ID", columnDefinition = "VARCHAR2(100 BYTE)")
  private String legalBasisId;

  @Column(name = "INCENTIVE_LEGAL_BASIS_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String legalBasisDescription;

  @Column(name = "INCENTIVE_GRANTED_ID", columnDefinition = "VARCHAR2(100 BYTE)")
  private String incentivesGrantedId;

  @Column(name = "INCENTIVE_GRANTED_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String incentivesGrantedDescription;

  /** The number of years. */
  @Column(name = "NUMBER_OF_YEARS", length = 4)
  private Integer numberOfYears;

  /** The start date. */
  @Column(name = "START_DATE", columnDefinition = "DATE")
  private Date startDate;

  /** The end date. */
  @Column(name = "END_DATE", columnDefinition = "DATE")
  private Date endDate;

  /** The registration accreditation. */
  @Column(name = "REGISTRATION_ACCREDITATION", columnDefinition = "VARCHAR2(255 BYTE)")
  private String registrationAccreditation;

  /** The effectivity from. */
  @Column(name = "EFFECTIVITY_FROM", columnDefinition = "DATE")
  private Date effectivityFrom;

  /** The effectivity to. */
  @Column(name = "EFFECTIVITY_TO", columnDefinition = "DATE")
  private Date effectivityTo;

  /** The date issued. */
  @Column(name = "DATE_ISSUED", columnDefinition = "DATE")
  private Date dateIssued;

  /** The registered activity summaries. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_INCENTIVE_DETAIL_ID", foreignKey = @ForeignKey(
      name = "FK_TAXPAYER_REGISTERED_ACTIVITY_SUMMARY__TAXPAYER_INCENTIVE_DETAIL_ID_01"))
  private List<RegisteredActivitySummary> registeredActivitySummaries;

  /** The data source created. */
  @Column(name = "DATA_SOURCE_CREATED", columnDefinition = "VARCHAR2(5 BYTE)")
  private String dataSourceCreated;

  /** The data source updated. */
  @Column(name = "DATA_SOURCE_UPDATED", columnDefinition = "VARCHAR2(5 BYTE)")
  private String dataSourceUpdated;

  /** The created by. */
  @Column(name = "CREATED_BY", nullable = false, columnDefinition = "VARCHAR2(50 BYTE)")
  private String createdBy;

  /** The created date. */
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE",
      nullable = false)
  private Date createdDate;

  /** The updated by. */
  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(50 BYTE)")
  private String updatedBy;

  /** The updated date. */
  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  /** The cancellation date. */
  @Column(name = "CANCELLATION_DATE")
  private Date cancellationDate;

  @Transient
  private String status;

  public String getStatus() {
    if (this.cancellationDate != null) {
      this.status = (String.valueOf(IncentiveStatusEnum.CANCELLED));
    } else {
      Date currDate = new Date();
      if (this.endDate != null) {
        if (currDate.after(this.endDate)) {
          this.status = (String.valueOf(IncentiveStatusEnum.EXPIRED));
        } else if (effectivityTo != null
            && (currDate.equals(effectivityTo) || currDate.after(effectivityTo))) {
          this.status = (String.valueOf(IncentiveStatusEnum.EXPIRED));
        } else {
          this.status = (String.valueOf(IncentiveStatusEnum.ACTIVE));
        }
      } else {
        this.status = (String.valueOf(IncentiveStatusEnum.ACTIVE));
      }
    }
    return status;
  }

  public IncentiveDetail() {
    super();
    this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
  }

  public IncentiveDetail(Long id, String investmentPromotionAgencyId,
      String investmentPromotionAgencyDescription, String legalBasisId,
      String legalBasisDescription, String incentivesGrantedId, String incentivesGrantedDescription,
      Integer numberOfYears, Date startDate, Date endDate, String registrationAccreditation,
      Date effectivityFrom, Date effectivityTo, Date dateIssued,
      List<RegisteredActivitySummary> registeredActivitySummaries, String dataSourceCreated,
      String dataSourceUpdated, String createdBy, Date createdDate, String updatedBy,
      Date updatedDate, Date cancellationDate, String status) {
    super();
    this.id = id;
    this.investmentPromotionAgencyId = investmentPromotionAgencyId;
    this.investmentPromotionAgencyDescription = investmentPromotionAgencyDescription;
    this.legalBasisId = legalBasisId;
    this.legalBasisDescription = legalBasisDescription;
    this.incentivesGrantedId = incentivesGrantedId;
    this.incentivesGrantedDescription = incentivesGrantedDescription;
    this.numberOfYears = numberOfYears;
    this.startDate = startDate;
    this.endDate = endDate;
    this.registrationAccreditation = registrationAccreditation;
    this.effectivityFrom = effectivityFrom;
    this.effectivityTo = effectivityTo;
    this.dateIssued = dateIssued;
    this.registeredActivitySummaries = registeredActivitySummaries;
    this.dataSourceCreated = dataSourceCreated;
    this.dataSourceUpdated = dataSourceUpdated;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.cancellationDate = cancellationDate;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getInvestmentPromotionAgencyId() {
    return investmentPromotionAgencyId;
  }

  public void setInvestmentPromotionAgencyId(String investmentPromotionAgencyId) {
    this.investmentPromotionAgencyId = investmentPromotionAgencyId;
  }

  public String getInvestmentPromotionAgencyDescription() {
    return investmentPromotionAgencyDescription;
  }

  public void setInvestmentPromotionAgencyDescription(String investmentPromotionAgencyDescription) {
    this.investmentPromotionAgencyDescription = investmentPromotionAgencyDescription;
  }

  public String getLegalBasisId() {
    return legalBasisId;
  }

  public void setLegalBasisId(String legalBasisId) {
    this.legalBasisId = legalBasisId;
  }

  public String getLegalBasisDescription() {
    return legalBasisDescription;
  }

  public void setLegalBasisDescription(String legalBasisDescription) {
    this.legalBasisDescription = legalBasisDescription;
  }

  public String getIncentivesGrantedId() {
    return incentivesGrantedId;
  }

  public void setIncentivesGrantedId(String incentivesGrantedId) {
    this.incentivesGrantedId = incentivesGrantedId;
  }

  public String getIncentivesGrantedDescription() {
    return incentivesGrantedDescription;
  }

  public void setIncentivesGrantedDescription(String incentivesGrantedDescription) {
    this.incentivesGrantedDescription = incentivesGrantedDescription;
  }

  public Integer getNumberOfYears() {
    return numberOfYears;
  }

  public void setNumberOfYears(Integer numberOfYears) {
    this.numberOfYears = numberOfYears;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getRegistrationAccreditation() {
    return registrationAccreditation;
  }

  public void setRegistrationAccreditation(String registrationAccreditation) {
    this.registrationAccreditation = registrationAccreditation;
  }

  public Date getEffectivityFrom() {
    return effectivityFrom;
  }

  public void setEffectivityFrom(Date effectivityFrom) {
    this.effectivityFrom = effectivityFrom;
  }

  public Date getEffectivityTo() {
    return effectivityTo;
  }

  public void setEffectivityTo(Date effectivityTo) {
    this.effectivityTo = effectivityTo;
  }

  public Date getDateIssued() {
    return dateIssued;
  }

  public void setDateIssued(Date dateIssued) {
    this.dateIssued = dateIssued;
  }

  public List<RegisteredActivitySummary> getRegisteredActivitySummaries() {
    return registeredActivitySummaries;
  }

  public void setRegisteredActivitySummaries(
      List<RegisteredActivitySummary> registeredActivitySummaries) {
    this.registeredActivitySummaries = registeredActivitySummaries;
  }

  public String getDataSourceCreated() {
    return dataSourceCreated;
  }

  public void setDataSourceCreated(String dataSourceCreated) {
    this.dataSourceCreated = dataSourceCreated;
  }

  public String getDataSourceUpdated() {
    return dataSourceUpdated;
  }

  public void setDataSourceUpdated(String dataSourceUpdated) {
    this.dataSourceUpdated = dataSourceUpdated;
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

  public Date getCancellationDate() {
    return cancellationDate;
  }

  public void setCancellationDate(Date cancellationDate) {
    this.cancellationDate = cancellationDate;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "IncentiveDetail [id=" + id + ", investmentPromotionAgencyId="
        + investmentPromotionAgencyId + ", investmentPromotionAgencyDescription="
        + investmentPromotionAgencyDescription + ", legalBasisId=" + legalBasisId
        + ", legalBasisDescription=" + legalBasisDescription + ", incentivesGrantedId="
        + incentivesGrantedId + ", incentivesGrantedDescription=" + incentivesGrantedDescription
        + ", numberOfYears=" + numberOfYears + ", startDate=" + startDate + ", endDate=" + endDate
        + ", registrationAccreditation=" + registrationAccreditation + ", effectivityFrom="
        + effectivityFrom + ", effectivityTo=" + effectivityTo + ", dateIssued=" + dateIssued
        + ", registeredActivitySummaries=" + registeredActivitySummaries + ", dataSourceCreated="
        + dataSourceCreated + ", dataSourceUpdated=" + dataSourceUpdated + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + ", cancellationDate=" + cancellationDate + ", status=" + status + "]";
  }

}
